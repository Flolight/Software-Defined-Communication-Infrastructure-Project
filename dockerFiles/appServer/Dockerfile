#appserver
FROM openjdk:7

RUN apt-get update \
&&  mkdir /workingdir/ \
&&  mkdir /workingdir/in-cse/

COPY in-cse /workingdir/in-cse/
COPY IoTApp1.jar /workingdir/IoTApp1.jar

WORKDIR /workingdir/in-cse/

#Uncomment inorder to launch the appServer program after container creation
#CMD ./start.sh
