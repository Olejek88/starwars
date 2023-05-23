# Star Wars project
TODO
<p>What're inside?
<br>- Jetpack Compose
<br>- Ktor Framework
<br>- Material3
<br>- Coil
</p>

# Set up the environment

To work with this app, you need the following:
<ul>
<li>A machine running a recent version of macOS</li>
<li>Xcode</li>
<li>Android Studio</li>
<li>The Kotlin Multiplatform Mobile plugin</li>
<li>The CocoaPods dependency manager</li>
</ul>

##Check your environment
To make sure everything works as expected, install and run the KDoctor tool (KDoctor works on macOS only).
In the Android Studio terminal or your command-line tool, run the following command to install the tool using Homebrew:
```
brew install kdoctor
```
If you don't have Homebrew yet, install it or see the KDoctor README for other ways to install it.
After the installation is completed, call KDoctor in the console:
```
kdoctor
```
https://kotlinlang.org/docs/multiplatform-mobile-setup.html#check-your-environment

You should:
1) clone 

##App structure
Star Wars App project includes 3 modules:
###shared
This is a Kotlin module that contains the logic common for both Android and iOS applications, that is, the code you share between platforms.
This shared module is also where you’ll write your Compose Multiplatform code. In shared/src/commonMain/kotlin/App.kt, you can find the shared root @Composable function for your app.
It uses Gradle as the build system. You can add dependencies and change settings in shared/build.gradle.kts. The shared module builds into an Android library and an iOS framework.
###androidApp
This is a Kotlin module that builds into an Android application. It uses Gradle as the build system. The androidApp module depends on and uses the shared module as a regular Android library.
###iosApp
This is an Xcode project that builds into an iOS application. It depends on and uses the shared module as a CocoaPods dependency.

##Run the application
###Android
To run your application on an Android emulator:
<br/>Ensure you have an Android virtual device available. Otherwise, create one.
<br/>In the list of run configurations, select androidApp.
<br/>Choose your virtual device and click Run
###iOS
TODO

# Troubleshooting
TODO