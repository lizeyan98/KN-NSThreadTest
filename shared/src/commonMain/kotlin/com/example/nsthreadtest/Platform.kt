package com.example.nsthreadtest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect fun nsThreadTest()