#appserver
FROM openjdk:alpine

RUN apk update \
&&  mkdir /workingdir/ \
&&  mkdir /workingdir/in-cse/

COPY in-cse /workingdir/in-cse/

WORKDIR /workingdir/in-cse/

CMD ./start.sh
