package com.dai1678.quest.repository

class HospitalRepository : BaseRepository() {

    companion object Factory {
        private var instance: HospitalRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: HospitalRepository().also { instance = it }
        }
    }
}
