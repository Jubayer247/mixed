package mdJubayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) {
        try {
            HttpURLConnection con = (HttpURLConnection) new URL("http://notify247.000webhostapp.com/json_test.php").openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.135 Safari/537.36 Edge/12.10240 ");
            con.setRequestProperty("Content-Language", "en-US");
            //con.setRequestProperty("Cookie", "__test=7f04e0c131a1ac373076b95b181ad0b3; expires=Fri, 01-Jan-38 5:55:55 GMT; path=/");
            con.setUseCaches(false);
            con.setDoInput(true);
            con.setDoOutput(true);
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                String json = readLine;
                if (readLine == null) {
                     sb.toString().trim();
                }
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(json);
                stringBuilder.append("\n");
                String s=stringBuilder.toString();

                if(s!=null) {
                    System.out.println(s);
                }
            }
        } catch (Exception e) {
      //      System.out.println("exception" );
        }
    }
    }

