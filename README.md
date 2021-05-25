# Bitcoin metrics charts


The goal of this project is to demonstrate skills on Android development as well as to apply some of the best practices in Mobile development.

The project aims to replicate real world development scenarios, with a well defined architecture, a continuous integration setup, unit and integration tests.

## Stack

The main technologies and concepts used are:

- Kotlin
- Clean Architecture + MVVM on presentation
- Multi gradle modules
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines)
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) (ViewModel)
- Dependency injection using [Hilt](https://dagger.dev/hilt/)
- Unit tests using [Junit](https://junit.org/junit4/) + [MockK](https://mockk.io/) + [Robolectric](http://robolectric.org/)
- Integration tests using [Espresso]() and following the [Robot Pattern](https://jakewharton.com/testing-robots/)
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)

For a list of all project dependencies, you may run this gradle command: `./gradlew <module>:dependencies` where ***module*** is one of the gradle modules on this project.

## Setup

This application was built using the Android Gradle Plugin 3.5.3, Gradle 6.7.1 and Kotlin 1.5.0. In order to import it into Android Studio, make sure your setup meets these requirements. It also makes heavy use of the AndroidX libraries and it is important that the [jetifier](https://developer.android.com/studio/command-line/jetifier) is enabled on the [gradle.properties](https://github.com/igorvilela28/Chuck-Norris-Facts/blob/master/gradle.properties) file.

## Build and tests

The follow gradle command will assemble and unit test the application

```
./gradlew build
```

After building the application, you will be able to grab the generated artifact apks from `{projectPath}/app/build/outputs/apk` and install on some device.

To install it using the command line, use:

```
./gradlew install<BuildType>
```

Where ***BuildType*** is one of the following: *Debug*, *Release*.

The following command will run all tests (unit + integration) and install the APK if all is OK.

```
./gradlew build connectAndroidTest install<BuildType>
```

***IMPORTANT:*** In order to run the integration tests, you will need to have an online AVD or Android Device. I. Also, make sure to disable the device animations, as described in the [Espresso setup instructions](https://developer.android.com/training/testing/espresso/setup#set-up-environment).


## LICENSE

```
The MIT License (MIT)

Copyright (c) 2021 Igor Vilela

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```

