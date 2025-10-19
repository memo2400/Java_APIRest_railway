FROM eclipse-temurin:21-jdk as build

# copiamos nuestra app al directorio de trabajo
COPY . /app
WORKDIR /app

# descartamos los test y guardamos lo jar
RUN chmod +x mvnw
RUN ./mvnw package -DskipTests
RUN mv -f target/*.jar app.jar


# seteamos variable puerto de acuerdo al entorno
FROM eclipse-temurin:21-jre

ARG PORT
ENV PORT=${PORT}

# copiar el app.jar a la app compilada
COPY --from=build /app/app.jar .

RUN useradd runtime
USER runtime

ENTRYPOINT [ "java", "-Dserver.port=${PORT}", "-jar", "app.jar"]
