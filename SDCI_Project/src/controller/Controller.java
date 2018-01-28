package controller;

/**
 *
 * Controller class for the Controller of the MVC pattern
 *
 */

public class Controller {

    private static class SingletonHolder{
        public static final Controller instance = new Controller();
    }
    public static Controller getInstance() {
        return SingletonHolder.instance;
    }

    private Controller(){

    }

}
