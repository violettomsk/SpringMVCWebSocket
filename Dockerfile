FROM tomcat:latest
RUN rm -rf /usr/local/tomcat/webapps/*
COPY target/Demo.war /usr/local/tomcat/webapps/ROOT.war