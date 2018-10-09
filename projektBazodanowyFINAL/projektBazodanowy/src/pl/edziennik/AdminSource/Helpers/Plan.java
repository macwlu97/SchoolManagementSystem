package pl.edziennik.AdminSource.Helpers;

public class Plan {
    public Integer ID_ZAJ;
    public Integer ID_PRZEDMIOTU;
    public Integer GODZINA;
    public Integer ID_KLASY;
    public Integer DZIEN;
    public String NAZWA;

    public Integer getID() { return ID_ZAJ; }
    public Integer getID_PRZEDMIOTU() { return ID_PRZEDMIOTU; }
    public Integer getGODZINA() { return GODZINA; }
    public Integer getID_KLASY() { return ID_KLASY; }
    public Integer getDZIEN() { return DZIEN; }
    public String getNAZWA() { return NAZWA; }

    public void setID(Integer id) { this.ID_ZAJ = id; }
    public void setID_PRZEDMIOTU(Integer idp) { this.ID_PRZEDMIOTU = idp; }
    public void setGODZINA(Integer idg) { this.GODZINA = idg; }
    public void setID_KLASY(Integer idk) { this.ID_KLASY = idk; }
    public void setDZIEN(Integer idd) { this.DZIEN = idd; }
    public void setNazwa(String naz) { this.NAZWA = naz; }
}
