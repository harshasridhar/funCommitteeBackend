FROM java:8
COPY committee-0.0.1-SNAPSHOT.jar backend.jar
COPY startServer.sh startServer.sh
EXPOSE 8081
ENTRYPOINT ["sh","startServer.sh"]