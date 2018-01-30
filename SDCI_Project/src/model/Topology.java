package model;


public class Topology {
	
	private final DataType_Server server;

	private final GFArray GFArray;
	
	private final GIArray GIArray;
	
	private final LinkArray LinkArray;
	
	public Topology(DataType_Server server, GFArray gfArray, GIArray giArray, LinkArray linkArray){
		this.server = server;
		this.GFArray = gfArray;
		this.GIArray = giArray;
		this.LinkArray = linkArray;
	}

	public DataType_Server getServer() {
		return server;
	}

	public GFArray getGFArray() {
		return GFArray;
	}

	public GIArray getGIArray() {
		return GIArray;
	}

	public LinkArray getLinkArray() {
		return LinkArray;
	}

}
