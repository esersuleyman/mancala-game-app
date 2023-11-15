FROM openjdk:17-alpine
ADD build/libs/mancala-game-app-1.0.0.jar mancala-game-app-1.0.0.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb-service:27017/mancalaDB","-jar","mancala-game-app-1.0.0.jar"]
EXPOSE 8080






