FROM java:8

RUN mkdir -p /opt
RUN mkdir -p /tmp
ADD target/sanctions-list-backend-swarm.jar /opt

EXPOSE 8080

CMD ["java", "-jar", "/opt/sanctions-list-backend-swarm.jar", "-Djava.net.preferIPv4Stack=true"]
