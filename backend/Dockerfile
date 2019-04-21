FROM java:8-jre

ADD ./target/poc-backend-1.0.0.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/poc-backend-1.0.0.jar"]

EXPOSE 8080
