#GI
FROM openjdk:alpine

RUN apk update \
&&  mkdir /workingdir/ \
&&  mkdir /workingdir/mn-cse/

COPY mn-cse /workingdir/mn-cse/

WORKDIR /workingdir/mn-cse/

#Uncomment inorder to launch the gi program after container creation
#CMD ./start.sh
