package com.dai1678.quest.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController

/**
 * NavigationでFragmentの戻り値を処理する処理の拡張関数
 * https://medium.com/@star_zero/navigation%E3%81%A7fragment%E3%81%AE%E6%88%BB%E3%82%8A%E5%80%A4%E3%82%92%E6%89%B1%E3%81%86-56d54395c64a
 */

// 値を返すFragment側で返す値を設定する
fun <T> Fragment.setResult(key: String, value: T) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key, value)
}

// 値を受け取る側でLiveDataとして受け取る
fun <T> Fragment.getResultLiveData(key: String): LiveData<T>? {
    return findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
}

// 値を受け取る側で通常の型として受け取る
fun <T> Fragment.getResult(key: String): T? {
    return findNavController().currentBackStackEntry?.savedStateHandle?.get(key)
}

// 戻り値を1回しか受け取りたくないとき使用
fun <T> Fragment.getResultOnce(owner: LifecycleOwner, key: String, onChanged: (T) -> Unit) {
    findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)?.observe(owner) {
        onChanged.invoke(it)
        findNavController().currentBackStackEntry?.savedStateHandle?.remove<T>(key)
    }
}
