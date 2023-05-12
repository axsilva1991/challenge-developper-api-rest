import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.6"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	id("org.owasp.dependencycheck") version "8.1.2" apply false
	jacoco
}

group = "br.com.bank"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

allprojects {
	apply(plugin = "org.owasp.dependencycheck")
}

configure<org.owasp.dependencycheck.gradle.extension.DependencyCheckExtension> {
	format = org.owasp.dependencycheck.reporting.ReportGenerator.Format.ALL.toString()
}

extra["springCloudVersion"] = "2021.0.3"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springdoc:springdoc-openapi-ui:1.6.4")
	runtimeOnly("com.h2database:h2:2.1.214")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("org.owasp:dependency-check-gradle:8.2.1")


}

dependencyManagement {
	imports {
		mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
	}
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.jacocoTestReport {
	reports {
		html.required
	}
}
