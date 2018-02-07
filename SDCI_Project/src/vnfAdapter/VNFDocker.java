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

import sdnAdapter.DeviceRest;

public class VNFDocker {
	
	public static final int DC_PORT = 2375;
	private static final String ctrldockerurl = "172.17.0.1"+":"+DC_PORT;
	private static final String img = "sdciproject/gi";
	
	private DockerClient dockerClient;

	public VNFDocker() {
		DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
				.withDockerHost("tcp://" + ctrldockerurl).withDockerTlsVerify(false).build();
		this.dockerClient = DockerClientBuilder.getInstance(config).build();
	}
	
	public String createContainer(int port, int bindport) {
		ExposedPort http = ExposedPort.tcp(port);
		Ports portBinding = new Ports();
		portBinding.bind(http, Binding.bindPort(bindport));

		CreateContainerResponse container = dockerClient.createContainerCmd(img).
				withExposedPorts(http).withPortBindings(portBinding).exec();
		System.out.println("[VNFDocker]create Id : " + container.getId());
		return container.getId();
	}

	public void createContainer(int port, int bindport, String scriptPath) throws IOException {
		ExposedPort http = ExposedPort.tcp(port);
		Ports portBinding = new Ports();
		portBinding.bind(http, Binding.bindPort(bindport));

		CreateContainerResponse container = dockerClient.createContainerCmd(img). withExposedPorts(http).withPortBindings(portBinding).
				withCmd("sh", "/home/" + Paths.get(scriptPath).getFileName().toString()).exec();
	    dockerClient.copyArchiveToContainerCmd(container.getId()).withHostResource(scriptPath).withRemotePath("/home").exec();

		dockerClient.startContainerCmd(container.getId()).exec();
	}
	
	public void startContainer(String containerId) throws IOException {
		dockerClient.startContainerCmd(containerId).exec();
	}
	
	public void stopContainer(String containerId) throws IOException {
		dockerClient.stopContainerCmd(containerId).exec();
	}
	
	public void removeContainer(String containerId) throws IOException {
		dockerClient.removeContainerCmd(containerId).exec();
	}
	
	public InspectContainerResponse getInfoContainer(String containerId) throws IOException {
		InspectContainerResponse info = dockerClient.inspectContainerCmd(containerId).exec();
		System.out.println("[VNFDocker]Info : " + info.toString());
		return info;
	}
	
	public List<Container> listContainers(boolean showall) throws IOException {
		List<Container> containers = dockerClient.listContainersCmd().withShowAll(showall) .exec();
		System.out.println("[VNFDocker]Cts Numb : " + containers.size());
		return containers;
	}
	
	public void closeClient() throws IOException {
		dockerClient.close();
	}
	
	// test
	public static void main(String[] args) {
		VNFDocker example = new VNFDocker();
		try {
			System.out.println("begin");
			String containerId = example.createContainer(2375, 28080);
			System.out.println("end");
			List<Container> list = example.listContainers(true);
			list.forEach((e)-> {System.out.println("id:"+e.getId());});
			example.startContainer(containerId);
			example.getInfoContainer(containerId);
			example.stopContainer(containerId);
			example.removeContainer(containerId);
			example.closeClient();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
