package pl.edziennik.TeacherSource.Helpers;

import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class getTableViewValues {
    public static ArrayList<Obecnosc> getTableViewValues(TableView tableView) {
        ArrayList<Obecnosc> values = new ArrayList<>();
        ObservableList<TableColumn> columns = tableView.getColumns();

        for (Object row : tableView.getItems()) {
            int x=0;
            Obecnosc obecnosc = new Obecnosc();
            for (TableColumn column : columns) {
                if(x==0) obecnosc.ID = Integer.parseInt((String) column.
                                getCellObservableValue(row).
                                getValue());
                if(x==1) obecnosc.NAZWA = (String) column.
                        getCellObservableValue(row).
                        getValue();
                if(x==2){
                    obecnosc.STAN = ((Boolean) ((CheckBox)column.
                            getCellObservableValue(row).getValue()).isSelected())? 1 : 0;
                }

                x++;
            }
            values.add(obecnosc);
        }

        return values;
    }
}
// values.add