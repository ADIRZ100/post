/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package getinfo.fromserver;


import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * @author user
 */

public class GetInfoFromServer {

    private static HttpURLConnection Connection;
    private static String sucses = "post work !!";
    
    public static void main(String[] args) throws IOException {
        postServer();
        //getFromTheServer();
    }
    
    
    // this func gave as info from the server with the help of "GET "
    public static void getFromTheServer(){
     BufferedReader redaer;
        String line;
        StringBuffer responesConnect = new StringBuffer();
        
        try{
            URL url = new URL("http://localhost:8080/mavenproject3/resources/javaee8//textfile");
            Connection = (HttpURLConnection)url.openConnection();
            
            //requset connection
            Connection.setRequestMethod("GET");
            Connection.setConnectTimeout(5000);             
            Connection.setReadTimeout(5000);
            
            
            
            int status = Connection.getResponseCode();
            System.out.println(status);
            if(status > 299){
                redaer = new BufferedReader(new InputStreamReader(Connection.getErrorStream()));
                while((line = redaer.readLine()) != null){
                    responesConnect.append(line);
                }
                redaer.close();
            }
            else{
                redaer = new BufferedReader(new InputStreamReader(Connection.getInputStream()));
                while((line = redaer.readLine()) != null){
                    responesConnect.append(line);
                }
                redaer.close();
            }
            System.out.println(responesConnect.toString());
            
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            Connection.disconnect();
        }
    }
    public static void postServer() throws IOException {
        
        URL obj = new URL ("http://localhost:8080/mavenproject3/resources/generic/adir");
        Connection = (HttpURLConnection)obj.openConnection();
        Connection.setRequestMethod("POST");
        Connection.setRequestProperty("Content-Type","application/x-www-form-urlendcode");
        Connection.setRequestProperty("Content-Length", String.valueOf(sucses.length()));
        Connection.setDoOutput(true);
        Connection.getOutputStream().write(sucses.getBytes("UTF-8"));
        
            int responseCode = Connection.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);
            
             if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(Connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in .readLine()) != null) {
                response.append(inputLine);
            } in .close();

            // print result
            System.out.println(response.toString());
        } else {
            System.out.println("POST request not worked");
        }

    }
    
    
}
