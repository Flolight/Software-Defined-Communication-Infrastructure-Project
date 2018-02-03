package model;


import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 */
public class LinkArray {
    /**
     * The list of all final gateway known by the system since launched
     */
    private final ObservableList<DataType_Link> listLinks;

    public LinkArray(){
    	listLinks = FXCollections.observableArrayList(new ArrayList<>());
    }
    
    /**
     * Add a Link
     *
     * @param link, the Link to be added in the list
     */
    public void addLink(DataType_Link link){
    	listLinks.add(link);
    }
    
    /**
     * Delete a Link
     *
     * @param link, the Link to be deleted from the list
     */
    public void deleteLink(DataType_Link link){
    	listLinks.remove(link);
    }
    
    /**
     * Returns a Link
     *
     * @param id, the Link Id in the list
     * @return The Link if it was found | null if not
     */
    public DataType_Link getLinkInfo(int id){

        if(listLinks.size()<id){
        	return null;
        }
        return listLinks.get(id);
    }

    /**
     * return every Link
     *
     * @return an ObservableList of Link
     */
    public ObservableList<DataType_Link> getLinks() {
        return listLinks;
    }
    
    public boolean IsUniqueLink (DataType_Link link){
    	boolean found = false;
    	for (DataType_Link l: this.listLinks) {
		    if (l.equals(link)) {
		    	found = true;
		    }
		};
		return found;
    }
}
