package pl.edziennik.TeacherSource.Helpers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class Person {
    private final SimpleStringProperty IDSTUD;
    private final SimpleStringProperty UCZEN;
    private final SimpleObjectProperty OBECNOSC;

    public Person(String id, String ucz, Object ob) {
        this.IDSTUD = new SimpleStringProperty(id);
        this.UCZEN = new SimpleStringProperty(ucz);
        this.OBECNOSC = new SimpleObjectProperty(ob);
    }

    public String getIDSTUD() {
        return IDSTUD.get();
    }
    public void setIDSTUD(String id) {
        IDSTUD.set(id);
    }

    public String getUCZEN() {
        return UCZEN.get();
    }
    public void setUCZEN(String fName) {
        UCZEN.set(fName);
    }

    public Object getOBECNOSC() {
        return OBECNOSC.get();
    }
    public void setOBECNOSC(String ob) {
        OBECNOSC.set(ob);
    }

}