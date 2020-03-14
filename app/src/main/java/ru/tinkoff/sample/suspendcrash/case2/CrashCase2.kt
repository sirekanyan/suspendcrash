package ru.tinkoff.sample.suspendcrash.case2

class Model(val foo: Boolean, val bar: Int, val baz: Int)

class CrashCase2 {

    suspend fun crash() {
        val foo = true
        val bar = bar()
        Model(foo, bar, baz())
    }

    private suspend fun bar() = 0

    private inline fun baz() = 0

}
