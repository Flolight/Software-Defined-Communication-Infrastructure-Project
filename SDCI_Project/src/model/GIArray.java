package model;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 */
public class GIArray {
    /**
     * The list of all final gateway known by the system since launched
     */
    private ArrayList<DataType_GI> GIs;

    /**
     * A singleton to be sure of unity of the Model instantiation
     *
     */
    private static class SingletonHolder{
        public static final GIArray instance = new GIArray();
    }

    /**
     * Here is how to access the Model from other classes
     * @return
     */
    public static GIArray getInstance() {
        return SingletonHolder.instance;
    }


    /**
     * Private constructor for the singleton
     */
    private GIArray(){
    }

    /**
     * Set the Status of GI
     * @param gi
     * @param status
     */
    public void updateGIStatus(DataType_GI gi, Status status){
    	gi.getContainer().setStatus(status);
    }
    
    /**
     * Add a GI
     *
     * @param gi, the GI to be added in the list
     */
    public void addGI(DataType_GI gi){
    	GIs.add(gi);
    }
    
    /**
     * Delete a GI
     *
     * @param gi, the GI to be deleted from the list
     */
    public void deleteGI(DataType_GI gi){
    	GIs.remove(gi);
    }
    
    /**
     * Returns a GI
     *
     * @param id, the GI Id in the list
     * @return The GI if it was found | null if not
     */
    public DataType_GI getGIInfo(int id){

        if(GIs.size()<id){
        	return null;
        }
        return GIs.get(id);
    }

    /**
     * return every GI
     *
     * @return an ArrayList of GI
     */
    public ArrayList<DataType_GI> getGIs() {
        return GIs;
    }
}
