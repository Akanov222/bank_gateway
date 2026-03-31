#Dockerfile
# Этап 1: сборка
FROM maven:4.0.0-openjdk-17 AS build
COPY src /src
COPY pom.xml /
RUN mvn -f /pom.xml clean packege -DskipTests

# ЭТАП 2: Упаковка (Tomcat)
FROM tomcat:11.0.7-jdk17-openjdk-slim
# Удаляем дефолтные приложения Tomcat для безопасности
RUN rm -rf /c/tomcat/apache-tomcat-11.0.7/webapps/*
# Копируем наш скомпилированный war из первого этапа
#в папку автодеплоя Tomcat и переименовываем в ROOT.war,
# чтобы приложение открывалось по адресу / (а не /bank_gateway)
COPY --from=build /target/*.war /c/tomcat/apache-tomcat-11.0.7/webapps/ROOT.war

EXPOSE 8080
ENTRYPOINT ["catalina.sh", "run"]
