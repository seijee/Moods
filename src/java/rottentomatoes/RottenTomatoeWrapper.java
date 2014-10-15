/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rottentomatoes;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author Adi
 */
public class RottenTomatoeWrapper {
    
    public static String rtAPIKey = "43byvtf8afsgw3j885gk97cz";
    public static String rtCall = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey="+rtAPIKey+"&page_limit=50&q=";
    
    public String callRottenTomatoes(String title){
        
        URL url;
         try {
			String call = rtCall + title;
			url = new URL(call);
			URLConnection conn = url.openConnection();
 
			// open the stream and put it into BufferedReader
			BufferedReader br = new BufferedReader(
                               new InputStreamReader(conn.getInputStream()));
 
			String inputLine;
                        String JS="";
 
 
			while ((inputLine = br.readLine()) != null) {
				JS = JS + inputLine; 
			}
                        
                        JSONObject js = new JSONObject(JS);
                        System.out.println(js.toString());
                        JSONArray movies = js.getJSONArray("movies");
                        
                        for(int i=0; i<movies.length(); i++){
                            JSONObject movie = movies.getJSONObject(i);
                            String rtTitle = (String)movie.get("title");
                            
                            if(rtTitle.equals(title)){
                                JSONObject posters = movie.getJSONObject("posters");
                                
                                String image = (String)posters.get("detailed");
                                image = image.replaceAll("tmb.jpg", "ori.jpg");
                                return image;
                                
                            }
                            //System.out.println("ans= "+movie.get("synopsis"));
                        }
                        
			br.close();
 
			
 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
         return "";
    }
}
