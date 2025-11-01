# ---- Build ----
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .

# Fix permissions + CRLF -> LF, puis build
RUN chmod +x mvnw && sed -i 's/\r$//' mvnw && \
    ./mvnw -q -DskipTests package

# ---- Run ----
FROM eclipse-temurin:21-jre
ENV JAVA_OPTS="-XX:MaxRAMPercentage=75 -XX:+UseSerialGC -XX:+UseStringDeduplication"
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["sh","-c","java $JAVA_OPTS -Dserver.port=${PORT:-8080} -jar app.jar"]
