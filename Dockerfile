FROM alpine as actions
RUN \
  apk update && \
  apk add git findutils ssh && \
  mkdir /root/.ssh && chmod 0700 ~/.ssh && \
  ssh-keyscan -t rsa github.com > ~/.ssh/known_hosts
ADD ./actions/xander-web-server.tar /src/api/

FROM openjdk:17
WORKDIR /src/api
COPY --from=actions /src/api/xander-web-server-* /src/api
ENTRYPOINT [ "sh", "/src/api/bin/xander-web-server" ]
