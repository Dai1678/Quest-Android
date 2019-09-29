package com.dai1678.quest.repository

class DoctorRepository : BaseRepository() {

    companion object {

        const val LIMIT = 100

        @Volatile
        private var instance: DoctorRepository? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: DoctorRepository().also { instance = it }
        }
    }
}
