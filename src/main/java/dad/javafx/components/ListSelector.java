package dad.javafx.components;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;

public class ListSelector<T> extends GridPane implements Initializable {

	// model

	private ListProperty<T> leftItems = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<T> rightItems = new SimpleListProperty<>(FXCollections.observableArrayList());
	private StringProperty leftTitle = new SimpleStringProperty();
	private StringProperty rightTitle = new SimpleStringProperty();

	// view

	@FXML
	private ListView<T> leftList, rightList;

	@FXML
	private Button moveLeftButton, moveAllLeftButton, moveAllRightButton, moveRightButton;

	@FXML
	private Label izquierdaLabel, derechaLabel;

	@FXML
	void onMoveAllLeftAction(ActionEvent event) {
		
		leftItems.addAll(rightItems);
		rightItems.clear();
		
	}

	@FXML
	void onMoveAllRightAction(ActionEvent event) {

		rightItems.addAll(leftItems);
		leftItems.clear();
		
		
	}

	@FXML
	void onMoveLeftAction(ActionEvent event) {

		leftItems.addAll(rightList.getSelectionModel().getSelectedItems());
		rightItems.removeAll(rightList.getSelectionModel().getSelectedItems());
	}

	@FXML
	void onMoveRightAction(ActionEvent event) {
		rightItems.addAll(leftList.getSelectionModel().getSelectedItems());
		leftItems.removeAll(leftList.getSelectionModel().getSelectedItems());
	}

	public ListSelector() {
		super();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ListSelectorView.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// binds

		izquierdaLabel.textProperty().bind(leftTitle);
		derechaLabel.textProperty().bind(rightTitle);

		leftList.itemsProperty().bind(leftItems);
		rightList.itemsProperty().bind(rightItems);
		
		moveRightButton.disableProperty().bind(leftList.getSelectionModel().selectedItemProperty().isNull());
		moveLeftButton.disableProperty().bind(rightList.getSelectionModel().selectedItemProperty().isNull());
		
		moveAllLeftButton.disableProperty().bind(Bindings.isEmpty(rightItems));
		
		moveAllRightButton.disableProperty().bind(Bindings.isEmpty(leftItems));
		
	}

	public final ListProperty<T> leftItemsProperty() {
		return this.leftItems;
	}

	public final ObservableList<T> getLeftItems() {
		return this.leftItemsProperty().get();
	}

	public final void setLeftItems(final ObservableList<T> leftItems) {
		this.leftItemsProperty().set(leftItems);
	}

	public final ListProperty<T> rightItemsProperty() {
		return this.rightItems;
	}

	public final ObservableList<T> getRightItems() {
		return this.rightItemsProperty().get();
	}

	public final void setRightItems(final ObservableList<T> rightItems) {
		this.rightItemsProperty().set(rightItems);
	}

	public final StringProperty leftTitleProperty() {
		return this.leftTitle;
	}

	public final String getLeftTitle() {
		return this.leftTitleProperty().get();
	}

	public final void setLeftTitle(final String leftTitle) {
		this.leftTitleProperty().set(leftTitle);
	}

	public final StringProperty rightTitleProperty() {
		return this.rightTitle;
	}

	public final String getRightTitle() {
		return this.rightTitleProperty().get();
	}

	public final void setRightTitle(final String rightTitle) {
		this.rightTitleProperty().set(rightTitle);
	}

}
