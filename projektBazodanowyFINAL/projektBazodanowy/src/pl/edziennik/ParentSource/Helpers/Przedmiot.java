package pl.edziennik.ParentSource.Helpers;

import javafx.beans.property.*;

public class Przedmiot {
    private final SimpleStringProperty NAZWA;
    private final SimpleStringProperty OCENY;
    private final SimpleStringProperty SREDNIA;
//    private final SimpleObjectProperty OBECNOSC;

    public Przedmiot(String naz, String oceny, String sre) {
        this.NAZWA = new SimpleStringProperty(naz);
        this.OCENY = new SimpleStringProperty(oceny);
        this.SREDNIA = new SimpleStringProperty(sre);
//        this.OBECNOSC = new SimpleObjectProperty(ob);
    }

    public String getNAZWA() {
        return NAZWA.get();
    }
    public void setNAZWA(String naz) {
        NAZWA.set(naz);
    }

    public String getOCENY() {
        return OCENY.get();
    }
    public void setOCENY(String oceny) {
        OCENY.set(oceny);
    }

    public String getSREDNIA() {
        return SREDNIA.get();
    }
    public void setSREDNIA(String sr) {
        SREDNIA.set(sr);
    }

//    public Object getOBECNOSC() {
//        return OBECNOSC.get();
//    }
//    public void setOBECNOSC(String ob) {
//        OBECNOSC.set(ob);
//    }

}