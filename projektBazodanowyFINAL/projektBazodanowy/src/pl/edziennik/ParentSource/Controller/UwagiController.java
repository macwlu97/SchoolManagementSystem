package pl.edziennik.ParentSource.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import pl.edziennik.ParentSource.Helpers.*;
import pl.edziennik.ParentSource.Model.DriverDB;
import pl.edziennik.ParentSource.Model.GetUwagi;

import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

//import pl.edziennik.TeacherSource.Controller.Usprawiedliwienie;
//import pl.edziennik.TeacherSource.Helpers.Uczniowie;
//import pl.edziennik.TeacherSource.Model.DriverDB;

public class UwagiController extends DriverDB implements Initializable {
    @FXML
    private FlowPane flowx = new FlowPane();

    @FXML
    private TableView<UwagiProperty> table;
    @FXML
    private TableColumn UWAGA = new TableColumn();
    @FXML
    private TableColumn NAUCZYCIEL = new TableColumn();
    @FXML
    private TableColumn DATA = new TableColumn();
    Uczniowie uczen;

    List<Uwagi> uwagies;
GetUwagi getUwagi;
  //  GetAttendance getAttendance = new GetAttendance();
//    List<Obecnosc> nieobs =  new ArrayList<>();

    public UwagiController() throws SQLException {
    }



    //    Przedmioty przedmiottmp = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setConfig(int childID) throws SQLException {
        getUwagi = new GetUwagi();
        uwagies = getUwagi.Get(childID);
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        String reportDate;
        final ObservableList<UwagiProperty> data = FXCollections.observableArrayList();
        for(Uwagi sub : uwagies) {



// Get the date today using Calendar object.
//            Date today = Calendar.getInstance().getTime();
// Using DateFormat format method we can create a string
// representation of a date with the defined format.
             reportDate = df.format(sub.getData());

// Print what date is today!
//            System.out.println("Report Date: " + reportDate);
            UwagiProperty tmp =  new UwagiProperty(sub.getOpis(),sub.getID_NAUCZYCIELA(), reportDate);
            data.add(tmp);

        }



                //uczen.
                UWAGA.setCellValueFactory(
                        new PropertyValueFactory<UwagiProperty, String>("UWAGA"));
                NAUCZYCIEL.setCellValueFactory(
                new PropertyValueFactory<UwagiProperty, String>("NAUCZYCIEL"));
                DATA.setCellValueFactory(
                        new PropertyValueFactory<UwagiProperty, String>("DATA"));
//                OBECNOSC.setCellValueFactory(
//                        new PropertyValueFactory<Person, Object>("OBECNOSC"));
                table.setEditable(true);
                table.setItems(data);

//                choiceZaj = keyValuePair.getKey();


    }
}


