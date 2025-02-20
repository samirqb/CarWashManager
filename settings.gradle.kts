pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Car Wash Manager"
include(":app")
include(":caja")
include(":helpers")
include(":personas")
include(":vehiculos")
include(":ofertas")
include(":ventas")
include(":transacciones")
include(":rrhh")
include(":pagos")
