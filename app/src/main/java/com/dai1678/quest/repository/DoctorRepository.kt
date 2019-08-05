package com.dai1678.quest.repository

class DoctorRepository : BaseRepository() {

    companion object Factory {
        private var instance: DoctorRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: DoctorRepository().also { instance = it }
        }
    }
}
