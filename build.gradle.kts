group = "com.merricklabs.hello"

plugins {
    kotlin("jvm") version "1.3.41"
    `maven-publish`
}

repositories {
    jcenter()
    mavenCentral()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/davidmerrick/github-packages-demo")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register("gpr") {
            from(components["java"])
        }
    }
}