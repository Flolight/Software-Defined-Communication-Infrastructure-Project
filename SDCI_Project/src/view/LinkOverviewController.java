package view;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.*;


public class LinkOverviewController {


	@FXML
	private ListView<DataType_Link> _LinkArray;
	
	// for test
	@FXML
	private Button btnAddLink;
		
	
	//reference to the main application
	private MainApp mainApp;
	
	public LinkOverviewController(){
		
	}
	
	/**
	 * Initializer called after the FXML file has been loaded
	 */
	@FXML
	private void initialize(){

		_LinkArray.setCellFactory(tc -> {
			ListCell<DataType_Link> cell = new ListCell<DataType_Link>(){
				@Override
				protected void updateItem(DataType_Link li, boolean empty){
					super.updateItem(li, empty);
					setText(empty ? null : li.getGf().getName() +" -> " + li.getGi().getName());
				}
			};
			return cell;
		});
		
		// for test
		btnAddLink.setOnAction((e)->{
			//TO DO
		});
	}
	
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
	
	public void bindLinkArray(ObservableList<DataType_Link> liArray) {
		_LinkArray.setItems(liArray);
	}

	public void refresh(){
		_LinkArray.refresh();
	}

	public void update(DataType_Link li) {
		Platform.runLater(() ->{
			boolean found = false;
			for(DataType_Link u : _LinkArray.getItems()){
				if(u.equals(li)){
					_LinkArray.refresh();
					found = true;
					break;
				}
			}
			if(!found){
				_LinkArray.getItems().add(li);
			}
		});
	}
}
