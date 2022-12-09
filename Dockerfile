FROM alpine:latest
LABEL maintainer info@codeschluss.de
COPY / /tmp/wooportal.server
RUN \
  #
  # packages
  apk --no-cache add \
  openjdk17-jre && \
  apk --no-cache --virtual build add \
  maven \
  openjdk17 && \
  #
  # wooportal.server
  cd /tmp/wooportal.server && \
  mvn -B install -DskipTests=true \
  -Dorg.slf4j.simpleLogger.log.org.apache.maven.cli.transfer.Slf4jMavenTransferListener=warn && \
  mkdir -p /data/media && \
  mkdir -p /usr/share/webapps/wooportal.server && \
  mv /tmp/wooportal.server/target/*.jar /usr/share/webapps/wooportal.server/server.jar && \
  #
  # cleanup
  apk del --purge build && \
  find /root /tmp -mindepth 1 -delete
#
# runtime
WORKDIR /usr/share/webapps/wooportal.server
CMD java \
  -XX:+ExitOnOutOfMemoryError \
  -Dserver.port=80 \
  -jar server.jar
EXPOSE 80