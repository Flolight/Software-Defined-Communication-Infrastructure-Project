package view;

import java.io.IOException;
import controller.Controller;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;
	private Controller controller;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("SDCI mini project");
		
		initRootLayout();
		initController();
		
		showSplashScreen();
	}
	
	public void initRootLayout(){
		try{
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			//Show the scene
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();

			primaryStage.setOnCloseRequest(e->{
				Platform.exit();
				System.out.println("Terminating ...");
				System.exit(1);
			});
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void initController() {
		controller = Controller.getInstance();
	}
	
	public BorderPane getRootLayout() {
		return rootLayout;
	}
	
	public Controller getController() {
		return controller;
	}
	
	
	public void showSplashScreen(){
		//Load userOverview
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/LoginWindow.fxml"));
			BorderPane loginWindow = (BorderPane) loader.load();

			//Set user overview in the center of root layout
			rootLayout.setCenter(loginWindow);

			LoginWindowController controller = loader.getController();
			controller.setMainApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showGFsOverview(){
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/GFOverview.fxml"));
			AnchorPane gfOverview = (AnchorPane) loader.load();
		
			//Set user overview int the Left of root layout
			rootLayout.setLeft(gfOverview);
		
			GFOverviewController gfcontroller = loader.getController();
			gfcontroller.setMainApp(this);
			gfcontroller.bindGFArray(controller.getTopologyCache().getGFArray().getGFs());
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showGIsOverview(){
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/GIOverview.fxml"));
			AnchorPane giOverview = (AnchorPane) loader.load();
		
			//Set user overview int the Left of root layout
			rootLayout.setRight(giOverview);
		
			GIOverviewController gicontroller = loader.getController();
			gicontroller.setMainApp(this);
			gicontroller.bindGIArray(controller.getTopologyCache().getGIArray().getGIs());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showLinksOverview(){
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/LinkOverview.fxml"));
			AnchorPane linkOverview = (AnchorPane) loader.load();
		
			//Set user overview int the Left of root layout
			rootLayout.setBottom(linkOverview);
		
			LinkOverviewController linkcontroller = loader.getController();
			linkcontroller.setMainApp(this);
			linkcontroller.bindLinkArray(controller.getTopologyCache().getLinkArray().getLinks());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
