FROM alpine as actions
ADD ./actions/xander-web-server.tar /src/api/

FROM openjdk:17
WORKDIR /src/api
COPY --from=actions /src/api/xander-web-server-* /src/api
ENTRYPOINT [ "sh", "/src/api/bin/xander-web-server" ]