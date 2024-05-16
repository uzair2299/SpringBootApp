# Use an OpenJDK base image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the packaged Spring Boot application JAR file into the container

COPY target/*.jar ./springBootApp.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "springBootApp.jar"]



#What is the difference between "expose" and "publish" in Docker?
#If you specify neither EXPOSE nor -p, the service in the container will only be accessible from inside the container itself.
#If you EXPOSE a port, the service in the container is not accessible from outside Docker, but from inside other Docker containers. So this is good for inter-container communication.
#If you EXPOSE and -p a port, the service in the container is accessible from anywhere, even outside Docker.
#If you do -p, but do not EXPOSE, Docker does an implicit EXPOSE. This is because if a port is open to the public, it is automatically also open to other Docker containers. Hence -p includes EXPOSE. This is effectively same as 3)


#Continer commands
#docker ps
#docker ps -a

#docker run -d --name my-container my-node-app
#docker run -p 8081:8081 your-image-name
#docker start <container_id or container_name>
#docker start -i <container_id or container_name> >>java -jar /path/to/your/application.jar
#docker stop <container_id or container_name>
#docker restart <container_name_or_id
#docker rm <container_name_or_id>
#docker rm container1 container2 container3
#docker inspect <container_id or container_name>
#docker logs <container_id or container_name>
#docker logs -f <container_id or container_name> >> Monitor the logs continuously
#docker container prune >> remove all stopped containers from your system
#docker ps -a >> show list of all container including the stopped one
