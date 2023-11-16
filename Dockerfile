FROM openjdk:17
ENV TZ=Asia/Seoul
COPY build/libs/Equus-Feed-0.0.1-SNAPSHOT.jar app.jar

ARG CLOUD_CONFIG_IMPORT_URL
ENV CLOUD_CONFIG_IMPORT_URL=$CLOUD_CONFIG_IMPORT_URL

ENTRYPOINT ["java","-javaagent:/home/ec2-user/dd-java-agent.jar", "-Ddd.agent.host=localhost", "-Ddd.profiling.enabled=true","-XX:FlightRecorderOptions=stackdepth=256", "-Ddd.logs.injection=true", "-Ddd.service=discovery-api", "-Ddd.env=prod", "-Dspring.profiles.active=production", "-jar", "/app.jar"]

