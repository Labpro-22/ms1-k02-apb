import java.io.File
import java.io.FileOutputStream
import java.util.Properties

val localPropsFile = file("local.properties")
if (!localPropsFile.exists()) {
    val androidHome = System.getenv("ANDROID_HOME")
        ?: System.getenv("ANDROID_SDK_ROOT")
        ?: run {
            // Try common Windows SDK location
            val windowsSdk = File("C:\\Users\\${System.getProperty("user.name")}\\AppData\\Local\\Android\\Sdk")
            if (windowsSdk.exists()) windowsSdk.absolutePath else null
        }

    if (androidHome != null) {
        val props = Properties()
        props.setProperty("sdk.dir", androidHome)
        FileOutputStream(localPropsFile).use { props.store(it, null) }
    }
}

plugins {
    id("com.android.application") version "8.7.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
    id("com.google.devtools.ksp") version "1.9.24-1.0.20" apply false
}
