package pl.edziennik.ParentSource.Helpers;

import java.util.Date;

public class Uwagi {
    protected Integer ID_UWAGI;
    protected Integer ID_UCZNIA;
    protected String OPIS;
    protected Date DATA;
    protected String ID_NAUCZYCIELA;

    public Integer getID() { return ID_UWAGI; }
    public Integer getIDu() { return ID_UCZNIA; }
    public String getOpis() { return OPIS; }
    public Date getData() { return DATA; }
    public String getID_NAUCZYCIELA() { return ID_NAUCZYCIELA; }

    public void setID(Integer id) { this.ID_UWAGI = id; }
    public void setIDu(Integer idu) { this.ID_UCZNIA = idu; }
    public void setOpis(String opis) { this.OPIS = opis; }
    public void setData(Date data) { this.DATA = data; }
    public void setID_NAUCZYCIELA(String idn) { this.ID_NAUCZYCIELA = idn; }
}
