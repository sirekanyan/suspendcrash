package ru.tinkoff.sample.suspendcrash

import kotlinx.coroutines.delay

class Repository {

    suspend fun getModel(): Model {
        return Model(false, getName())
    }

    private suspend fun getName(): String {
        delay(10)
        return "name"
    }

}
