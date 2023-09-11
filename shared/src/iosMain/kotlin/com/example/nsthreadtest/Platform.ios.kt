package com.example.nsthreadtest

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ExportObjCClass
import kotlinx.cinterop.ObjCAction
import platform.Foundation.NSSelectorFromString
import platform.Foundation.NSThread
import platform.UIKit.UIDevice
import platform.darwin.NSObject
import platform.posix.sleep

class IOSPlatform : Platform {
    override val name: String =
        UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()


interface Runnable {
    fun run()
}

@ExportObjCClass
class RunnableWrapper(val runnable: Runnable) : NSObject() {

    @ObjCAction
    fun run() {
        runnable.run()
    }
}

@OptIn(ExperimentalForeignApi::class)
actual fun nsThreadTest() {
    val r = object : Runnable {
        override fun run() {
            while (true) {
                print("testing....")
                sleep(1u)
            }
        }

    }

    val thread = NSThread(r, NSSelectorFromString("run"), null)

    thread.start()
    //    val thread = NSThread{
    //        rw.run()
    //    }
    //
    //    thread.start()
}

