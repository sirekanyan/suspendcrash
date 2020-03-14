package ru.tinkoff.sample.suspendcrash.case1

class Model(val foo: Boolean, val bar: Int)

class CrashCase1 {

    suspend fun crash() {
        Model(true, bar())
    }

    private suspend fun bar() = 0

}
