package seedu.address.ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Submits user data
 */
public class ExitDialog {

    private static boolean flagYes;

    /**
     * displays Exit Dialog
     * @param title Exit Dialog header
     * @param message Exit Dialog message
     * @return boolean flagYes
     */
    public static boolean display(String title, String message) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.resizableProperty().setValue(false);
        window.setMinWidth(400);

        Button yesButton = new Button("Yes");
        yesButton.setOnAction(e -> {
            window.close();
            flagYes = true;
        });
        Button noButton = new Button("No");
        noButton.setOnAction(e -> {
            window.close();
            flagYes = false;
        });
        Label label = new Label();
        Label emptySpace = new Label();
        label.setText(message);

        VBox layoutMain = new VBox(10);
        HBox layoutButton = new HBox(10);
        layoutButton.getChildren().addAll(yesButton, noButton);
        layoutMain.getChildren().addAll(label, layoutButton, emptySpace);

        layoutMain.setAlignment(Pos.CENTER);
        layoutButton.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layoutMain);
        window.setScene(scene);
        window.showAndWait();

        return flagYes;
    }
}
