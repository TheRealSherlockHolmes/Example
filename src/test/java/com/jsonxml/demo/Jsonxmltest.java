package com.jsonxml.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;  
import org.apache.log4j.LogManager;  
import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.Collection;
import org.json.JSONException;
import org.json.JSONObject;
import io.restassured.response.Response;


import org.json.JSONArray;



@RunWith(Parameterized.class)
public class Jsonxmltest {
	//private static final Logger LOGGER = LogManager.getLogger(Jsonxmltest.class.getName());
	private String Api1,Api2;
	public Jsonxmltest(String Api1, String Api2) {
	      this.Api1 = Api1;
	      this.Api2 = Api2;
	   }
	@Parameterized.Parameters
	   public static Collection<Object[]> Apis() throws IOException {
	      return TestRunner.getList();
	   }
	
	@Test
	public void getTest() throws JSONException, IOException {
		
		//specify baseurl
			Response response1 = TestRunner.getResponse(Api1);
			//creating response object for xml reponse
			Response response2 = TestRunner.getResponse(Api2);
//			System.out.println(response1.getHeaders());
			//	print response body in consol
			String respbody1 = response1.getBody().asString();
			
			//System.out.println("response1 body is:" +respbody1);
			String respbody2 = response2.getBody().asString();
			//System.out.println("response2 body is:" +respbody2);
			//Header Content validation
			int statuscode1= response1.getStatusCode();
			int statuscode2= response2.getStatusCode();
//			String statusLine2 = response2.statusLine();
//			String contentType2 = response2.contentType();
//			String contentType1 = response1.contentType();
//			System.out.println("response status is:" +statuscode1);
//			System.out.println("response status is:" +statuscode2);
//			System.out.println("response2 statusLine is:" +statusLine2 );
//			System.out.println("response1 contenttype is:" +contentType1 );
//			System.out.println("response2 contenttype is:" +contentType2 );
			Assert.assertEquals(statuscode1, statuscode2);

			//checking the content of xml and json reponse are similar

			//storing converted xml response body to jsonObject
			JSONObject xmlJSONString=new JSONObject(dataUtilities.Convert(respbody2));
			//System.out.println("converted xml to json data:" +xmlJSONString.toString());
			String xmlson=xmlJSONString.toString().replace("{\"Values\":{\"item\":", "");
			xmlson=xmlson.replace("{\"Product\":", "");
			xmlson=xmlson.substring(0, xmlson.length() - 1);
			//System.out.println("removed extra tags from xml:"+xmlson);

			//storing json reponse body in Json array
			try {
				JSONArray json=new JSONArray(respbody1);
				//System.out.println("json data:" +json);
				//LOGGER.info(",Passed : "+dataUtilities.compareJson(new JSONArray(xmlson), json));
				LoggerImpl.logResult(dataUtilities.compareJson(new JSONArray(xmlson), json));
				LoggerImpl.RecordLog();
				Assert.assertTrue(dataUtilities.compareJson(new JSONArray(xmlson), json));
			}catch(Exception e) {
				JSONObject json=new JSONObject(respbody1);
				//System.out.println("json data:" +json);
				//LOGGER.info(",Passed : "+dataUtilities.compareJson(new JSONObject(xmlson), json));
				LoggerImpl.logResult(dataUtilities.compareJson(new JSONObject(xmlson), json));
				LoggerImpl.RecordLog();
				Assert.assertTrue(dataUtilities.compareJson(new JSONObject(xmlson), json));
			}
			
		}


	

	


}


