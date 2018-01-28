package model;


public class Topology {

	private GFArray GFArray;
	
	private GIArray GIArray;
	
	private LinkArray LinkArray;
	
	public Topology(GFArray gfArray, GIArray giArray, LinkArray linkArray){
		this.setGFArray(gfArray);
		this.setGIArray(giArray);
		this.setLinkArray(linkArray);
	}

	public GFArray getGFArray() {
		return GFArray;
	}

	public void setGFArray(GFArray gFArray) {
		GFArray = gFArray;
	}

	public GIArray getGIArray() {
		return GIArray;
	}

	public void setGIArray(GIArray gIArray) {
		GIArray = gIArray;
	}

	public LinkArray getLinkArray() {
		return LinkArray;
	}

	public void setLinkArray(LinkArray linkArray) {
		LinkArray = linkArray;
	}

}
