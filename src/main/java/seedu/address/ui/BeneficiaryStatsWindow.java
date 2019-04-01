package seedu.address.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.beneficiary.Beneficiary;
import seedu.address.model.project.ProjectTitle;

/**
 * Controller for a help page
 */
public class BeneficiaryStatsWindow {

    /*private TableView<SummarisedBeneficiary> table = new TableView<SummarisedBeneficiary>();
    private Stage stage;
    private static final Logger logger = LogsCenter.getLogger(BeneficiaryStatsWindow.class);

    public BeneficiaryStatsWindow(List<Beneficiary> beneficiaryList, Stage root) {
        super(FXML, root);
        List<SummarisedBeneficiary> data0 = new ArrayList<>();
        for (Beneficiary beneficiary: beneficiaryList) {
            data0.add(new SummarisedBeneficiary(beneficiary));
        }
        final ObservableList<SummarisedBeneficiary> data = FXCollections.observableArrayList(data0);
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(450);
        stage.setHeight(500);

        final Label label = new Label("Beneficiary Summary Table");
        label.setFont(new Font("Arial", 20));

        table.setEditable(true);

        TableColumn firstNameCol = new TableColumn("Beneficiary Name");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<SummarisedBeneficiary, String>("name"));

        TableColumn lastNameCol = new TableColumn("Number of Projects");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<SummarisedBeneficiary, String>("numberOfProjects"));

        TableColumn emailCol = new TableColumn("List of attached projects");
        emailCol.setMinWidth(200);
        emailCol.setCellValueFactory(
                new PropertyValueFactory<SummarisedBeneficiary, List<String>>("projectList"));

        table.setItems(data);
        table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();
    }

    public static class SummarisedBeneficiary {

        private final String name;
        private final String numberOfProjects;
        private final List<String> projectList = new ArrayList<>();

        private SummarisedBeneficiary(Beneficiary beneficiary) {
            this.name = beneficiary.getName().fullName;
            for (ProjectTitle project: beneficiary.getAttachedProjectLists()) {
                this.projectList.add(project.fullTitle);
            }
            this.numberOfProjects = Integer.toString(this.projectList.size());
        }

        public String getName() {
            return name;
        }

        public String getNumberOfProjects() {
            return numberOfProjects;
        }

        public List<String> getProjectList() {
            return projectList;
        }
    }*/
}
