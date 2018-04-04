# Software Defined Communication Infrastructure-Project

## Getting Started

### Conception

To get more informations about the conception process and the implementation and conception choices, please check the [Miscellaneous](Miscellaneous) and [design](design) folders.
### Cleanning the project

Start the controller going to the SDNController directory and run :
```
java -jar myTarget/sdncontroller.jar
```
Launching the [cleanAll.sh](cleanAll.sh) script with root privileges will clean all the environment (docker containers, images and mininet topology).

### Creating the mininet topology
In order to create the mininet topology, change the sdncontroller address in the [buildTopo.sh](buildTopo.sh) with the one presented by the enp0sx interface of your machine.
Then just run the [buildTopo.sh](buildTopo.sh) script.
### Start the different instances
#### The application server
```
sudo docker exec mn.appserver sh -c "cd /workingdir/in-cse && ./start.sh"
```
#### The first gateway (GI)
```
sudo docker exec mn.gi sh -c "cd /workingdir/mn-cse && ./start.sh"
```
#### The final gateways (GFx)
```
sudo docker exec mn.gfx sh -c "cd /workingdir/mn-cse && ./start.sh"
```
#### The client application
```
sudo docker exec -it mn.appserver sh -c "cd /workingdir && java -jar IoTApp1.jar"
```
#### The IoTDevice
```
sudo docker exec -it mn.gf1 sh -c "cd /workingdir && java -jar IoTDevice.jar"
```
This last program is not currently working due to a connectivity error...

#### The datacenter
```
docker run --priviledged -h h1 --name=mininet-h1 -ti  --net='none' ubuntu /bin/bash
```
### Launch the Java program

1. Open the project with Eclipse (SDCI_Project)
1. Install javafx
  1. Install e(fx)clipse
  1. In command line do : ``` sudo apt-install openjfx ```
  1. Add ```/usr/lib/jvm/java-8-openjdk-amd64/jre/lib/ext/jfxrt.jar``` in the project classpath
1. Replace ``` sdnAdapter/DeviceRest.java/LOCAL_IPV4 ``` with the host machine ip (can be found using ifconfig). By default, the coded address is 10.0.2.15
1. Comment line 66 in the file ``` vnfAdapter/vnfAdapter.java ``` if the tcp connection is not possible (the line is: ```vnfDocker.createContainer(VNFDocker.DC_PORT, countBindPort);```)
1. Launch ```view/MainApp.java```

### View the conception shemas
Open [design/uml_step1.mdj](design/uml_step1.mdj) with starUML.

# Useful commands

### Build a docker image from a Dockerfile
```
sudo docker build -t imageName DockerfileLocation
```
### Run a docker container
```
sudo docker run -ti imageName sh
```
## Various details
### Share folder creation
- Create a directory on the OS
- Add it to Virtualbox share folders
- Launch the virtual machine
- Create a "share" folder in your home directory
- Launch the following line :
```
Sudo mount -t vboxsf FileName /home/sdnvm/share
```
- Check for the access rights
- Add the following lines into /etc/fstab
```
#share folder
FileName /home/sdnvm/share vboxsf defaults 0 0
```
## Deployment

Add additional notes about how to deploy this on a live system


## Authors

* **Rama Desplats** -  [ramadesplats](https://github.com/ramadesplats)
* **Yuxiao Mao** -  [yuxiaomao](https://github.com/yuxiaomao)
* **Florian Clanet** -  [Flolight](https://github.com/flolight)

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
