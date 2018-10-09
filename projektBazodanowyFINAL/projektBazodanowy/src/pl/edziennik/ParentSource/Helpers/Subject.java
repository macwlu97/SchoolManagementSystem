package pl.edziennik.ParentSource.Helpers;

public class Subject {

        protected Integer ID_PRZEDMIOTU;
        protected Integer ID_Klasy;
        protected Integer ID_Nauczyciela;
        protected String Nazwa;

        public Integer getID() { return ID_PRZEDMIOTU; }
        public Integer getID_KLASY() { return ID_Klasy; }
        public Integer getID_Nauczyciela() { return ID_Nauczyciela; }
        public String getNazwa() { return Nazwa; }


        public void setID(Integer id) { this.ID_PRZEDMIOTU = id; }
        public void setID_Klasy(Integer idk) { this.ID_Klasy = idk; }
        public void setID_Nauczyciela(Integer idn) { this.ID_Nauczyciela = idn; }
        public void setNazwa(String naz) { this.Nazwa = naz; }

}
