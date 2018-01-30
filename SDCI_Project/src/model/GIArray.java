package model;

import java.util.ArrayList;

/**
 */
public class GIArray {
    /**
     * The list of all final gateway known by the system since launched
     */
    private final ArrayList<DataType_GI> listGIs;

    public GIArray(){
    	listGIs = new ArrayList<>();
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
    	listGIs.add(gi);
    }
    
    /**
     * Delete a GI
     *
     * @param gi, the GI to be deleted from the list
     */
    public void deleteGI(DataType_GI gi){
    	listGIs.remove(gi);
    }
    
    /**
     * Returns a GI
     *
     * @param id, the GI Id in the list
     * @return The GI if it was found | null if not
     */
    public DataType_GI getGIInfo(int id){

        if(listGIs.size()<id){
        	return null;
        }
        return listGIs.get(id);
    }

    /**
     * return every GI
     *
     * @return an ArrayList of GI
     */
    public ArrayList<DataType_GI> getGIs() {
        return listGIs;
    }
}
