FROM gradle:7.2.0-jdk17 as cache
ENV GRADLE_USER_HOME /home/gradle/cache_home
COPY build.gradle.kts /home/gradle/java-code/
WORKDIR /home/gradle/java-code
RUN gradle build -i --no-daemon || return 0

FROM gradle:7.2.0-jdk17 as runner
COPY --from=cache /home/gradle/cache_home /home/gradle/.gradle
COPY . /usr/src/java-code/
WORKDIR /usr/src/java-code
EXPOSE 8080
ENTRYPOINT ["gradle", "bootRun", "-i"]