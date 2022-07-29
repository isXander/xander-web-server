FROM alpine as actions
RUN \
  apt-get update && \
  apt-get install git -y && \
  ssh-keyscan -t rsa github.com > ~/.ssh/known_hosts
ADD ./actions/xander-web-server.tar /src/api/

FROM openjdk:17
WORKDIR /src/api
COPY --from=actions /src/api/xander-web-server-* /src/api
ENTRYPOINT [ "sh", "/src/api/bin/xander-web-server" ]