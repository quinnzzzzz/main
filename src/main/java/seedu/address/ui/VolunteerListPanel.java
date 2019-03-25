package seedu.address.ui;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.volunteer.Volunteer;

/**
 * Panel containing the list of volunteers.
 */
public class VolunteerListPanel extends UiPart<Region> {
    private static final String FXML = "VolunteerListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(VolunteerListPanel.class);

    @FXML
    private ListView<Volunteer> volunteerListView;

    @FXML
    private Label panelName;


    public VolunteerListPanel(ObservableList<Volunteer> volunteerList, ObservableValue<Volunteer> selectedVolunteer,
            Consumer<Volunteer> onSelectedVolunteerChange) {
        super(FXML);
        panelName.setFont(Font.font("Cambria", 32));
        panelName.setTextFill(Color.web("#ADFF2F"));
        panelName.setText("Beneficiaries Dummy");
        volunteerListView.setItems(volunteerList);
        volunteerListView.setCellFactory(listView -> new VolunteerListViewCell());
        volunteerListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            logger.fine("Selection in volunteer list panel changed to : '" + newValue + "'");
            onSelectedVolunteerChange.accept(newValue);
        });
        selectedVolunteer.addListener((observable, oldValue, newValue) -> {
            logger.fine("Selected volunteer changed to: " + newValue);

            // Don't modify selection if we are already selecting the selected volunteer,
            // otherwise we would have an infinite loop.
            if (Objects.equals(volunteerListView.getSelectionModel().getSelectedItem(), newValue)) {
                return;
            }

            if (newValue == null) {
                volunteerListView.getSelectionModel().clearSelection();
            } else {
                int index = volunteerListView.getItems().indexOf(newValue);
                volunteerListView.scrollTo(index);
                volunteerListView.getSelectionModel().clearAndSelect(index);
            }
        });
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Volunteer} using a {@code VolunteerCard}.
     */
    class VolunteerListViewCell extends ListCell<Volunteer> {
        @Override
        protected void updateItem(Volunteer volunteer, boolean empty) {
            super.updateItem(volunteer, empty);

            if (empty || volunteer == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new VolunteerCard(volunteer, getIndex() + 1).getRoot());
            }
        }
    }

}
