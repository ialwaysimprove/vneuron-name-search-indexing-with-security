FROM maven:3.6.3-openjdk-15 AS builder
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean package -DskipTests
RUN java -Djarmode=layertools -jar /workspace/target/*.jar extract

FROM maven:3.6.3-openjdk-15
COPY --from=builder /workspace/dependencies/ ./
RUN true
COPY --from=builder /workspace/spring-boot-loader/ ./
RUN true
COPY --from=builder /workspace/internal-dependencies/ ./
RUN true
COPY --from=builder /workspace/snapshot-dependencies/ ./
RUN true
COPY --from=builder /workspace/application/ ./
RUN true
EXPOSE 9000
CMD ["java", "org.springframework.boot.loader.JarLauncher"]
