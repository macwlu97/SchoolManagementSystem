package pl.edziennik.TeacherSource.Controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pl.edziennik.TeacherSource.Model.DriverDB;
import pl.edziennik.User.User;
import pl.edziennik.auth.authUsers;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginController extends DriverDB implements Initializable {
    authUsers authUser = new authUsers();

    public LoginController() throws SQLException {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(authUser.get_SHA_512_SecurePassword("haselko123", "projekt"));
    }

    @FXML private JFXTextField loginfield;
    @FXML private JFXPasswordField passfield;
    @FXML
    public void onEnter(ActionEvent ae) throws SQLException {
        User user = new User();
        user.login = loginfield.getText();
        user.password = passfield.getText();
        String hash = authUser.get_SHA_512_SecurePassword(user.password, "projekt");
        DriverDB log = new DriverDB();
        log.polacz();
        log.pobierzDaneTeacher();
        if(log.logowanie(log.wynik, user.login, hash).equals("zalogowany")) {
            //System.out.println("zalog");
//                Parent blah = FXMLLoader.load(getClass().getResource("../View/Templates/Parent.fxml"));
//                Scene scene = new Scene(blah);
//                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                AppController cvc = .getController(); // This did the "trick"
//                cvc.setClient("gowno"); // Passing the client-object to the ClientViewController
//                appStage.setScene(scene);
//                appStage.show();
            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("../View/Templates/Teacher.fxml"));
            try {
                Loader.load();
            } catch (IOException ex){
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            AppController display = Loader.getController();
            display.setConfig(user, log);
            display.setName();
            Parent blah = Loader.getRoot();
//                Stage stage = new Stage();
////                stage.setScene(new Scene(blah));
////                stage.showAndWait();
            Stage appStage = (Stage) ((Node) ae.getSource()).getScene().getWindow();
            appStage.setScene(new Scene(blah));
            appStage.show();
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Uwaga");
            errorAlert.setContentText("Nie poprawne dane!");
            errorAlert.showAndWait();
        }
    }
    @FXML
    public void LoginTo(MouseEvent event) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, SQLException, IOException {
        User user = new User();
        user.login = loginfield.getText();
        user.password = passfield.getText();
        String hash = authUser.get_SHA_512_SecurePassword(user.password, "projekt");
        DriverDB log = new DriverDB();
            log.polacz();
            log.pobierzDaneTeacher();
            if(log.logowanie(log.wynik, user.login, hash).equals("zalogowany")) {
        //System.out.println("zalog");
//                Parent blah = FXMLLoader.load(getClass().getResource("../View/Templates/Parent.fxml"));
//                Scene scene = new Scene(blah);
//                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//                AppController cvc = .getController(); // This did the "trick"
//                cvc.setClient("gowno"); // Passing the client-object to the ClientViewController
//                appStage.setScene(scene);
//                appStage.show();
FXMLLoader Loader = new FXMLLoader();
Loader.setLocation(getClass().getResource("../View/Templates/Teacher.fxml"));
try {
    Loader.load();
} catch (IOException ex){
    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
}
AppController display = Loader.getController();
display.setConfig(user, log);
display.setName();
                Parent blah = Loader.getRoot();
//                Stage stage = new Stage();
////                stage.setScene(new Scene(blah));
////                stage.showAndWait();
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(new Scene(blah));
                appStage.show();
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Uwaga");
                errorAlert.setContentText("Nie poprawne dane!");
                errorAlert.showAndWait();
            }
    }

}
