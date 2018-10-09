package pl.edziennik.ParentSource.Model;



import pl.edziennik.ParentSource.Helpers.Informacje;
import pl.edziennik.ParentSource.Helpers.Uczniowie;
import pl.edziennik.ParentSource.Helpers.Uwagi;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetUwagi extends DriverDB {
    public GetUwagi() throws SQLException {
    }

    public List<Uwagi> Get(int idUser) throws SQLException {
        List<Uwagi> uwagi = new ArrayList<>();
        Uwagi temp2;
        GetInformationTeacher getInformationTeacher = new GetInformationTeacher();
        Informacje info;
        zapytanie = conn.createStatement();
        String quest = "SELECT * FROM Uwagi WHERE UCZNIOWIE_ID = '" + idUser + "'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Uwagi();
//            temp2.ID = Integer.toString(wynik.getInt("ID"));
            temp2.setID(wynik.getInt("ID_UWAGI"));
            temp2.setIDu(wynik.getInt("UCZNIOWIE_ID"));
            temp2.setOpis(wynik.getString("OPIS"));
            temp2.setData(wynik.getDate("DATA"));
             info = getInformationTeacher.Get(wynik.getInt("NAUCZYCIELE_ID_NAUCZYCIELA"));
             String cos = info.getIMIE()+" " +info.getNAZWISKO();
            temp2.setID_NAUCZYCIELA(cos);
            uwagi.add(temp2);


        }
        return uwagi;
        //return tablica z klasa dzieci
    }

}
