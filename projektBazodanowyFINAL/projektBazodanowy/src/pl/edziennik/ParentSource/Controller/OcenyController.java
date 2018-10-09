package pl.edziennik.ParentSource.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import pl.edziennik.ParentSource.Helpers.*;
import pl.edziennik.ParentSource.Model.DriverDB;
import pl.edziennik.ParentSource.Model.GetChildInformation;
import pl.edziennik.ParentSource.Model.GetChildSubject;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

//import pl.edziennik.TeacherSource.Controller.Usprawiedliwienie;
//import pl.edziennik.TeacherSource.Helpers.Uczniowie;
//import pl.edziennik.TeacherSource.Model.DriverDB;

public class OcenyController extends DriverDB implements Initializable {
    @FXML
    private FlowPane flowx = new FlowPane();
    @FXML
    private Label info = new Label();
    @FXML
    private TableView<Przedmiot> table;
    @FXML
    private TableColumn NAZWA = new TableColumn();
    @FXML
    private TableColumn OCENY = new TableColumn();
    @FXML
    private TableColumn SREDNIA = new TableColumn();
    Uczniowie uczen;
    GetChildInformation getChildInformation = new GetChildInformation();
    GetChildSubject getChildSubject = new GetChildSubject();
    Klasa klasa;
    Nauczyciele wychowawca;
    List<Subject> przedmioties;
    List<Ratings> ratings;


    public OcenyController() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setConfig(int childID) throws SQLException {
        uczen = getChildInformation.Get(childID);
        klasa = getChildInformation.GetKlasa(uczen.getID_KLASY());
        przedmioties = getChildSubject.Get(klasa.getID());

        final ObservableList<Przedmiot> data = FXCollections.observableArrayList();
        for(Subject sub : przedmioties) {

                ratings = getChildSubject.GetR(sub.getID(), childID);
                double srednia = 0;
            String sr=null;
            int ocen=0;
            String ocens = "";
            for(Ratings rat : ratings) {
            ocens += String.valueOf(rat.getOCENA())+" ";
            srednia += rat.getOCENA();
            ocen++;
            }
            if(srednia != 0) {
                srednia = srednia / ocen;
                java.text.DecimalFormat df=new java.text.DecimalFormat(); //tworzymy obiekt DecimalFormat
                df.setMaximumFractionDigits(2); //dla df ustawiamy największą ilość miejsc po przecinku
                df.setMinimumFractionDigits(2); //dla df ustawiamy najmniejszą ilość miejsc po przecinku
                 sr = df.format(srednia);
            }

            Przedmiot tmp =  new Przedmiot(sub.getNazwa(), ocens, sr);
            data.add(tmp);
            ocens = "";
        }



                //uczen.
                NAZWA.setCellValueFactory(
                        new PropertyValueFactory<Przedmiot, String>("NAZWA"));
                OCENY.setCellValueFactory(
                        new PropertyValueFactory<Przedmiot, String>("OCENY"));
                 SREDNIA.setCellValueFactory(
                        new PropertyValueFactory<Przedmiot, String>("SREDNIA"));

                table.setEditable(true);
                table.setItems(data);



    }
}


