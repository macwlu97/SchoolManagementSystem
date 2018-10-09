package pl.edziennik.TeacherSource.Helpers;

import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class getTableViewValuesGrade {
    public static ArrayList<Ocena> getTableViewValues(TableView tableView) {
        ArrayList<Ocena> values = new ArrayList<>();
        ObservableList<TableColumn> columns = tableView.getColumns();

        for (Object row : tableView.getItems()) {
            int x=0;
            Ocena ocena = new Ocena();
            for (TableColumn column : columns) {
                if(x==0) ocena.ID_UCZNIA =  (Integer) column.
                                getCellObservableValue(row).
                                getValue();
                if(x==1) ocena.UCZEN = (String) column.
                        getCellObservableValue(row).
                        getValue();
                if(x==2){
                    ocena.OCENA = (Integer) ((ChoiceBox)column.getCellObservableValue(row).getValue()).getValue();

                }

                x++;
            }
            values.add(ocena);
        }

        return values;
    }
}
// values.add