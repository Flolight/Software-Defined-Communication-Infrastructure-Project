package view;

import java.util.Optional;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import model.*;


public class LinkOverviewController {


	@FXML
	private ListView<DataType_Link> _LinkArray;
	
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
	}
	
	@FXML
	public void handleBtnAddLinkAction() {
		int gfIndex = mainApp.getSelectedIndexGF();
		int giIndex = mainApp.getSelectedIndexGI();
		
		DataType_GF gf = mainApp.getController().getTopologyCache().getGFArray().getGFInfo(gfIndex);
		DataType_GI gi = mainApp.getController().getTopologyCache().getGIArray().getGIInfo(giIndex);
		DataType_Link link = new DataType_Link(gi, gf);
		mainApp.getController().askLinkCreation(link);
	}
	
	@FXML
	public void handleBtnDelLinkAction() {
		if(_LinkArray.getSelectionModel().getSelectedItem()==null){
			return;
		}
		
		DataType_Link link = _LinkArray.getSelectionModel().getSelectedItem();
		mainApp.getController().askLinkDeletion(link);
	}
	
	@FXML
	public void handleBtnEditLinkAction() {
		if(_LinkArray.getSelectionModel().getSelectedItem()==null){
			return;
		}
		
		DataType_Link link = _LinkArray.getSelectionModel().getSelectedItem();
		DataType_Link newLink = ShowCustomEditView(link);
		if(newLink==null){
			return;
		}
		
		mainApp.getController().askLinkDeletion(link);
		mainApp.getController().askLinkCreation(newLink);
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
	
	public int getSelectedIndex() {
		return _LinkArray.getSelectionModel().getSelectedIndex();
	}
	
	public DataType_Link ShowCustomEditView(DataType_Link link){
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Edit a link");
		dialog.setHeaderText("Please select an intermediate gateway and a final gateway");

		// Set the button types.
		ButtonType EditButton = new ButtonType("Edit", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(EditButton, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
		
		GFArray GFlist = mainApp.getController().getTopologyCache().getGFArray();
		GIArray GIlist= mainApp.getController().getTopologyCache().getGIArray();
		
		final ComboBox<String> comboBoxGI = new ComboBox<String>();
		final ComboBox<String> comboBoxGF = new ComboBox<String>();

		for(DataType_GF gf : GFlist.getGFs()){
			comboBoxGF.getItems().add(gf.getName());
		}
		
		for(DataType_GI gi : GIlist.getGIs()){
			comboBoxGI.getItems().add(gi.getName());
		}
		
		//We remove the initial gateway from the list
		comboBoxGI.getItems().remove(0);
		
		comboBoxGF.getSelectionModel().select(link.getGf().getName());
		comboBoxGI.getSelectionModel().select(link.getGi().getName());
		
		grid.add(new Label("Final Gateway :"), 0, 0);
		grid.add(comboBoxGF, 1, 0);
		grid.add(new Label("Intermediate Gateway :"), 0, 1);
		grid.add(comboBoxGI, 1, 1);
		
		dialog.getDialogPane().setContent(grid);
		
		dialog.setResultConverter(dialogButton -> {
		   return new Pair<>(comboBoxGF.getValue(), comboBoxGI.getValue());
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();
		if (result.isPresent()){
			if(result.get().getValue().equals(link.getGi().getName()) && result.get().getKey().equals(link.getGf().getName())){
				return null;
			}
			link.setGf(GFlist.getGFByName(result.get().getKey()));
			link.setGi(GIlist.getGIByName(result.get().getValue()));
		}
		return link;
	
	}
}
