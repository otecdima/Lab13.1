package proxy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String urlCheck = "https://www.newhomesource.com/";
//        String urlCheck = "https://walmart.com";
        Cache cachedUrl = new Cache();

        if (cachedUrl.check(urlCheck)) {
            String scrapped = cachedUrl.get(urlCheck);
            System.out.println(scrapped);
        } else {
            Document doc = Jsoup.connect(urlCheck).userAgent("Mozilla").get();
            System.out.println(doc);
            cachedUrl.setUrl(urlCheck);
            cachedUrl.setScrapped(doc.toString());
            cachedUrl.save();
        }
    }
}
