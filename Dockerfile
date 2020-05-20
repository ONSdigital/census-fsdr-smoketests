FROM gradle:5.4.1-jdk11

RUN mkdir /opt/census-fsdr-smoke-tests
COPY . /opt/census-fsdr-smoke-tests

WORKDIR /opt/census-fsdr-smoke-tests
ENTRYPOINT [ "./gradlew", "--stacktrace",  "test" ]
