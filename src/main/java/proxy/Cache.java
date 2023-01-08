package proxy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @Setter
@NoArgsConstructor
public class Cache {
    private String url;
    private String scrapped;

    public void save() {
        DBConnection dbConnection = DBConnection.getInstance();
        String query = String.format("insert into cached (url, scrapped) values ('%s', '%s');",
                url, scrapped.replace("'", "^"));
        dbConnection.saving(query);
    }

    public boolean check(String url) {
        DBConnection dbConnection = DBConnection.getInstance();
        String query = "select * from cached;";
        return dbConnection.checking(query, url);
    }

    public String get(String url) {
        DBConnection dbConnection = DBConnection.getInstance();
        String query = String.format("select * from cached where url=('%s');", url);
        return dbConnection.getting(query);
    }
}
