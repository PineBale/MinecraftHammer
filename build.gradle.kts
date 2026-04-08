plugins {
    `java-library`
    `maven-publish`
    jacoco
}

group = "dev.pinebale.minecraft.nl.rutgerkok"
version = "0.1-SNAPSHOT"

dependencies {
    implementation("com.google.guava:guava:17.0")
    implementation("com.googlecode.json-simple:json-simple:1.1.1")

    testImplementation(platform("org.junit:junit-bom:${project.findProperty("junit_version")}"))
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.platform:junit-platform-launcher")
    testImplementation("commons-io:commons-io:2.20.0")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withSourcesJar()
    withJavadocJar()
}

tasks {
    named<Javadoc>("javadoc") {
        options.encoding = "UTF-8"

        (options as? StandardJavadocDocletOptions)?.apply {
            links(
                "https://guava.dev/releases/17.0/api/docs/"
            )
        }
    }

    named<Jar>("jar") {
        manifest {
            attributes["Built-By"] = ""
        }
    }

    test {
        useJUnitPlatform()
        finalizedBy(jacocoTestReport)
    }

    jacocoTestReport {
        dependsOn(test)
        reports {
            xml.required = true
            html.required = true
            csv.required = true
        }
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
