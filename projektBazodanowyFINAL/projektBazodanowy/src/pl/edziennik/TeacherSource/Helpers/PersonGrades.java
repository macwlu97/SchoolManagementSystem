package pl.edziennik.TeacherSource.Helpers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class PersonGrades {
    private final SimpleIntegerProperty IDSTUD;
    private final SimpleStringProperty UCZEN;
    private final SimpleObjectProperty OCENA;

    public PersonGrades(Integer id, String ucz, Object ob) {
        this.IDSTUD = new SimpleIntegerProperty(id);
        this.UCZEN = new SimpleStringProperty(ucz);
        this.OCENA = new SimpleObjectProperty(ob);
    }

    public Integer getIDSTUD() {
        return IDSTUD.get();
    }
    public void setIDSTUD(Integer id) {
        IDSTUD.set(id);
    }

    public String getUCZEN() {
        return UCZEN.get();
    }
    public void setUCZEN(String fName) {
        UCZEN.set(fName);
    }

    public Object getOCENA() {
        return OCENA.get();
    }
    public void setOCENA(String ob) {
        OCENA.set(ob);
    }
//    public void setOCENA(String ob) {
//        OCENA.set(ob);
//    }
}