package i9_everify_demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;

/**
 * 
 * This class includes various HTTP methods - GET, POST, PATCH. <br>
 * There are also methods to extract the JSON and payload.
 * 
 * @author Varun Shah
 *
 */
public class HTTPMethods {
	
	/**
	 * Performs an HTTP GET request
	 * 
	 * @param url url of the GET request
	 * @param authentication authorization for the GET request
	 * @return the HTTP response of the GET request 
	 */
	public static HttpResponse get(String url, String authentication){
		
		try{
			 
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet get = new HttpGet(url);
			
			//add request headers
			/*
			 * to add more request headers, this would be a good play
			 * get.addHeader(headerKey, headerValue);
			 */
			get.addHeader("Authorization", authentication);
			get.addHeader("Content-Type", "application/json");
			
			HttpResponse response = client.execute(get); //executes the GET request
			return response;
			
		} catch(Exception e){
			
			System.out.println("exception: " + e);
		}
		
		return null;
	}
	
	/**
	 * Performs an HTTP POST request
	 * 
	 * @param url url of the POST request
	 * @param authentication authorization for the POST request
	 * @param payload payload of the POST request
	 * @return the HTTP response of the POST request
	 */
	public static HttpResponse post(String url, String authentication, String payload){
		
		try{

			HttpClient client = HttpClientBuilder.create().build();     
			HttpPost post = new HttpPost(url);
			
			//add request headers
			/*
			 * to add more request headers, this would be a good play
			 * get.addHeader(headerKey, headerValue);
			 */
			post.addHeader("Authorization", authentication);
			post.addHeader("Content-Type", "application/json");
			
			//set payload
			post.setEntity(new StringEntity(payload, ContentType.create("application/json")));

			HttpResponse response = client.execute(post);
			return response;
			
		} catch(Exception e){
			
			System.out.println("exception: " + e);
		}
		
		return null;
	}
	
	/**
	 * Performs an HTTP PATCH request
	 * 
	 * @param url url of the PATCH request
	 * @param authentication authorization for the PATCH request
	 * @param payload payload of the PATCH request
	 * @return the HTTP response of the PATCH request
	 */
	public static HttpResponse patch(String url, String authentication, String payload){
		
		try{

			HttpClient client = HttpClientBuilder.create().build();     
			HttpPatch patch = new HttpPatch(url);
			
			//add request headers
			/*
			 * to add more request headers, this would be a good play
			 * get.addHeader(headerKey, headerValue);
			 */
			patch.addHeader("Authorization", authentication);
			patch.addHeader("Content-Type", "application/json");
			
			//set payload
			patch.setEntity(new StringEntity(payload, ContentType.create("application/json")));

			HttpResponse response = client.execute(patch);
			return response;
			
		} catch(Exception e){
			
			System.out.println("exception: " + e);
		}
		
		return null;
	}
	
	/**
	 * Extracts the JSON entity from an HTTP response
	 * 
	 * @param response the HTTP response which contains the JSON entity
	 * @return the extracted JSON entity
	 */
	public static JSONObject extractJSON(HttpResponse response){
		
		JSONObject json = null;
		String responseString = "";
		
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			String line = "";
			while ((line = br.readLine()) != null){
				responseString += line;
			}
		} catch(Exception e){
			System.out.println("exception: " + e);			
		}
		
		json = new JSONObject(responseString);
		
		return json;
	}
	
	/**
	 * Extract the payload from an HTTP request
	 * 
	 * @param request the HTTP request which contains the payload
	 * @return the extract payload as a JSON object
	 */
	public static JSONObject extractPayload(HttpServletRequest request){
		
		String payload = "";
		try {
			BufferedReader br = request.getReader();
			String line = "";
			while((line = br.readLine()) != null){
				payload += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new JSONObject(payload);
	}

}
