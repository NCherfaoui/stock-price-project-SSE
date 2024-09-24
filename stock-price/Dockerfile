# Utiliser une image de base Java
FROM openjdk:17-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR généré dans l'image Docker
COPY build/libs/*.jar app.jar

# Exposer le port 8080
EXPOSE 8080

# Démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
