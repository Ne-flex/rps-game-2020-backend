FROM openjdk:15-alpine
WORKDIR /
ADD build/libs/rps-game-2020-backend.jar rps-game-2020-backend.jar
CMD ["java", "-jar", "rps-game-2020-backend.jar", "--port=$PORT"]