package pl.edziennik.ParentSource.Helpers;

public class Informacje {
    protected Integer ID;
    protected String IMIE;
    protected String NAZWISKO;
    protected String LOGIN;
    public Integer getID() { return ID; }
    public String getIMIE() { return IMIE; }
    public String getNAZWISKO() { return NAZWISKO; }
    public String getLOGIN() { return LOGIN; }


    public void setID(Integer id) { this.ID = id; }
    public void setIMIE(String imie) { this.IMIE = imie; }
    public void setNAZWISKO(String nazwisko) { this.NAZWISKO = nazwisko; }
    public void setLOGIN(String login) { this.LOGIN = login; }

}
