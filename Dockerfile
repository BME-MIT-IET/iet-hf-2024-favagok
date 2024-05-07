FROM amazoncorretto:18.0.2-alpine

WORKDIR /app

COPY /out/sivatagi_vizhalozat.jar /app/sivatagi_vizhalozat.jar

CMD ["java", "-jar", "sivatagi_vizhalozat.jar"]