package view;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import model.*;


public class GFOverviewController {


	@FXML
	private ListView<DataType_GF> _GFArray;
	
	//reference to the main application
	private MainApp mainApp;
	
	public GFOverviewController(){
		
	}
	
	/**
	 * Initializer called after the FXML file has been loaded
	 */
	@FXML
	private void initialize(){

		_GFArray.setCellFactory(tc -> {
			ListCell<DataType_GF> cell = new ListCell<DataType_GF>(){
				@Override
				protected void updateItem(DataType_GF gf, boolean empty){
					super.updateItem(gf, empty);
					setText(empty ? null : gf.getName() +" : " + gf.getStatus().toString());
				}
			};
			return cell;
		});
	}
	
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
	
	public void bindGFArray(ObservableList<DataType_GF> gfArray) {
		_GFArray.setItems(gfArray);
	}

	public void refresh(){
		_GFArray.refresh();
	}

	public void update(DataType_GF gf) {
		Platform.runLater(() ->{
			boolean found = false;
			for(DataType_GF u : _GFArray.getItems()){
				if(u.equals(gf)){
					_GFArray.refresh();
					found = true;
					break;
				}
			}
			if(!found){
				_GFArray.getItems().add(gf);
			}
		});
	}
}
