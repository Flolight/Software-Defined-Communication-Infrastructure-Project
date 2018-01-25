#GF
FROM openjdk:alpine

RUN apk update \
&&  mkdir /workingdir/ \
&&  mkdir /workingdir/mn-cse/

COPY mn-cse /workingdir/mn-cse/

WORKDIR /workingdir/mn-cse/

CMD ./start.sh
