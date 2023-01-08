package proxy;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
    private static DBConnection dbconnection;
    private Connection connection;

    @SneakyThrows
    private DBConnection() {
        connection = DriverManager.getConnection("jdbc:sqlite:/Users/dimabatko/Desktop/OOP/Lab13.1/SQLite/scraper.db");
    }

    @SneakyThrows
    public void saving(String query) {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
        stmt.close();
    }

    @SneakyThrows
    public boolean checking(String query, String url) {
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(query);
        while (res.next()) {
            if (res.getString("url").equals(url)) {
                return true;
            }
        }
        return false;
    }

    @SneakyThrows
    public String getting(String query) {
        Statement statement = connection.createStatement();
        ResultSet res = statement.executeQuery(query);
        return res.getString("scrapped");
    }

    public static DBConnection getInstance() {
        if (dbconnection == null) {
            dbconnection = new DBConnection();
        }
        return dbconnection;
    }
}
