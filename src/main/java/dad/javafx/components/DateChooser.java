package dad.javafx.components;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Year;
import java.util.Calendar;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
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

	private IntegerProperty day = new SimpleIntegerProperty();
	private IntegerProperty month = new SimpleIntegerProperty();
	private StringProperty year = new SimpleStringProperty();
	private IntegerProperty leapYear = new SimpleIntegerProperty();
	private ObjectProperty<LocalDate> dateProperty = new SimpleObjectProperty<LocalDate>();

	// view

	@FXML
	private GridPane view;

	@FXML
	private ComboBox<Integer> dayComboBox;

	@FXML
	private ComboBox<String> monthComboBox;

	@FXML
	private ComboBox<String> yearComboBox;

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

		ListProperty<Integer> daysList = new SimpleListProperty<>(FXCollections.observableArrayList());
		ListProperty<String> monthsList = new SimpleListProperty<>(FXCollections.observableArrayList());
		ListProperty<String> yearsList = new SimpleListProperty<>(FXCollections.observableArrayList());

		dayProperty().bind(dayComboBox.getSelectionModel().selectedItemProperty());
		monthProperty().bind(monthComboBox.getSelectionModel().selectedIndexProperty().add(1));
		yearProperty().bind(yearComboBox.getSelectionModel().selectedItemProperty());
		
		for (int i = 1900; i < Calendar.getInstance().get(Calendar.YEAR) + 1; i++) {
			yearsList.add(String.valueOf(i));
		}

		monthsList.addAll("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre",
				"Octubre", "Noviembre", "Diciembre");

		for (int i = 1; i <= 31; i++) {
			daysList.add(i);
		}

		dayComboBox.itemsProperty().bind(daysList);
		monthComboBox.itemsProperty().bind(monthsList);
		yearComboBox.itemsProperty().bind(yearsList);

		dayComboBox.setEditable(false);
		monthComboBox.setEditable(false);
		yearComboBox.setEditable(true);

		dateProperty.addListener((o, ov, nv) -> {

			dayComboBox.getSelectionModel().select(nv.getDayOfMonth() - 1);
			monthComboBox.getSelectionModel().select(monthsList.get(nv.getMonthValue() - 1));
			yearComboBox.getSelectionModel().select(yearsList.indexOf(nv.getYear() +""));

		});

		dayProperty().addListener((o, ov, nv) -> {

			if (nv.intValue() != 0 && getMonth() != 0) {
				dateProperty.set(LocalDate.of(getDateProperty().getYear(), getMonth(), nv.intValue()));
			}

		});

		monthProperty().addListener((o, ov, nv) -> {

			daysList.clear();

			if (getMonth() == 2) {
				// leapYear
				if (Year.of(getLeapYear()).isLeap() && getLeapYear() != 0) {
					for (int i = 1; i <= 29; i++) {
						daysList.add(i);
					}
				} else {
					for (int i = 1; i <= 28; i++) {
						daysList.add(i);
					}
				}
			} else if (getMonth() == 4 || getMonth() == 6 || getMonth() == 9 || getMonth() == 11) {
				for (int i = 1; i <= 30; i++) {
					daysList.add(i);
				}
			} else {
				for (int i = 1; i <= 31; i++) {
					daysList.add(i);
				}
			}
			dayComboBox.getSelectionModel().select(0);
		});

		yearProperty().addListener((o, ov, nv) -> {

			try {
				setLeapYear(Integer.parseInt(nv));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		});

		leapYearProperty().addListener((o, ov, nv) -> {
			try {
				if (getMonth() == 2) {
					// leapYear
					if (Year.of(getLeapYear()).isLeap() && getLeapYear() != 0) {
						for (int i = 1; i <= 29; i++) {
							daysList.add(i);
						}
					} else {
						for (int i = 1; i <= 28; i++) {
							daysList.add(i);
						}
					}
					dayComboBox.getSelectionModel().select(0);
				}

			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			dateProperty.set(
					LocalDate.of(getLeapYear(), getDateProperty().getMonthValue(), getDateProperty().getDayOfMonth()));
		});

		dateProperty.set(LocalDate.now());

	}

	public final IntegerProperty dayProperty() {
		return this.day;
	}

	public final int getDay() {
		return this.dayProperty().get();
	}

	public final void setDay(final int day) {
		this.dayProperty().set(day);
	}

	public final IntegerProperty monthProperty() {
		return this.month;
	}

	public final int getMonth() {
		return this.monthProperty().get();
	}

	public final void setMonth(final int month) {
		this.monthProperty().set(month);
	}

	public final StringProperty yearProperty() {
		return this.year;
	}

	public final String getYear() {
		return this.yearProperty().get();
	}

	public final void setYear(final String year) {
		this.yearProperty().set(year);
	}

	public final ObjectProperty<LocalDate> datePropertyProperty() {
		return this.dateProperty;
	}

	public final LocalDate getDateProperty() {
		return this.datePropertyProperty().get();
	}

	public final void setDateProperty(final LocalDate dateProperty) {
		this.datePropertyProperty().set(dateProperty);
	}

	public final IntegerProperty leapYearProperty() {
		return this.leapYear;
	}

	public final int getLeapYear() {
		return this.leapYearProperty().get();
	}

	public final void setLeapYear(final int leapYear) {
		this.leapYearProperty().set(leapYear);
	}

}
