package pl.edziennik.ParentSource.Helpers;

public class Ratings {
    protected Integer ID_OCENY;
    protected Integer ID_UCZNIA;
    protected Integer ID_PRZEDMIOTU;
    protected Integer OCENA;

    public Integer getID() { return ID_OCENY; }
    public Integer getID_UCZNIA() { return ID_UCZNIA; }
    public Integer getID_PRZEDMIOTU() { return ID_PRZEDMIOTU; }
    public Integer getOCENA() { return OCENA; }


    public void setID(Integer id) { this.ID_OCENY = id; }
    public void setID_UCZNIA(Integer idu) { this.ID_UCZNIA = idu; }
    public void setID_PRZEDMIOTU(Integer idp) { this.ID_PRZEDMIOTU = idp; }
    public void setOCENA(Integer ocena) { this.OCENA = ocena; }
}
