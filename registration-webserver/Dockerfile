#FROM pietvandongen/docker-tomcat-development:latest
FROM tomcat:8.5
MAINTAINER Nethunder <13122535350@163.com>

ENV TZ=Asia/Shanghai JAVA_OPTS="$JAVA_OPTS -Djava.security.egd=file:/dev/./urandom"
# create mount point for volume with application
#COPY sources.list /etc/apt/sources.list
COPY ROOT.war $CATALINA_HOME/webapps/
RUN rm -rf $CATALINA_HOME/webapps/ROOT
#        && apt-get -y update \
#        && apt-get install -y vim less \
