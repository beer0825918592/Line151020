package com.iphayao.linebot;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriBuilder;







//throws ClientProtocolException, IOException
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.jersey.json.impl.provider.entity.JSONRootElementProvider;


import java.io.IOException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.apache.http.client.ClientProtocolException;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.representation.Form;









public class Callrest {
    public String getCV(String Userid)throws ClientProtocolException, IOException {
    	 // Create a Logger 
        Logger logger 
            = Logger.getLogger( 
            		Callrest.class.getName()); 

       // "http://localhost:8082/rest/prsorderservice/v1/Post"
    	try {
    		System.out.println("Call Rest : Satrt ");
    		
    		URL url = new URL("http://localhost:8082/rest/prsorderservice/v1/Post");
    		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    		conn.setDoOutput(true);
    		conn.setRequestMethod("POST");
    		conn.setRequestProperty("Content-Type", "application/json");
    		
    		System.out.println("Call Rest : Input ");
    		
    		String input = "{\"OrderID\":1}";
    		//String input = "{\"qty\":100,\"name\":\"iPad 4\"}";
    		System.out.println("Call Rest : OS ");
    		OutputStream os = conn.getOutputStream();
    		System.out.println("Call Rest : 69 ");
    		os.write(input.getBytes());
    		System.out.println("Call Rest : 71 ");
    		os.flush();
    		System.out.println("Call Rest : 73 ");
    		System.out.println("Call Rest : OS Get");
    		if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
    			throw new RuntimeException("Failed : HTTP error code : "
    				+ conn.getResponseCode());
    		}
            
    		System.out.println("Call Rest : BufferedReader ");
    		
    		BufferedReader br = new BufferedReader(new InputStreamReader(
    				(conn.getInputStream())));

    		String output;
    		System.out.println("Output from Server .... \n");
    		while ((output = br.readLine()) != null) {
    			System.out.println(output);
    		}

    		conn.disconnect();
       	  
       	  
    	}catch(Exception e) {
    		
    		System.err.println("Error "+e);
    		
    		
    	}
    	
    	return "x";
    	
    } 
    	
    
}
