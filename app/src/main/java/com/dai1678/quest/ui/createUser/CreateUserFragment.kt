package com.dai1678.quest.ui.createUser

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dai1678.quest.databinding.FragmentCreateUserBinding

class CreateUserFragment : Fragment() {

    private val createUserViewModel: CreateUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentCreateUserBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@CreateUserFragment
            viewModel = createUserViewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
