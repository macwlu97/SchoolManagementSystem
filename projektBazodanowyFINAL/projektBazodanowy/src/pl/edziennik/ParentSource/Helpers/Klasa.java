package pl.edziennik.ParentSource.Helpers;

public class Klasa {
    protected Integer ID;
    protected String NAZWA;
    protected String ROK_SZKOLNY;
    protected Integer ID_NAUCZYCIELA;
    public Integer getID() { return ID; }
    public String getNAZWA() { return NAZWA; }
    public String getROK_SZKOLNY() { return ROK_SZKOLNY; }
    public Integer getID_NAUCZYCIELA() { return ID_NAUCZYCIELA; }


    public void setID(Integer id) { this.ID = id; }
    public void setNAZWA(String nazwa) { this.NAZWA = nazwa; }
    public void setROK_SZKOLNY(String rok) { this.ROK_SZKOLNY = rok; }
    public void setID_NAUCZYCIELA(Integer id) { this.ID_NAUCZYCIELA = id; }

}
