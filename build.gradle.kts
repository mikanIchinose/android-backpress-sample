import io.gitlab.arturbosch.detekt.Detekt

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.spotless)
}

val spotlessPluginId = libs.plugins.spotless.get().pluginId

allprojects {
    apply(plugin = spotlessPluginId)

    // フォーマッター
    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("**/build/**/*.kt", "/**/*Test.kt")
            ktfmt().googleStyle()
            trimTrailingWhitespace()
            endWithNewline()
        }
    }
}

val detektPluginId = libs.plugins.detekt.get().pluginId

subprojects {
    apply(plugin = detektPluginId)

    // 静的解析
    detekt {
        config.setFrom(rootProject.file("config/detekt/detekt.yml"))
        allRules = true
        buildUponDefaultConfig = true
    }

    tasks.withType<Detekt>().configureEach {
        reports {
            xml.required.set(true)
            html.required.set(true)
            txt.required.set(false)
            sarif.required.set(false)
            md.required.set(true)
        }
    }
}
