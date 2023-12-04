plugins {
    id("java")
    id("application")
}


group = "cs370.cutlist_project"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {

    compileOnly("org.projectlombok:lombok:1.18.28")
    annotationProcessor("org.projectlombok:lombok:1.18.28")

    testCompileOnly("org.projectlombok:lombok:1.18.28")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.28")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")


}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("cs370.cutlist_project.Main")
    applicationDistribution.from("src/main/resources") {
        include("*")
    }
}

