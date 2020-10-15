package com.iphayao.linebot;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
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
import org.codehaus.jettison.json.JSONObject;

import com.iphayao.linebot.*;


public class Callrest {
	//LineRet lineret;
	Configs config=new Configs();
	String web=config.getWeb(); 
	String onlineorder=config.getOnlineOrder();
	String orderstatus=config.getOrderStatus();
	String productprice=config.getProductPrice();
	String checkbalance=config.getCheckBalance();
	String payment=config.getPayment();
	String paymentbarcode=config.getPaymentBarCode();
	String paymentqrcode=config.getPaymentQrCode();
	String contact=config.getContact();
	String tokenlogin=config.getTokenLogin();
	String token=config.getToken();
	String checkid=config.getCheckID();
	String cvname=config.getCVName();
	String follow=config.getFollow();
	String unfollow=config.getUnFollow();
	String profilename=config.getProfileName();
	String imageflexhero=config.getImageFlexHero();
	String steptest=config.getStepTest();
	String strNewLine=config.getStrNewLine();
	
    
    
    public String getOnlineOrder(String Userid)  throws ClientProtocolException, IOException  {
    	 // Create a Logger 
        Logger logger 
            = Logger.getLogger( 
            		Callrest.class.getName()+"Method : getOnlineOrder Userid = "+Userid); 
    	
        String Data="";
        try {
        	
    	URL obj = new URL(web+onlineorder);
        	
      	 
      	 HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
   		    postConnection.setRequestMethod("POST");
   		  
   		   postConnection.setRequestProperty("Content-Type", "application/json");
   		   postConnection.setDoOutput(true);
   		   postConnection.setConnectTimeout(10000);
   		   
   		OutputStream os = postConnection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(Userid);

        writer.flush();
        writer.close();
        os.close();
   		   
   		   
   		   System.out.println("Call Rest : postConnection "+postConnection.getResponseCode());

   			if (postConnection.getResponseCode()!=200&&postConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
   				throw new RuntimeException("Failed : HTTP error code : "
   					+ postConnection.getResponseCode());
   			}

   			BufferedReader br = new BufferedReader(new InputStreamReader(
   					(postConnection.getInputStream()),"UTF8"));

   			String output;
   			
   			System.out.println("Output from Server .... \n");
   			while ((output = br.readLine()) != null) {
   				output = output.replace(strNewLine, "\r\n");  
   				Data+=output;
   				System.out.println(output);
   			}

   			postConnection.disconnect();
       	
    		}catch(Exception e) {
      		
      		logger.info("Error "+e.toString());
      		
     	 }       	
        return Data;
       	
    }
    
    
    
    
    ///nouse
    public String getOrderStatus(String Userid) throws ClientProtocolException, IOException  {
    	 // Create a Logger 
        Logger logger 
            = Logger.getLogger( 
            		Callrest.class.getName()+"Method : getOrderStatus Userid = "+Userid); 
    	//////
        String Data="";
        try {
    	URL obj = new URL(web+orderstatus);
     	 
     	 HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
  		    postConnection.setRequestMethod("POST");
  		  
  		   postConnection.setRequestProperty("Content-Type", "application/json");
  		   postConnection.setDoOutput(true);
  		   postConnection.setConnectTimeout(10000);
  		   
  		 OutputStream os = postConnection.getOutputStream();
         BufferedWriter writer = new BufferedWriter(
                 new OutputStreamWriter(os, "UTF-8"));
         writer.write(Userid);

         writer.flush();
         writer.close();
         os.close();
  		   
  		   
  		   System.out.println("Call Rest : postConnection "+postConnection.getResponseCode());

  			if (postConnection.getResponseCode()!=200&&postConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
  				throw new RuntimeException("Failed : HTTP error code : "
  					+ postConnection.getResponseCode());
  			}

  			BufferedReader br = new BufferedReader(new InputStreamReader(
  					(postConnection.getInputStream()),"UTF8"));

  			String output;
  			
  			System.out.println("Output from Server .... \n");
  			while ((output = br.readLine()) != null) {
  				Data+=output;
  				System.out.println(output);
  			}

  			postConnection.disconnect();
      
    		}catch(Exception e) {
      		
      		logger.info("Error "+e.toString());
      		
     	 }    
    	return Data;
    }
     ////// nouse////////
    
    /////nouse
    public String getProductPrice(String Userid) throws ClientProtocolException, IOException {
    	 // Create a Logger 
        Logger logger 
            = Logger.getLogger( 
            		Callrest.class.getName()+"Method : getProductPrice  Userid = "+Userid); 
    	//lineret=new LineRet();
        String Data="";
        try {
    	 URL obj = new URL(web+productprice);
    	 
    	 HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		    postConnection.setRequestMethod("POST");
		  
		   postConnection.setRequestProperty("Content-Type", "application/json");
		   postConnection.setDoOutput(true);
		   postConnection.setConnectTimeout(10000);
		   
		   OutputStream os = postConnection.getOutputStream();
	         BufferedWriter writer = new BufferedWriter(
	                 new OutputStreamWriter(os, "UTF-8"));
	         writer.write(Userid);

	         writer.flush();
	         writer.close();
	         os.close();
		   
		   System.out.println("Call Rest : postConnection "+postConnection.getResponseCode());

			if (postConnection.getResponseCode()!=200&&postConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ postConnection.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(postConnection.getInputStream()),"UTF8"));

			String output;
			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				Data+=output;
				System.out.println(output);
			}
			
			

			postConnection.disconnect();
			//System.out.println(Data);
			//System.out.println("Userid"+Userid);
//			int retResult_start = 14;
//	        int retResult_end =Data.indexOf(",",14);
	      //  System.out.println(retResult_end);
//	        String retResult =Data.substring(retResult_start,retResult_end-1);
	        
	        
    	
    	
    		}catch(Exception e) {
      		
      		logger.info("Error "+e.toString());
      		
     	 }
        return Data;
    }
    ////// nouse////////
    
    
    public String getCheckBalance(String Userid)throws ClientProtocolException, IOException {
    	Logger logger 
        = Logger.getLogger( 
        		Callrest.class.getName()+"Method : getCheckBalance  Userid = "+Userid);
    	String Data="";
    	try {
    	URL obj = new URL(web+checkbalance);
   	 
   	 HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		    postConnection.setRequestMethod("POST");
		  
		   postConnection.setRequestProperty("Content-Type", "application/json");
		   postConnection.setDoOutput(true);
		   postConnection.setConnectTimeout(10000);
		   
		   OutputStream os = postConnection.getOutputStream();
	         BufferedWriter writer = new BufferedWriter(
	                 new OutputStreamWriter(os, "UTF-8"));
	         writer.write(Userid);

	         writer.flush();
	         writer.close();
	         os.close();
		   
		   
		   System.out.println("Call Rest : postConnection "+postConnection.getResponseCode());

			if (postConnection.getResponseCode()!=200&&postConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ postConnection.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(postConnection.getInputStream()),"UTF8"));

			String output;
			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				Data+=output+"\n";
				System.out.println(output);
			}

			postConnection.disconnect();
    	
    		}catch(Exception e) {
      		
      		logger.info("Error "+e.toString());
      		
     	 }    
    	return Data;
    }
    
    
    
    public String getPaymentFileId(String Userid,int type) throws ClientProtocolException, IOException  {
    	String type_="";
    	if(type==1)type_="BarCode";
    	else type_="QrCode";
    	
    	Logger logger 
        = Logger.getLogger( 
        		Callrest.class.getName()+"Method : getPayment  Userid = "+Userid+" ,type = "+type_);
    	String Data="";
    	try {
    	URL obj=null;
    	
    	if(type==1)obj = new URL(web+paymentbarcode);
    	else obj = new URL(web+paymentqrcode);
    	 
     	 
     	 HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
  		    postConnection.setRequestMethod("POST");
  		  
  		   postConnection.setRequestProperty("Content-Type", "application/json");
  		   postConnection.setDoOutput(true);
  		   postConnection.setConnectTimeout(10000);
  		   
  		   
  		 OutputStream os = postConnection.getOutputStream();
         BufferedWriter writer = new BufferedWriter(
                 new OutputStreamWriter(os, "UTF-8"));
         writer.write(Userid);

         writer.flush();
         writer.close();
         os.close();
  		   
  		   
  		   System.out.println("Call Rest : postConnection "+postConnection.getResponseCode());

  			if (postConnection.getResponseCode()!=200&&postConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
  				throw new RuntimeException("Failed : HTTP error code : "
  					+ postConnection.getResponseCode());
  			}

  			BufferedReader br = new BufferedReader(new InputStreamReader(
  					(postConnection.getInputStream()),"UTF8"));

  			String output;
  			
  			System.out.println("Output from Server .... \n");
  			while ((output = br.readLine()) != null) {
  				Data+=output;
  				System.out.println(output);
  			}

  			postConnection.disconnect();
      	
    		}catch(Exception e) {
      		
      		logger.info("Error "+e.toString());
      		
     	 }
    	return Data;
    }
    
    
    
    
    public String getContact(String Userid) throws ClientProtocolException, IOException  {
    	
    	Logger logger 
        = Logger.getLogger( 
        		Callrest.class.getName()+"Method : getContact  Userid = "+Userid);
    	String Data="";
    	try {
    	URL obj = new URL(web+contact);
     	 
     	 HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
  		    postConnection.setRequestMethod("POST");
  		  
  		   postConnection.setRequestProperty("Content-Type", "application/json");
  		   postConnection.setDoOutput(true);
  		   postConnection.setConnectTimeout(10000);
  		   
  		 OutputStream os = postConnection.getOutputStream();
         BufferedWriter writer = new BufferedWriter(
                 new OutputStreamWriter(os, "UTF-8"));
         writer.write(Userid);

         writer.flush();
         writer.close();
         os.close();
  		   
  		   
  		   System.out.println("Call Rest : postConnection "+postConnection.getResponseCode());

  			if (postConnection.getResponseCode()!=200&&postConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
  				throw new RuntimeException("Failed : HTTP error code : "
  					+ postConnection.getResponseCode());
  			}

  			BufferedReader br = new BufferedReader(new InputStreamReader(
  					(postConnection.getInputStream()),"UTF8"));

  			String output;
  			
  			System.out.println("Output from Server .... \n");
  			while ((output = br.readLine()) != null) {
  				Data+=output;
  				System.out.println(output);
  			}

  			postConnection.disconnect();
      	
    	}catch(Exception e) {
      		
      		logger.info("Error "+e.toString());
      		
     	 }
    	return Data;
    }
    
     public String getTokenLogin(String Userid)throws ClientProtocolException, IOException {
    	 
    	 Logger logger 
         = Logger.getLogger( 
         		Callrest.class.getName()+"Method : getTokenLogin  Userid = "+Userid);
    	 String Data="";
    	 try {
    	 	URL obj = new URL(web+tokenlogin);
     	 
     	 HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
  		    postConnection.setRequestMethod("POST");
  		  
  		   postConnection.setRequestProperty("Content-Type", "application/json");
  		   postConnection.setDoOutput(true);
  		   postConnection.setConnectTimeout(10000);
  		   
  		 OutputStream os = postConnection.getOutputStream();
         BufferedWriter writer = new BufferedWriter(
                 new OutputStreamWriter(os, "UTF-8"));
         writer.write(Userid);

         writer.flush();
         writer.close();
         os.close();
  		   
  		   
  		   System.out.println("Call Rest : postConnection "+postConnection.getResponseCode());

  			if (postConnection.getResponseCode()!=200&&postConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
  				throw new RuntimeException("Failed : HTTP error code : "
  					+ postConnection.getResponseCode());
  			}

  			BufferedReader br = new BufferedReader(new InputStreamReader(
  					(postConnection.getInputStream()),"UTF8"));

  			String output;
  			
  			System.out.println("Output from Server .... \n");
  			while ((output = br.readLine()) != null) {
  				Data+=output;
  				System.out.println(output);
  			}

  			postConnection.disconnect();
      	
    	 }catch(Exception e) {
       		
       		logger.info("Error "+e.toString());
       		
      	 }
    	 return Data;
     }
     
public String getToken(String Userid)throws ClientProtocolException, IOException {
    	 
    	 Logger logger 
         = Logger.getLogger( 
         		Callrest.class.getName()+"Method : getToken  Userid = "+Userid);
    	 String Data="";
    	 try {
    	 	URL obj = new URL(web+token);
     	 
     	 HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
  		    postConnection.setRequestMethod("POST");
  		  
  		   postConnection.setRequestProperty("Content-Type", "application/json");
  		   postConnection.setDoOutput(true);
  		   postConnection.setConnectTimeout(10000);
  		   
  		 OutputStream os = postConnection.getOutputStream();
         BufferedWriter writer = new BufferedWriter(
                 new OutputStreamWriter(os, "UTF-8"));
         writer.write(Userid);

         writer.flush();
         writer.close();
         os.close();
  		   
  		   
  		   System.out.println("Call Rest : postConnection "+postConnection.getResponseCode());

  			if (postConnection.getResponseCode()!=200&&postConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
  				throw new RuntimeException("Failed : HTTP error code : "
  					+ postConnection.getResponseCode());
  			}

  			BufferedReader br = new BufferedReader(new InputStreamReader(
  					(postConnection.getInputStream()),"UTF8"));

  			String output;
  			
  			System.out.println("Output from Server .... \n");
  			while ((output = br.readLine()) != null) {
  				Data+=output;
  				System.out.println(output);
  			}

  			postConnection.disconnect();
      	
    	 }catch(Exception e) {
       		
       		logger.info("Error "+e.toString());
       		
      	 }
    	 return Data;
     }
     
     
     public String getCheckID(String Userid)throws ClientProtocolException, IOException {
    	 
    	 Logger logger 
         = Logger.getLogger( 
         		Callrest.class.getName()+"Method : getCheckID  Userid = "+Userid);
    	 String Data="";
    	 try {
 	 	URL obj = new URL(web+checkid);
  	 
  	 HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		    postConnection.setRequestMethod("POST");
		  
		   postConnection.setRequestProperty("Content-Type", "application/json");
		   postConnection.setDoOutput(true);
		   postConnection.setConnectTimeout(10000);
		   
		   OutputStream os = postConnection.getOutputStream();
	         BufferedWriter writer = new BufferedWriter(
	                 new OutputStreamWriter(os, "UTF-8"));
	         writer.write(Userid);

	         writer.flush();
	         writer.close();
	         os.close();
		   
		   
		   System.out.println("Call Rest : postConnection "+postConnection.getResponseCode());

			if (postConnection.getResponseCode()!=200&&postConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ postConnection.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(postConnection.getInputStream()),"UTF8"));

			String output;
			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				Data+=output;
				System.out.println(output);
			}

			postConnection.disconnect();
   
    	 }catch(Exception e) {
      		
      		logger.info("Error "+e.toString());
      		
     	 }
    		return Data;
  }
     
public String getCVName(String Userid)throws ClientProtocolException, IOException {
    	 
    	 Logger logger 
         = Logger.getLogger( 
         		Callrest.class.getName()+"Method : getCVName  Userid = "+Userid);
    	 String Data="";
    	 try {
 	 	URL obj = new URL(web+cvname);
  	 
  	 HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		    postConnection.setRequestMethod("POST");
		  
		   postConnection.setRequestProperty("Content-Type", "application/json");
		   postConnection.setDoOutput(true);
		   postConnection.setConnectTimeout(10000);
		   
		   OutputStream os = postConnection.getOutputStream();
	         BufferedWriter writer = new BufferedWriter(
	                 new OutputStreamWriter(os, "UTF-8"));
	         writer.write(Userid);

	         writer.flush();
	         writer.close();
	         os.close();
		   
		   
		   System.out.println("Call Rest : postConnection "+postConnection.getResponseCode());

			if (postConnection.getResponseCode()!=200&&postConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ postConnection.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(postConnection.getInputStream()),"UTF8"));

			String output;
			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				Data+=output;
				System.out.println(output);
			}

			postConnection.disconnect();
   
    	 }catch(Exception e) {
      		
      		logger.info("Error "+e.toString());
      		
     	 }
    		return Data;
  }

public String getProfileName(String Userid)throws ClientProtocolException, IOException {
	 
	 Logger logger 
  = Logger.getLogger( 
  		Callrest.class.getName()+"Method : getProfileName  Userid = "+Userid);
	 String Data="";
	 try {
	URL obj = new URL(web+profilename);
	 
	 HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	  
	   postConnection.setRequestProperty("Content-Type", "application/json");
	   postConnection.setDoOutput(true);
	   postConnection.setConnectTimeout(10000);
	   
	   OutputStream os = postConnection.getOutputStream();
      BufferedWriter writer = new BufferedWriter(
              new OutputStreamWriter(os, "UTF-8"));
      writer.write(Userid);

      writer.flush();
      writer.close();
      os.close();
	   
	   
	   System.out.println("Call Rest : postConnection "+postConnection.getResponseCode());

		if (postConnection.getResponseCode()!=200&&postConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ postConnection.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(postConnection.getInputStream()),"UTF8"));

		String output;
		
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			Data+=output;
			System.out.println(output);
		}

		postConnection.disconnect();

	 }catch(Exception e) {
		
		logger.info("Error "+e.toString());
		
	 }
		return Data;
}

public String CheckFollow(String Token)throws ClientProtocolException, IOException {
	 
	 Logger logger 
   = Logger.getLogger( 
   		Callrest.class.getName()+"Method : CheckFollow  Userid = "+Token);
	 String Data="";
	 try {
	URL obj = new URL(web+follow);
	 
	 HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	  
	   postConnection.setRequestProperty("Content-Type", "application/json");
	   postConnection.setDoOutput(true);
	   postConnection.setConnectTimeout(10000);
	   
	   OutputStream os = postConnection.getOutputStream();
       BufferedWriter writer = new BufferedWriter(
               new OutputStreamWriter(os, "UTF-8"));
       writer.write(Token);

       writer.flush();
       writer.close();
       os.close();
	   
	   
	   System.out.println("Call Rest : postConnection "+postConnection.getResponseCode());

		if (postConnection.getResponseCode()!=200&&postConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ postConnection.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(postConnection.getInputStream()),"UTF8"));

		String output;
		
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			Data+=output;
			System.out.println(output);
		}

		postConnection.disconnect();

	 }catch(Exception e) {
		
		logger.info("Error "+e.toString());
		
	 }
		return Data;
}



public String UnFollow(String Token)throws ClientProtocolException, IOException {
	 
	 Logger logger 
    = Logger.getLogger( 
    		Callrest.class.getName()+"Method : UnFollow  Userid = "+Token);
	 String Data="";
	 try {
 	URL obj = new URL(web+unfollow);
	 
	 HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	  
	   postConnection.setRequestProperty("Content-Type", "application/json");
	   postConnection.setDoOutput(true);
	   postConnection.setConnectTimeout(10000);
	   
	   OutputStream os = postConnection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(Token);

        writer.flush();
        writer.close();
        os.close();
	   
	   
	   System.out.println("Call Rest : postConnection "+postConnection.getResponseCode());

		if (postConnection.getResponseCode()!=200&&postConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ postConnection.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(postConnection.getInputStream()),"UTF8"));

		String output;
		
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			Data+=output;
			System.out.println(output);
		}

		postConnection.disconnect();

	 }catch(Exception e) {
 		
 		logger.info("Error "+e.toString());
 		
	 }
		return Data;
}


public String getImageFlexHero(String Userid)throws ClientProtocolException, IOException {
	 
	 Logger logger 
   = Logger.getLogger( 
   		Callrest.class.getName()+"Method : getImageFlexHero  Userid = "+Userid);
	 String Data="";
	 try {
	URL obj = new URL(web+imageflexhero);
	 
	 HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
	    postConnection.setRequestMethod("POST");
	  
	   postConnection.setRequestProperty("Content-Type", "application/json");
	   postConnection.setDoOutput(true);
	   postConnection.setConnectTimeout(10000);
	   
	   OutputStream os = postConnection.getOutputStream();
       BufferedWriter writer = new BufferedWriter(
               new OutputStreamWriter(os, "UTF-8"));
       writer.write(Userid);

       writer.flush();
       writer.close();
       os.close();
	   
	   
	   System.out.println("Call Rest : postConnection "+postConnection.getResponseCode());

		if (postConnection.getResponseCode()!=200&&postConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
			throw new RuntimeException("Failed : HTTP error code : "
				+ postConnection.getResponseCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(postConnection.getInputStream()),"UTF8"));

		String output;
		
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			Data+=output;
			System.out.println(output);
		}

		postConnection.disconnect();

	 }catch(Exception e) {
		
		logger.info("Error "+e.toString());
		
	 }
		return Data;
}


public String getStepTest(String Userid) throws ClientProtocolException, IOException  {
	 
	 Logger logger 
	   = Logger.getLogger( 
	   		Callrest.class.getName()+"Method : getStepTest  Userid = "+Userid);
		 String Data="";
		 try {
		URL obj = new URL(web+steptest);
		 
		 HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
		    postConnection.setRequestMethod("POST");
		  
		   postConnection.setRequestProperty("Content-Type", "application/json");
		   postConnection.setDoOutput(true);
		   postConnection.setConnectTimeout(10000);
		   
		   OutputStream os = postConnection.getOutputStream();
	       BufferedWriter writer = new BufferedWriter(
	               new OutputStreamWriter(os, "UTF-8"));
	       writer.write(Userid);

	       writer.flush();
	       writer.close();
	       os.close();
		   
		   
		   System.out.println("Call Rest : postConnection "+postConnection.getResponseCode());

			if (postConnection.getResponseCode()!=200&&postConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ postConnection.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(postConnection.getInputStream()),"UTF8"));

			String output;
			
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				Data+=output;
				System.out.println(output);
			}

			postConnection.disconnect();

		 }catch(Exception e) {
			
			logger.info("Error "+e.toString());
			
		 }
			return Data;
}


     
     public String getTest(String Userid) throws ClientProtocolException, IOException  {
    	 
    	 Logger logger 
         = Logger.getLogger( 
         		Callrest.class.getName()+"Method : getTest  Userid = "+Userid);
    	 String Data="";
    	 try {
     	URL obj = new URL(web+"rest/prsproductprice/v1/PostProductPrice");
      	 
      	 HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
   		    postConnection.setRequestMethod("POST");
   		  
   		   postConnection.setRequestProperty("Content-Type", "application/json");
   		   postConnection.setDoOutput(true);
   		   postConnection.setConnectTimeout(10000);
   		   
   		OutputStream os = postConnection.getOutputStream();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(os, "UTF-8"));
        writer.write(Userid);

        writer.flush();
        writer.close();
        os.close();
   		   
   		   
   		   System.out.println("Call Rest : postConnection "+postConnection.getResponseCode());

   			if (postConnection.getResponseCode()!=200&&postConnection.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
   				throw new RuntimeException("Failed : HTTP error code : "
   					+ postConnection.getResponseCode());
   			}

   			BufferedReader br = new BufferedReader(new InputStreamReader(
   					(postConnection.getInputStream()),"UTF8"));

   			String output;
         //  ArrayList<String> a=new ArrayList<String>();
   			System.out.println("Output from Server .... \n");
   			while ((output = br.readLine()) != null) {
   				Data+=output;
   				System.out.println(output);
   			//	a.add(output);
   			}

   			postConnection.disconnect();
   		 
   			
    	 
//   			for(String x:a) {
//   				System.out.println(x);
//   				
//   			}
    	 
    	// JSONObject obj = new JSONObject();
    	 
    	// String Data="";
    	 
       	
    	 }catch(Exception e) {
     		
     		logger.info("Error "+e.toString());
     		
    	 }
    	 return Data;
     }
    
    
}




