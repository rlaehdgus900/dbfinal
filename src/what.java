
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class what {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("https://openapi.gg.go.kr/MrktStoreM"); 
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=4f5be36dcb204f35a0786e7e1ab277c7"); 
   urlBuilder.append("&" + URLEncoder.encode("Type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pIndex","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pSize","UTF-8") + "=" + URLEncoder.encode("50", "UTF-8")); 
        urlBuilder.append("&" + URLEncoder.encode("SIGUN_CD","UTF-8") + "=" + URLEncoder.encode("41650", "UTF-8")); 

        File file = new File("result.txt");
        FileWriter writer = null; 
        
        writer = new FileWriter(file,true);
         
        
        
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
            writer.write(line);
        }
        
        
        writer.close();
        rd.close();
        
        
        conn.disconnect();
        System.out.println(sb.toString());
    }
}