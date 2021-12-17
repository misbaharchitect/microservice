FROM openjdk:11.0.10-jre-slim
COPY docker-db-demo-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
ENTRYPOINT ["/wait-for-it.sh", "db:5432", "--timeout=20", "--", "java","-jar","/app.jar"]