package pl.edziennik.ParentSource.Controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import pl.edziennik.ParentSource.Helpers.Informacje;
import pl.edziennik.ParentSource.Helpers.Klasa;
import pl.edziennik.ParentSource.Helpers.Nauczyciele;
import pl.edziennik.ParentSource.Helpers.Uczniowie;
import pl.edziennik.ParentSource.Model.DriverDB;
import pl.edziennik.ParentSource.Model.GetChildInformation;
//import pl.edziennik.TeacherSource.Controller.Usprawiedliwienie;
//import pl.edziennik.TeacherSource.Helpers.Uczniowie;
//import pl.edziennik.TeacherSource.Model.DriverDB;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InformacjeController extends DriverDB implements Initializable {
    @FXML
    private FlowPane flowx = new FlowPane();
    @FXML
    private Label info = new Label();
    Uczniowie uczen;
    GetChildInformation getChildInformation = new GetChildInformation();
    Klasa klasa;
    Nauczyciele wychowawca;
  //  GetAttendance getAttendance = new GetAttendance();
//    List<Obecnosc> nieobs =  new ArrayList<>();

    public InformacjeController() throws SQLException {
    }



    //    Przedmioty przedmiottmp = null;
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    public void setConfig(int childID) throws SQLException {
        uczen = getChildInformation.Get(childID);
        klasa = getChildInformation.GetKlasa(uczen.getID_KLASY());
        wychowawca = getChildInformation.GetWychowawca(klasa.getID_NAUCZYCIELA());
    info.setText("Uczeń: "+uczen.getIMIE()+ " " + uczen.getNAZWISKO() + "\n" + "Data urodzenia: " + uczen.getDATA_URODZENIA() +"\nKlasa: " + klasa.getNAZWA() + "\nRok szkolny: " + klasa.getROK_SZKOLNY()+"\nWychowawca: "+wychowawca.getIMIE()+" "+wychowawca.getNAZWISKO());

    }
}


