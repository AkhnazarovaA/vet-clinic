FROM openjdk:17-oracle
WORKDIR /alabs
COPY "/target/vet-clinic-0.0.1.jar" "vet-clinic.jar"
ENTRYPOINT ["java", "-jar", "vet-clinic.jar"]