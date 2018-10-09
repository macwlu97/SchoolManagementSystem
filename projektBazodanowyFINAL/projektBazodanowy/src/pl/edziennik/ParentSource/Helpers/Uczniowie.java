package pl.edziennik.ParentSource.Helpers;

import java.sql.Date;

public class Uczniowie {
    protected Integer ID;
    protected Integer ID_RODZICA;
    protected String IMIE;
    protected String NAZWISKO;
    protected String AVATAR;
    protected Date DATA_URODZENIA;
    protected Integer ID_KLASY;

    public Integer getID() { return ID; }
    public Integer getID_RODZICA() { return ID_RODZICA; }
    public String getIMIE() { return IMIE; }
    public String getNAZWISKO() { return NAZWISKO; }
    public String getAVATAR() { return AVATAR; }
    public Date getDATA_URODZENIA() { return DATA_URODZENIA; }
    public Integer getID_KLASY() { return ID_KLASY; }

    public void setID(Integer id) { this.ID = id; }
    public void setIMIE(String imie) { this.IMIE = imie; }
    public void setNAZWISKO(String nazwisko) { this.NAZWISKO = nazwisko; }
    public void setID_RODZICA(Integer id) { this.ID_RODZICA = id; }
    public void setAVATAR(String avatar) { this.AVATAR = avatar; }
    public void setDATA_URODZENIA(Date data) { this.DATA_URODZENIA = data; }
    public void setID_KLASY(Integer id) { this.ID_KLASY = id; }

}


