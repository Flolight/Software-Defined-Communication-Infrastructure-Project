package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginWindowController {

	
	@FXML
	private TextField userNameTextField;
	
	@FXML
	private Button logInButton;
	
	private MainApp mainApp;
	
	
	public LoginWindowController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	public void initialize(){
		
	}
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
		
	}
	@FXML
	public void handleLoginButtonAction(ActionEvent event){
		mainApp.getRootLayout().setCenter(null);
		mainApp.showGFsOverview();
		mainApp.showGIsOverview();
		mainApp.showLinksOverview();
	}

	@FXML
	public void onEnter(ActionEvent ae){
		handleLoginButtonAction(ae);
	}

}
