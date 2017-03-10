FROM openjdk:8

RUN mkdir -p /usr/src/vuzoll-datasets-manager && mkdir -p /usr/app

COPY build/distributions/* /usr/src/vuzoll-datasets-manager/

RUN unzip /usr/src/vuzoll-datasets-manager/vuzoll-datasets-manager-*.zip -d /usr/app/ && ln -s /usr/app/vuzoll-datasets-manager-* /usr/app/vuzoll-datasets-manager

WORKDIR /usr/app/vuzoll-datasets-manager

ENTRYPOINT ["./bin/vuzoll-datasets-manager"]
CMD []
