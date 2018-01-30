package view;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.*;


public class GIOverviewController {


	@FXML
	private ListView<DataType_GI> _GIArray;
	
	@FXML
	private Button btnAddGI;
	@FXML
	private Button btnDelGI;
	
	//reference to the main application
	private MainApp mainApp;
	
	public GIOverviewController(){
		
	}
	
	/**
	 * Initializer called after the FXML file has been loaded
	 */
	@FXML
	private void initialize(){

		_GIArray.setCellFactory(tc -> {
			ListCell<DataType_GI> cell = new ListCell<DataType_GI>(){
				@Override
				protected void updateItem(DataType_GI gi, boolean empty){
					super.updateItem(gi, empty);
					setText(empty ? null : gi.getName() +" : " + gi.getContainer().getStatus().toString());
				}
			};
			return cell;
		});
	}
	
	@FXML
	public void handleBtnAddGIAction() {
		DataType_GICreationParam giConfig = new DataType_GICreationParam(2, 10, 10);
		mainApp.getController().askGICreation(giConfig);
	}
	
	@FXML
	public void handleBtnDelGIAction() {
		int selectedIndex = _GIArray.getSelectionModel().getSelectedIndex();
		if (selectedIndex > 0) {
			// can not delete GInit in this version
			mainApp.getController().askGIDeletion(selectedIndex);
		}
	}
	
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
	
	public void bindGIArray(ObservableList<DataType_GI> giArray) {
		_GIArray.setItems(giArray);
	}

	public void refresh(){
		_GIArray.refresh();
	}

	public void update(DataType_GI gi) {
		Platform.runLater(() ->{
			boolean found = false;
			for(DataType_GI u : _GIArray.getItems()){
				if(u.equals(gi)){
					_GIArray.refresh();
					found = true;
					break;
				}
			}
			if(!found){
				_GIArray.getItems().add(gi);
			}
		});
	}
}
