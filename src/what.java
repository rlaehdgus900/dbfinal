
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.sql.*;
import java.io.*;


public class what {

	/************************************************************************************************************************
	 * 
	 * The function that parses the necessary information to put into the table The
	 * parsed informations are written on the result.txt file
	 * 
	 */
	public static class checking {

		public void check(int[] indexing, String result, String parser) throws IOException {
			int index2 = 0, index1 = 0, how_many = 0, i = 0;
			while (true) {
				index2 = result.indexOf(parser, index1);
				// System.out.printf("index1 = %d\n", index1);
				// System.out.printf("index2 = %d\n", index2);
				if (index2 == -1)
					break;
				indexing[i++] = index2;
				// System.out.printf("array = %d\n", indexing[i - 1]);
				index1 = index2 + 1;
				// System.out.printf("index1 = %d\n", index1);
				how_many++;
			}
			// System.out.printf("how_many = %d\n", how_many);
			// System.out.println(index_array);
			File file = new File("result.txt");
			FileWriter writer = null;

			writer = new FileWriter(file, true);
			
			int k = 0;

			if (parser == "TELNO") {
				for (int j = 0; j < how_many - 1; j++) {

					k = result.indexOf("\"", indexing[j]) + 2;
					writer.write(result.substring(k, result.indexOf(",", k)));
					//System.out.println(result.substring(k, result.indexOf(",", k)));
					writer.write("\n");
					if (j + 1 == how_many - 1) {
						writer.write(result.substring(k, result.indexOf(",", k)));
						writer.write("\n");
					}
					System.out.println("나 여기있어1");
					//System.out.println(j);
				}
			}else {
				for (int j = 0; j < how_many - 1; j++) {
					k = result.indexOf("\"", indexing[j]) + 3;
					writer.write(result.substring(k, result.indexOf("\"", k)));

					writer.write("\n");
					if (j + 1 == how_many - 1) {
						writer.write(result.substring(k, result.indexOf("\"", k)));
						writer.write("\n");
					}
					System.out.println("나 여기있어2");
					// System.out.println(j);
				}
			}
			writer.close();
	}

	}

	public static void main(String[] args) throws IOException {
		StringBuilder urlBuilder = new StringBuilder("https://openapi.gg.go.kr/MrktStoreM");
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=4f5be36dcb204f35a0786e7e1ab277c7");
		urlBuilder.append("&" + URLEncoder.encode("Type", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("pIndex", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("pSize", "UTF-8") + "=" + URLEncoder.encode("50", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("SIGUN_CD", "UTF-8") + "=" + URLEncoder.encode("41650", "UTF-8"));

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		System.out.println("Response code: " + conn.getResponseCode());
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();

		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}

		rd.close();
		

		//String all_results = sb.toString();
		//System.out.println(all_results);
		String all_results = "\"SIGUN_NM\":\"포천시\",\"SIGUN_CD\":\"41650\",\"FACLT_DIV_NM\":\"전통시장\",\"MARKET_NM\":\"일동5일장\",\"TELNO\":null,\"REFINE_LOTNO_ADDR\":\"경기도 포천시 일동면 기산리 94-36 주차장 일원\",\"REFINE_ROADNM_ADDR\":\"경기도 포천시 일동면 기산리 94-36 주차장 일원\",\"REFINE_ZIP_CD\":\"11117\",\"REFINE_WGS84_LOGT\":\"127.3201243147\",\"REFINE_WGS84_LAT\":\"37.9593507063\"},{\"SIGUN_NM\":\"포천시\",\"SIGUN_CD\":\"41650\",\"FACLT_DIV_NM\":\"전통시장\",\"MARKET_NM\":\"이동5일장\",\"TELNO\":456456456,\"REFINE_LOTNO_ADDR\":\"경기도 포천시 이동면 장암리 509-2, 장암4리 마을회관 일원\",\"REFINE_ROADNM_ADDR\":\"경기도 포천시 이동면 장암리 509-2, 장암4리 마을회관 일원\",\"REFINE_ZIP_CD\":\"11111\",\"REFINE_WGS84_LOGT\":\"127.3671989743\",\"REFINE_WGS84_LAT\":\"38.0354045010\"},{\"SIGUN_NM\":\"포천시\",\"SIGUN_CD\":\"41650\",\"FACLT_DIV_NM\":\"전통시장\",\"MARKET_NM\":\"양문5일장\",\"TELNO\":null,\"REFINE_LOTNO_ADDR\":\"경기도 포천시 영중면 양문리 810-1 일원\",\"REFINE_ROADNM_ADDR\":\"경기도 포천시 영중면 양문리 810-1 일원\",\"REFINE_ZIP_CD\":\"11129\",\"REFINE_WGS84_LOGT\":\"127.2431030999\",\"REFINE_WGS84_LAT\":\"38.0019033063\"},{\"SIGUN_NM\":\"포천시\",\"SIGUN_CD\":\"41650\",\"FACLT_DIV_NM\":\"전통시장\",\"MARKET_NM\":\"송우5일장\",\"TELNO\":456456771123,\"REFINE_LOTNO_ADDR\":\"경기도 포천시 송우리 138-2 일원\",\"REFINE_ROADNM_ADDR\":\"경기도 포천시 송우리 138-2 일원\",\"REFINE_ZIP_CD\":\"11179\",\"REFINE_WGS84_LOGT\":\"127.1459997600\",\"REFINE_WGS84_LAT\":\"37.8273084147\"},{\"SIGUN_NM\":\"포천시\",\"SIGUN_CD\":\"41650\",\"FACLT_DIV_NM\":\"전통시장\",\"MARKET_NM\":\"내리5일장\",\"TELNO\":6458302351684,\"REFINE_LOTNO_ADDR\":\"경기도 포천시 내촌면 내리 389-9 일원\",\"REFINE_ROADNM_ADDR\":\"경기도 포천시 내촌면 내리 389-9 일원\",\"REFINE_ZIP_CD\":\"11189\",\"REFINE_WGS84_LOGT\":\"127.2269151872\",\"REFINE_WGS84_LAT\":\"37.7850091095\"}]}]}";
		int[] name_index = new int[300];
		int[] addr_index = new int[300];
		int[] long_index = new int[300];
		int[] lati_index = new int[300];
		int[] pnum_index = new int[300];

		checking plz = new checking();

		plz.check(name_index, all_results, "MARKET_NM");
		plz.check(addr_index, all_results, "REFINE_ROADNM_ADDR");
		plz.check(long_index, all_results, "REFINE_WGS84_LOGT");
		plz.check(lati_index, all_results, "REFINE_WGS84_LAT");
		plz.check(pnum_index, all_results, "TELNO");

		// System.out.println(name_index);
		// System.out.println(addr_index);
		// System.out.println(long_index);
		// System.out.println(lati_index);
		// System.out.println(pnum_index);

		
		 Scanner scan = new Scanner(System.in);
	      System.out.println("SQL Programming Test");
	      
	      System.out.println("Connecting PostgreSQL database");
	      Connection conn1 = null;
	      Statement st = null;
	      ResultSet rs = null;
	      
	      String url1 = "jdbc:postgresql://localhost:5432/mydb";
	      String user = "danny";
	      String password = "1234";
	      
	      try {
	         conn1 = DriverManager.getConnection(url1, user, password);
	         st = conn1.createStatement();
	         rs = st.executeQuery("SELECT VERSION()");
	         
	         if(rs.next())
	            System.out.println(rs.getString(1));
	      }catch(SQLException sqlEX) {
	         System.out.println(sqlEX);
	      }
		
		
		
		
		 System.out.println("Creating food, brand, stock, market  relations");
	      try {
	         String query1 = "create table food(fName varchar(20),categoryID int)";
	         String query2 = "create table brand(bName varchar(20), primary key(bName))";
	         String query3 = "create table Stock(quantitiy int, fName_Food varchar(20), bName_Brand varchar(20), mID_Market varchar(20), primary key(fName_Food, bName_Brand, mID_Market), "
	         		+ "foreign key(fName_Food) references food(fName), foreign key(bName_Brand) references Brand(bName), foreign key(mID_Market) references Market(mID))";
	         String query4 = "create table Market(mID int, mName varchar(20), Location varchar(40), Longitude float, Latitude float, phoneNumber varchar(20))";
	         conn1.executeUpdate(query1);
	         conn1.executeUpdate(query2);
	         conn1.executeUpdate(query3);
	         conn1.executeUpdate(query4);
	         System.out.println("finished");
	      }catch(SQLException sqlEX) {
	         System.out.println(sqlEX);
	      }
		
		
	      conn1.disconnect();
	}

}
