package model;

public class DataType_Link {
	
	/**
	 * The intermediate gateway
	 */
	private DataType_GI gi;
	
	/**
	 * The final gateway
	 */
	private DataType_GF gf;
	
	/**
	 * A basic DataType_Link constructor
	 */
	public DataType_Link(DataType_GI gi, DataType_GF gf){
		this.setGf(gf);
		this.setGi(gi);
	}

	public DataType_GI getGi() {
		return gi;
	}

	public void setGi(DataType_GI gi) {
		this.gi = gi;
	}

	public DataType_GF getGf() {
		return gf;
	}

	public void setGf(DataType_GF gf) {
		this.gf = gf;
	}
	
	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	        return false;
	    }
	    if (!DataType_Link.class.isAssignableFrom(obj.getClass())) {
	        return false;
	    }
	    final DataType_Link other = (DataType_Link) obj;
	    if ((this.getGi() == null) ? (other.getGi() != null) : !this.getGi().getName().equals(other.getGi().getName())) {
	        return false;
	    }
	    if ((this.getGf() == null) ? (other.getGf() != null) : !this.getGf().getName().equals(other.getGf().getName())) {
	        return false;
	    }
	    return true;
	}

}
