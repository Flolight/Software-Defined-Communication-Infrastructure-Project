package model;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 */
public class LinkArray {
    /**
     * The list of all final gateway known by the system since launched
     */
    private ArrayList<DataType_Link> Links;

    /**
     * A singleton to be sure of unity of the Model instantiation
     *
     */
    private static class SingletonHolder{
        public static final LinkArray instance = new LinkArray();
    }

    /**
     * Here is how to access the Model from other classes
     * @return
     */
    public static LinkArray getInstance() {
        return SingletonHolder.instance;
    }


    /**
     * Private constructor for the singleton
     */
    private LinkArray(){
    }
    
    /**
     * Add a Link
     *
     * @param link, the Link to be added in the list
     */
    public void addLink(DataType_Link link){
    	Links.add(link);
    }
    
    /**
     * Delete a Link
     *
     * @param link, the Link to be deleted from the list
     */
    public void deleteLink(DataType_Link link){
    	Links.remove(link);
    }
    
    /**
     * Returns a Link
     *
     * @param id, the Link Id in the list
     * @return The Link if it was found | null if not
     */
    public DataType_Link getLinkInfo(int id){

        if(Links.size()<id){
        	return null;
        }
        return Links.get(id);
    }

    /**
     * return every Link
     *
     * @return an ArrayList of Link
     */
    public ArrayList<DataType_Link> getLinks() {
        return Links;
    }
}
