# Software Defined Communication Infrastructure-Project

Project description

## Getting Started
### Cleanning the project
Launching the [cleanAll.sh](cleanAll.sh) script will clean all the environment (docker containers, images and mininet topology).

### Creating the mininet topology
In order to create the mininet topology, just run the [buildTopo.sh](buildTopo.sh) script.

### Prerequisites

What things you need to install the software and how to install them

### Installing

# Useful commands

### Build a docker image from a Dockerfile
```
sudo docker build -t imageName destination
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

## Built With



## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.


## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

