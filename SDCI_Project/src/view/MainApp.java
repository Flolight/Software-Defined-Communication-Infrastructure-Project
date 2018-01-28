package view;

import java.io.IOException;

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

	
	public MainApp(){
	}

	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("SDCI mini project");
		
		initRootLayout();
		
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
	public BorderPane getRootLayout(){
		return rootLayout;
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
		//Load userOverview
		try {
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/GFOverview.fxml"));
			AnchorPane gfOverview = (AnchorPane) loader.load();
		
			//Set user overview int the Left of root layout
			rootLayout.setLeft(gfOverview);
		
			GFOverviewController controller = loader.getController();
			controller.setMainApp(this);
		
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*public void showConversationOverview(){
		//Load userOverview
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("../view/ConversationOverview.fxml"));
			AnchorPane conversationOverview = (AnchorPane) loader.load();
		
			//Set user overview int the center of root layout
			rootLayout.setCenter(conversationOverview);
		
			ConversationOverviewController controller = loader.getController();
			controller.setMainApp(this);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	

	public static void main(String[] args) {
		launch(args);
		
	}
}
