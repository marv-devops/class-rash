# Use a Java 17 JDK base image from the OpenJDK project.
# This matches the Java version specified in your application's pom.xml.
FROM openjdk:17-jdk

# Expose port 8081 to the outside world.
# This is the port your Spring Boot application will run on.
EXPOSE 9090

# The path to the compiled JAR file in the target directory of your Maven project.
# The JAR filename is constructed based on the artifactId and version as specified in your pom.xml.
ARG JAR_FILE=target/flightbookingservice-0.0.1-SNAPSHOT.jar

# Copy the compiled JAR file into the Docker image as 'app.jar'.
# This prepares the JAR to be run inside the container.
ADD ${JAR_FILE} app.jar

# Specify the command to run your Spring Boot application.
# This command executes the JAR file, starting your application.
ENTRYPOINT ["java","-jar","/app.jar"]
