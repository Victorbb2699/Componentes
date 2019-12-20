package dad.javafx.components;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TestApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		
		
//		ListSelector<String> listSelector = new ListSelector<>();
//		listSelector.setLeftTitle("Jugadores");
//		listSelector.setRightTitle("Participantes");
//		listSelector.getLeftItems().addAll("Perico");
//		listSelector.getLeftItems().addAll("Palotes");
//		listSelector.getLeftItems().addAll("Menganita");
//		listSelector.getLeftItems().addAll("Fulanito");

		DateChooser dateChooser = new DateChooser();
		
		BorderPane root = new BorderPane();
		root.setCenter(dateChooser);
		

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("Custon components test app");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}

}
