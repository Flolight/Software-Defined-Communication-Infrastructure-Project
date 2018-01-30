package model;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 */
public class GFArray {
    /**
     * The list of all final gateway known by the system since launched
     */
    private final ObservableList<DataType_GF> listGFs;

    public GFArray(){
    	listGFs = FXCollections.observableArrayList(new ArrayList<>());
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
    	listGFs.add(gf);
    }
    
    /**
     * Delete a GF
     *
     * @param gf, the GF to be deleted from the list
     */
    public void deleteGF(DataType_GF gf){
    	listGFs.remove(gf);
    }
    
    /**
     * Returns a GF
     *
     * @param id, the GF Id in the list
     * @return The GF if it was found | null if not
     */
    public DataType_GF getGFInfo(int id){

        if(listGFs.size()<id){
        	return null;
        }
        return listGFs.get(id);
    }

    /**
     * return every GF
     *
     * @return an ObservableList of GF
     */
    public ObservableList<DataType_GF> getGFs() {
        return listGFs;
    }
}
