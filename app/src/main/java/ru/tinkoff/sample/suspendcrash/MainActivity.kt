package ru.tinkoff.sample.suspendcrash

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.tinkoff.sample.suspendcrash.case1.CrashCase1
import ru.tinkoff.sample.suspendcrash.case2.CrashCase2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            try {
                CrashCase1().crash()
                Log.i("suspendcrash", "case 1 passed, try android api 19 or less")
            } catch (e: VerifyError) {
                Log.e("suspendcrash", "VerifyError case 1 FAILED", e)
            }
            try {
                CrashCase2().crash()
                Log.i("suspendcrash", "case 2 passed, try android api 19 or less")
            } catch (e: VerifyError) {
                Log.e("suspendcrash", "VerifyError case 2 FAILED", e)
            }
        }
    }

}
