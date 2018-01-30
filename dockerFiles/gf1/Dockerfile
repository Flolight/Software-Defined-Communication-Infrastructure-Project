#GF
#FROM openjdk:7

#RUN apt-get update \
#&&  mkdir /workingdir/ \
#&&  mkdir /workingdir/mn-cse/

#COPY mn-cse /workingdir/mn-cse/
#COPY IoTDevice.jar /workingdir/IoTDevice.jar
#COPY device.cfg /workingdir/device.cfg

#WORKDIR /workingdir/mn-cse/

#
#CMD ./start.sh


FROM ubuntu:trusty

RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y  software-properties-common && \
    add-apt-repository ppa:webupd8team/java -y && \
    apt-get update && \
    echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y oracle-java8-installer && \
    apt-get clean \
&&  mkdir /workingdir/ \
&&  mkdir /workingdir/mn-cse/

COPY mn-cse /workingdir/mn-cse/
COPY IoTDevice.jar /workingdir/IoTDevice.jar
COPY device.cfg /workingdir/device.cfg
