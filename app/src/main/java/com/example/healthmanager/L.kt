package com.example.healthmanager

import android.util.Log

object L {

    private var TAG = "zp"
    private val MIN_STACK_OFFSET = 3

    fun init(tag: String = TAG):L {
        TAG = tag
        return this
    }

    fun v(msg: String) {
        Log.v(TAG, String.format(getMethodInfo(), msg))
    }

    fun d(msg: String) {
        if (BuildConfig.DEBUG) {
            Log.d(TAG, String.format(getMethodInfo(), msg))
        }
    }

    fun i(msg: String) {
        Log.i(TAG, String.format(getMethodInfo(), msg))
    }

    fun w(msg: String) {
        Log.w(TAG, String.format(getMethodInfo(), msg))
    }

    fun e(msg: String) {
        Log.e(TAG, String.format(getMethodInfo(), msg))
    }

    private fun getMethodInfo(): String {
        val sElements = Thread.currentThread().stackTrace

        var stackOffset = getStackOffset(sElements)

        stackOffset++
        val builder = StringBuilder()

        builder
            .append("(")
            .append(sElements[stackOffset].fileName)
            .append(":")
            .append(sElements[stackOffset].lineNumber)
            .append(") ")
            .append(sElements[stackOffset].methodName)
            .append("(): ")
            .append("%s")

        return builder.toString()
    }

    fun getStackOffset(trace: Array<StackTraceElement>): Int {
        var i = MIN_STACK_OFFSET
        while (i < trace.size) {
            val e = trace[i]
            val name = e.className
            if (name != L::class.java.name) {
                return --i
            }
            i++
        }
        return -1
    }
}