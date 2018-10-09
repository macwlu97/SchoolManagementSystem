package pl.edziennik.ParentSource.Helpers;

import java.sql.Date;

public class Nauczyciele {
    protected Integer ID;
    protected String IMIE;
    protected String NAZWISKO;
    protected String TELEFON;
    protected String EMAIL;

    public Integer getID() { return ID; }
    public String getIMIE() { return IMIE; }
    public String getNAZWISKO() { return NAZWISKO; }
    public String getTELEFON() { return TELEFON; }
    public String getEMAIL() { return EMAIL; }

    public void setID(Integer id) { this.ID = id; }
    public void setIMIE(String imie) { this.IMIE = imie; }
    public void setNAZWISKO(String nazwisko) { this.NAZWISKO = nazwisko; }
    public void setTELEFON(String tele) { this.TELEFON = tele; }
    public void setEMAIL(String email) { this.EMAIL = email; }

}


