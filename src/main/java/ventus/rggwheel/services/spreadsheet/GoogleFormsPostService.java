package ventus.rggwheel.services.spreadsheet;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class GoogleFormsPostService {
    private static final String forms = "https://docs.google.com/forms/d/e/1FAIpQLScw21VjR9jpyojgLN5xVn-3Zd92dsLVjwqQu5aSF-DalSnEOQ/formResponse";

    private static String stateToPostParams(String prizeName, String inventory) {
        return "entry.1230378250=" + System.getProperty("user.name") + "&entry.1233962603=" + prizeName + "&entry.1667522736=" + inventory;
    }

    public static void savePrizeToSpreadsheet(String prizeName, String inventory) {
        if (System.getProperty("user.name").equalsIgnoreCase("TMR")) {
            try {
                URL url = new URL(forms);
                URLConnection con = url.openConnection();
                HttpURLConnection http = (HttpURLConnection) con;
                con.setRequestProperty("User-Agent", "Java");
                http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                http.setRequestMethod("POST");
                http.setDoOutput(true);
                http.connect();
                byte[] postData = stateToPostParams(prizeName, inventory).getBytes(StandardCharsets.UTF_8);
                try (var wr = new DataOutputStream(con.getOutputStream())) {
                    wr.write(postData);
                }
                StringBuilder content;

                try (var br = new BufferedReader(
                        new InputStreamReader(con.getInputStream()))) {

                    String line;
                    content = new StringBuilder();

                    while ((line = br.readLine()) != null) {

                        content.append(line);
                        content.append(System.lineSeparator());
                    }
                }

//            System.out.println(content.toString());
                http.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
