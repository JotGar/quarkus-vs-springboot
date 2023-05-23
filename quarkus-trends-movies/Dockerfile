FROM amazoncorretto:17

ENV LANG=C.UTF-8 \
  LC_ALL=C.UTF-8

VOLUME /tmp

# We make four distinct layers so if there are application changes the library layers can be re-used
COPY target/quarkus-app/lib/ /lib/
COPY target/quarkus-app/quarkus-run.jar /app.jar
COPY target/quarkus-app/app/ /app/
COPY target/quarkus-app/quarkus/ /quarkus/

RUN bash -c 'touch /app.jar'
COPY docker/entrypoint.sh /entrypoint.sh
RUN chmod 777 /entrypoint.sh

ENTRYPOINT ["./entrypoint.sh"]
