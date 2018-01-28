package model;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 */
public class GFArray {
    /**
     * The list of all final gateway known by the system since launched
     */
    private ArrayList<DataType_GF> GFs;

    /**
     * A singleton to be sure of unity of the Model instantiation
     *
     */
    private static class SingletonHolder{
        public static final GFArray instance = new GFArray();
    }

    /**
     * Here is how to access the Model from other classes
     * @return
     */
    public static GFArray getInstance() {
        return SingletonHolder.instance;
    }


    /**
     * Private constructor for the singleton
     */
    private GFArray(){
    	try {
			this.createSampleData();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * Set the sample Data for GFArray class
     * @throws UnknownHostException 
     */
    public void createSampleData() throws UnknownHostException{
    	GFs.add(new DataType_GF(new Address(InetAddress.getByName("10.0.0.1"), 80), Status.Working, "GF Zone 1"));
    	GFs.add(new DataType_GF(new Address(InetAddress.getByName("10.0.0.2"), 80), Status.Working, "GF Zone 2"));
    	GFs.add(new DataType_GF(new Address(InetAddress.getByName("10.0.0.3"), 80), Status.Working, "GF Zone 3"));
    }

    /**
     * Set the Status of GF
     * @param gf
     * @param status
     */
    public void updateGFStatus(DataType_GF gf, Status status){
    	gf.setStatus(status);
    }
    
    /**
     * Add a GF
     *
     * @param gf, the GF to be added in the list
     */
    public void addGF(DataType_GF gf){
    	GFs.add(gf);
    }
    
    /**
     * Delete a GF
     *
     * @param gf, the GF to be deleted from the list
     */
    public void deleteGF(DataType_GF gf){
    	GFs.remove(gf);
    }
    
    /**
     * Returns a GF
     *
     * @param id, the GF Id in the list
     * @return The GF if it was found | null if not
     */
    public DataType_GF getGFInfo(int id){

        if(GFs.size()<id){
        	return null;
        }
        return GFs.get(id);
    }

    /**
     * return every GF
     *
     * @return an ArrayList of GF
     */
    public ArrayList<DataType_GF> getGFs() {
        return GFs;
    }
}
