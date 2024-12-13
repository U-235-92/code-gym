FROM openjdk:latest
WORKDIR /app/practise
COPY ./target/code-gym-0.0.1-SNAPSHOT.jar /app/practise/code-gym.jar
ENTRYPOINT ["java", "-cp", "code-gym.jar", "aq.gym.fun.pyramid.Main"]
CMD ["10"]