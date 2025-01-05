/*
 * Copyright (c) [2024] [Murphy]
 * [Stock] is licensed under Mulan PubL v2.
 * You can use this software according to the terms and conditions of the Mulan PubL v2.
 * You may obtain a copy of Mulan PubL v2 at:
 *          http://license.coscl.org.cn/MulanPubL-2.0
 * THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
 * EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
 * MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
 * See the Mulan PubL v2 for more details.
 */

plugins {
    java
    id("io.freefair.lombok") version "8.11"
    `maven-publish`
}

group = "tech.forethought"
version = "1.1-SNAPSHOT"

repositories {
    maven {
        url = uri(System.getenv("MAVEN_URL"))
        credentials {
            username = System.getenv("MAVEN_USERNAME")
            password = System.getenv("MAVEN_PASSWORD")
        }
    }
    mavenCentral()
}

dependencies {
    implementation(platform("org.noear:solon-parent:3.0.4.1"))
    /* web */
    implementation("org.noear:solon-web")
    /* data */
    implementation("com.zaxxer:HikariCP:6.2.1")
    implementation("org.noear:wood-solon-plugin")
    implementation("org.postgresql:postgresql:42.7.4")
    /* other */
    implementation("org.noear:nami-channel-http")
    implementation("org.noear:nami.coder.snack3")
    implementation("org.noear:solon.logging.logback")
    implementation("org.noear:solon.scheduling.quartz")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.compilerArgs.add("-parameters")
}

tasks.withType<ProcessResources> {
    filesMatching("app.yml") {
        expand(
            "SERVER_PORT" to System.getenv("SERVER_PORT"),
            "JDBC_URL" to System.getenv("JDBC_URL"),
            "DB_USERNAME" to System.getenv("DB_USERNAME"),
            "DB_PASSWORD" to System.getenv("DB_PASSWORD"),
            "MAIRUI_LICENCE" to System.getenv("MAIRUI_LICENCE")
        )
    }
}

tasks.jar {
    archiveBaseName = "stock"
    manifest.attributes["Main-Class"] = "tech.forethought.stock.Main"
    dependsOn(configurations.runtimeClasspath)
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    from(sourceSets.main.get().output)
    from("LICENSE") { into("META-INF") }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "tech.forethought"
            artifactId = "stock"
            version = "1.1-SNAPSHOT"
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri(System.getenv("MAVEN_URL"))
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
        }
    }
}