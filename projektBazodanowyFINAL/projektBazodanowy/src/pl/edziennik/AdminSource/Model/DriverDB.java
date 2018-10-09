package pl.edziennik.AdminSource.Model;

import oracle.jdbc.pool.OracleDataSource;
import pl.edziennik.User.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DriverDB extends User {
    public DriverDB() throws SQLException {
        polacz();
    }
    protected String jdbcurl = "jdbc:oracle:thin:@localhost:1521:xe";
    protected String userid = "G3_lipinski97";
    protected String password = "password";
    protected Connection conn;
    protected Statement zapytanie;
    public ResultSet wynik = null;

    public void polacz() throws SQLException {
        OracleDataSource ds = new OracleDataSource();
        ds.setURL(jdbcurl);
        conn=ds.getConnection(userid,password);
    }

}
