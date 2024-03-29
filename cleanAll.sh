#!/bin/bash

#Remove all running containers
docker rm $(docker ps -a -q)

#Remove all docker images
docker rmi -f $(docker images -q)

#Clean the mininet topology
sudo mn -c
