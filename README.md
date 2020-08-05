# RemoteLogger
![GitHub Release Date](https://img.shields.io/github/release-date/iambaljeet/RemoteLogger)
![GitHub All Releases](https://img.shields.io/github/downloads/iambaljeet/RemoteLogger/total)
[![GitHub license](https://img.shields.io/github/license/iambaljeet/Covid19Tracker)](LICENSE)
![Github Followers](https://img.shields.io/github/followers/iambaljeet?label=Follow&style=social)
![GitHub stars](https://img.shields.io/github/stars/iambaljeet/RemoteLogger)
![GitHub forks](https://img.shields.io/github/forks/iambaljeet/RemoteLogger)
![GitHub watchers](https://img.shields.io/github/watchers/iambaljeet/RemoteLogger?style=social)
![Tweet](	https://img.shields.io/twitter/url?url=https%3A%2F%2Fgithub.com%2Fiambaljeet%2FRemoteLogger)
![Twitter Follow](https://img.shields.io/twitter/follow/yetanotherdev_?label=Follow&style=social)

**RemoteLogger** is an Android library 📱 used to log anything/crashes in a file. 

# Usage
1. Setting up library to your App

Add below to your project level 'build.gradle' file.
```
        allprojects {
            repositories {
                maven { url 'https://jitpack.io' }
            }
        }
```
Add library to your app level 'build.gradle' file.
```
        implementation 'com.github.iambaljeet:RemoteLogger:1.0'
```

2. First setup RemoteLogger in your Application class
```
        RemoteLoggerBuilder()
            .setApplication(this)
            .setLogFileNamePrefix("userId_")
            .setCrashLogsEnabled(true)
            .build()
```

Logging crash: CrashLogging is optional and can be disabled by passing 'false' in 'setCrashLogsEnabled(false)' method.
Adding prefix to file: There is an optional parameter to add prefix to Log file using 'setLogFileNamePrefix("prefix_)' method.

3. Now simply Log anything you want using some generic methods available in RemoteLogger.
```
        RemoteLogger.e(TAG, "This is a error log")
        RemoteLogger.d(TAG, "This is a debug log")
        RemoteLogger.i(TAG, "This is a info log")
        RemoteLogger.v(TAG, "This is a verbose log")
```

Also, you can pass a throwable to any of the Log as an optional parameter
```
        RemoteLogger.e(TAG, "This is a error log", throwable)
```

## About
. This library aims to print all provided logs to a File(Stored in local storage) also it supports logging all crashes in the file in a well formatted way along with device, Android version etc. So one can easily debug apps remotely by sending the log file to server.
- Ability to add prefix to the log file. 
- Easy and simple methods to get started.
- It supports logging of crashes too.

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.

**Contributed By:** [Baljeet Singh](https://github.com/iambaljeet/)

## Contact
If you need any help, you can connect with me.

Twitter:- [YetAnotherDev](https://twitter.com/yetanotherdev_)
Visit:- [baljeet.dev](https://baljeet.dev)

## License

GNU License Visit:- [LICENSE](https://github.com/iambaljeet/RemoteLogger/blob/master/LICENSE)
