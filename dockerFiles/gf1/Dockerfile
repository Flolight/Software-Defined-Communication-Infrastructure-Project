#GF
FROM openjdk:7

RUN apt-get update \
&&  mkdir /workingdir/ \
&&  mkdir /workingdir/mn-cse/

COPY mn-cse /workingdir/mn-cse/
COPY IoTDevice.jar /workingdir/IoTDevice.jar
COPY device.cfg /workingdir/device.cfg

WORKDIR /workingdir/mn-cse/

#
#CMD ./start.sh
