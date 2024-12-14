FROM openjdk:latest
WORKDIR /app/practise
COPY ./target/code-gym-0.0.1-SNAPSHOT.jar /app/practise/code-gym.jar
ENV HELLO=Hello!
ENV BYE=Bye!
ENTRYPOINT ["java", "-cp", "code-gym.jar", "aq.gym.fun.env_var_reader.MainEnvironmentVariable"]
#CMD ["10"]