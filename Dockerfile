FROM openjdk:18
EXPOSE 8089
ADD target/DevOps_Project-4.0.jar yassinebaccouche-devops.jar
ENTRYPOINT ["java","-jar","yassinebaccouche-devops.jar"]