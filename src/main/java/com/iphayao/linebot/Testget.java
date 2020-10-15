//package com.iphayao.linebot;
//
//import java.io.StringReader;
//
//import javax.xml.bind.JAXBContext;
//import javax.xml.bind.Unmarshaller;
//
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.util.EntityUtils;
//import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
//
//
//public class Testget {
//	public static void demoGetRESTAPI() throws Exception 
//	{
//	    DefaultHttpClient httpClient = new DefaultHttpClient();
//	    try
//	    {
//	        //Define a HttpGet request; You can choose between HttpPost, HttpDelete or HttpPut also.
//	        //Choice depends on type of method you will be invoking.
//	        HttpGet getRequest = new HttpGet("http://localhost:8080/RESTfulDemoApplication/user-management/users/10");
//	         
//	        //Set the API media type in http accept header
//	        getRequest.addHeader("accept", "application/xml");
//	          
//	        //Send the request; It will immediately return the response in HttpResponse object
//	        HttpResponse response = httpClient.execute(getRequest);
//	         
//	        //verify the valid error code first
//	        int statusCode = response.getStatusLine().getStatusCode();
//	        if (statusCode != 200) 
//	        {
//	            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
//	        }
//	         
//	        //Now pull back the response object
//	        HttpEntity httpEntity = response.getEntity();
//	        String apiOutput = EntityUtils.toString(httpEntity);
//	         
//	        //Lets see what we got from API
//	        System.out.println(apiOutput); //<user id="10"><firstName>demo</firstName><lastName>user</lastName></user>
//	         
//	        //In realtime programming, you will need to convert this http response to some java object to re-use it.
//	        //Lets see how to jaxb unmarshal the api response content
//	        JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
//	        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
//	        User user = (User) jaxbUnmarshaller.unmarshal(new StringReader(apiOutput));
//	         
//	        //Verify the populated object
//	        System.out.println(user.getId());
//	        System.out.println(user.getFirstName());
//	        System.out.println(user.getLastName());
//	    }
//	    finally
//	    {
//	        //Important: Close the connect
//	        httpClient.getConnectionManager().shutdown();
//	    }
//	}
//}
