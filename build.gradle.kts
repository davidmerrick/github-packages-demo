plugins {
    kotlin("jvm") version "1.3.41"
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    build {
        dependsOn(buildJar)
    }
}

val buildJar by tasks.creating(Jar::class) {
    from(tasks.compileKotlin)
}

publishing {
    publications {
        create<MavenPublication>("default") {
            artifact(buildJar)
        }
    }

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
}