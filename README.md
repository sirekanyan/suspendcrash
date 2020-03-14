# Demo VerifyError crash on API 19 or less

See https://youtrack.jetbrains.com/issue/KT-35616

## Case 1

```kotlin
class Model(val foo: Boolean, val bar: Int)

class CrashCase1 {

    suspend fun crash() {
        Model(true, bar())
    }

    private suspend fun bar() = 0

}
```

```
W/dalvikvm: VFY: register1 v4 type 17, wanted 5
W/dalvikvm: VFY:  rejecting opcode 0x70 at 0x0052
W/dalvikvm: VFY:  rejected Lru/tinkoff/sample/suspendcrash/case1/CrashCase1;.crash (Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
W/dalvikvm: Verifier rejected class Lru/tinkoff/sample/suspendcrash/case1/CrashCase1;
E/suspendcrash: VerifyError case 1 FAILED
    java.lang.VerifyError: ru/tinkoff/sample/suspendcrash/case1/CrashCase1
        at ru.tinkoff.sample.suspendcrash.MainActivity$onStart$1.invokeSuspend(MainActivity.kt:22)
        at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
        at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:56)
        at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(CoroutineScheduler.kt:571)
        at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask(CoroutineScheduler.kt:738)
        at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker(CoroutineScheduler.kt:678)
        at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(CoroutineScheduler.kt:665)
```

## Case 2

```kotlin
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
```

```
W/dalvikvm: VFY: register1 v2 type 17, wanted 5
W/dalvikvm: VFY:  rejecting opcode 0x70 at 0x0059
W/dalvikvm: VFY:  rejected Lru/tinkoff/sample/suspendcrash/case2/CrashCase2;.crash (Lkotlin/coroutines/Continuation;)Ljava/lang/Object;
W/dalvikvm: Verifier rejected class Lru/tinkoff/sample/suspendcrash/case2/CrashCase2;
E/suspendcrash: VerifyError case 2 FAILED
    java.lang.VerifyError: ru/tinkoff/sample/suspendcrash/case2/CrashCase2
        at ru.tinkoff.sample.suspendcrash.MainActivity$onStart$1.invokeSuspend(MainActivity.kt:28)
        at kotlin.coroutines.jvm.internal.BaseContinuationImpl.resumeWith(ContinuationImpl.kt:33)
        at kotlinx.coroutines.DispatchedTask.run(DispatchedTask.kt:56)
        at kotlinx.coroutines.scheduling.CoroutineScheduler.runSafely(CoroutineScheduler.kt:571)
        at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.executeTask(CoroutineScheduler.kt:738)
        at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.runWorker(CoroutineScheduler.kt:678)
        at kotlinx.coroutines.scheduling.CoroutineScheduler$Worker.run(CoroutineScheduler.kt:665)
```
