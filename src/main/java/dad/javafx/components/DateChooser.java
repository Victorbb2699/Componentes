package dad.javafx.components;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.GridPane;
import javafx.fxml.Initializable;

public class DateChooser extends GridPane implements Initializable {

	// model

	private ListProperty<Integer> dias = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<String> meses = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ListProperty<Integer> años = new SimpleListProperty<>(FXCollections.observableArrayList());
	private ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<LocalDate>();
	private StringProperty fecha = new SimpleStringProperty();
	private Calendar c = Calendar.getInstance();

	// view

	@FXML
	private GridPane view;

	@FXML
	private ComboBox<Integer> dayComboBox;

	@FXML
	private ComboBox<String> monthComboBox;

	@FXML
	private ComboBox<Integer> yearComboBox;

	public DateChooser() {
		super();

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DateChooserView.fxml"));
			loader.setController(this);
			loader.setRoot(this);
			loader.load();

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		dayComboBox.setEditable(false);
		dayComboBox.setDisable(true);

		monthComboBox.setEditable(false);
		monthComboBox.setDisable(true);
		
		
	}

	public enum Month {
		Enero, Febrero, Marzo, Abril, Mayo, Junio, Julio, Agosto, Septiembre, Octubre, Noviembre, Diciembre
	}

}
