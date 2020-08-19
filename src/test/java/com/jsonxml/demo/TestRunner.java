package com.jsonxml.demo;


import java.io.*;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestRunner {
	private static final Logger LOGGER = LogManager.getLogger(TestRunner.class.getName());
	
	
	public static Collection<Object[]> getList() throws IOException{
		/*return Arrays.asList(new Object[][] {
	          { "products", "productsxml" },
	          { "products/1", "productsxml/1" },
	          { "products/2", "productsxml/2" },
	          
	       });*/
		Resource resource = new ClassPathResource("requests_response");
        InputStream input = resource.getInputStream();
        File file = resource.getFile();
        String content = new String(Files.readAllBytes(file.toPath()));
        String ar[]=content.split("\n");
        String arr1[][]=new String[ar.length][2];
        for(int i=0;i<ar.length;i++)
        {
        String ar1[]=ar[i].split(",");
        arr1[i][0]=ar1[0].replace("\"","");
        arr1[i][1]=ar1[1].replace("\"", "");
        }
	    List list1 = Arrays.asList(arr1);
	    return list1;
	}
	
	public static Response getResponse(String api) {
		RestAssured.baseURI="http://localhost";
		RestAssured.port=8080;
		RequestSpecification httprequest = RestAssured.given();
		ByteArrayOutputStream requestStream = new ByteArrayOutputStream();
        ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
        PrintStream requestPrint = new PrintStream(requestStream);
        PrintStream responsePrint = new PrintStream(responseStream);
        CommonsRequestLoggingFilter filter=new CommonsRequestLoggingFilter();
        RequestLoggingFilter requestLoggingFilter = new RequestLoggingFilter(LogDetail.ALL,false,requestPrint);
        ResponseLoggingFilter responseLoggingFilter = new ResponseLoggingFilter(LogDetail.ALL,false,responsePrint);
        
        RestAssured.filters(requestLoggingFilter,responseLoggingFilter);
        Response response = RestAssured.given().get(api);
        
        String req = requestStream.toString();
        String res = responseStream.toString();
		//LOGGER.info(","+req+","+res);
		LoggerImpl.logRequest(req);
		LoggerImpl.logResponse(res);
		
		return response;
	    
	}
	
}
