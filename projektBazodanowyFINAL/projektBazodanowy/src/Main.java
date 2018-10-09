

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Main implementuje część aplikacji odpowiedzialnej
 * za prezentację możliwości programu.
 *
 * @author  Maciej Wnuk Lipiński
 * @version 1.0
 * @since   2018-05-28
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
/**
 * Ta metoda używana jest do ładowania
 * poszczególnego szablonu aplikacji
 *
 * @param primaryStage To jedyny parametr dla metody start
 * @return Stage Zwraca typ Stage
 */
        Parent fxml = FXMLLoader.load(getClass().getResource("app.fxml"));
//pl/edziennik/TeacherSource/View/Templates/Login.fxml
        Scene scene = new Scene(fxml);

        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.initStyle(StageStyle.TRANSPARENT);

        primaryStage.show();



    }




    public static void main(String... args){
        launch(args);

    }
}
