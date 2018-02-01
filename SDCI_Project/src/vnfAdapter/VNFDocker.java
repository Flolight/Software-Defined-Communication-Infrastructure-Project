package vnfAdapter;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.api.model.Ports.Binding;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;

public class VNFDocker {

	public VNFDocker() {
		// TODO Auto-generated constructor stub
	}
	
	public void createContainer(String ctrldockerurl, String img, int port, int bindport) throws IOException {

		ExposedPort http = ExposedPort.tcp(port);
		Ports portBinding = new Ports();
		portBinding.bind(http, Binding.bindPort(bindport));

		DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
		.withDockerHost("tcp://" + ctrldockerurl).withDockerTlsVerify(false).build();
		DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

		CreateContainerResponse container = dockerClient.createContainerCmd(img). withExposedPorts(http).withPortBindings(portBinding).exec();

		dockerClient.close();
	}
	
	public void startContainer(String ctrldockerurl, String containerId) throws IOException {

		DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
		.withDockerHost("tcp://" + ctrldockerurl).withDockerTlsVerify(false).build();
		DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

		dockerClient.startContainerCmd(containerId).exec();

		dockerClient.close();
	}
	
	public void stopContainer(String ctrldockerurl, String containerId) throws IOException {

		DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
		.withDockerHost("tcp://" + ctrldockerurl).withDockerTlsVerify(false).build();
		DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

		dockerClient.stopContainerCmd(containerId).exec();
		dockerClient.close();
	}
	
	public void removeContainer(String ctrldockerurl, String containerId) throws IOException {

		DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
		.withDockerHost("tcp://" + ctrldockerurl).withDockerTlsVerify(false).build();
		DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

		dockerClient.removeContainerCmd(containerId).exec();

		dockerClient.close();
	}
	
	public void getInfoContainer(String ctrldockerurl, String containerId) throws IOException {

		DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
		.withDockerHost("tcp://" + ctrldockerurl).withDockerTlsVerify(false).build();
		DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

		InspectContainerResponse info = dockerClient.inspectContainerCmd(containerId)
		.exec();

		System.out.println("info : " + info.toString());

		dockerClient.close();
	}
	
	public void listContainers(String ctrldockerurl, boolean showall) throws IOException {

		DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
		.withDockerHost("tcp://" + ctrldockerurl).withDockerTlsVerify(false).build();
		DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

		List<Container> containers = dockerClient.listContainersCmd().withShowAll(showall) .exec();
		System.out.println("Cts Numb : " + containers.size());
		for (Container ct : containers) {System.out.println("ct id : " + ct.getId());}

		dockerClient.close();
	}
	
	public void createContainer(String ctrldockerurl, String img, int port, int bindport, String scriptPath) throws IOException {

		ExposedPort http = ExposedPort.tcp(port);
		Ports portBinding = new Ports();
		portBinding.bind(http, Binding.bindPort(bindport));

		DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
		.withDockerHost("tcp://" + ctrldockerurl).withDockerTlsVerify(false).build();
		DockerClient dockerClient = DockerClientBuilder.getInstance(config).build();

		CreateContainerResponse container = dockerClient.createContainerCmd(img). withExposedPorts(http).withPortBindings(portBinding).withCmd("sh", "/home/" + Paths.get(scriptPath).getFileName().toString()).exec();
		    dockerClient.copyArchiveToContainerCmd(container.getId()).withHostResource(scriptPath).withRemotePath("/home").exec();

		dockerClient.startContainerCmd(container.getId()).exec();
		dockerClient.close();
		}

}
