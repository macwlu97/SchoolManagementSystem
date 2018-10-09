package pl.edziennik.TeacherSource.Model;

import pl.edziennik.TeacherSource.Helpers.Informacje;
import pl.edziennik.TeacherSource.Helpers.Przedmioty;
import pl.edziennik.TeacherSource.Helpers.Uczniowie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GetStudents extends DriverDB {
    public GetStudents() throws SQLException {
    }

    public List<Uczniowie> Get(int klasa) throws SQLException {
        List<Uczniowie> sub = new ArrayList<>();
        Uczniowie temp2;
        zapytanie = conn.createStatement();

        String quest = "SELECT * FROM Uczniowie WHERE KLASA_ID_KLASY = '"+klasa+"'";
        wynik = zapytanie.executeQuery(quest);
        while (wynik.next()) {
            temp2 = new Uczniowie();
            temp2.ID = wynik.getInt("ID");
            temp2.IMIE = wynik.getString("IMIE");
            temp2.NAZWISKO = wynik.getString("NAZWISKO");
//            temp2.ID_RODZICA = wynik.getInt("RODZICE_ID");
            temp2.AVATAR = wynik.getString("AVATAR");
            temp2.DATA_URODZENIA = wynik.getDate("DATA_URODZENIA");
            temp2.ID_KLASY = wynik.getInt("KLASA_ID_KLASY");
            sub.add(temp2);
        }
        return sub;
    }
}
