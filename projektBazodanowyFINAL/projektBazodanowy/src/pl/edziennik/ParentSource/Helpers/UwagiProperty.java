package pl.edziennik.ParentSource.Helpers;

import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UwagiProperty {
    private final SimpleStringProperty UWAGA;
    private final SimpleStringProperty NAUCZYCIEL;
    private final SimpleStringProperty DATA;
//    private final SimpleObjectProperty OBECNOSC;

    public UwagiProperty(String naz, String naucz, String data) {
        this.UWAGA = new SimpleStringProperty(naz);
        this.NAUCZYCIEL = new SimpleStringProperty(naucz);
        this.DATA = new SimpleStringProperty(data);
//        this.OBECNOSC = new SimpleObjectProperty(ob);
    }

    public String getUWAGA() {
        return UWAGA.get();
    }
    public void setUWAGA(String naz) {
        UWAGA.set(naz);
    }

    public String getNAUCZYCIEL() {
        return NAUCZYCIEL.get();
    }
    public void setNAUCZYCIEL(String naucz) {
        NAUCZYCIEL.set(naucz);
    }

    public String getDATA() {
        return DATA.get();
    }
    public void setDATA(String data) {
        DATA.set(data);
    }

//    public Object getOBECNOSC() {
//        return OBECNOSC.get();
//    }
//    public void setOBECNOSC(String ob) {
//        OBECNOSC.set(ob);
//    }

}