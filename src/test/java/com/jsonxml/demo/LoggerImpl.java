package com.jsonxml.demo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

public class LoggerImpl {
	
	private static String file="logs.xls";
	private static Workbook book ; 
	private static HSSFSheet sheet ;
	private static Row row;
	private static int count=0,req=0,res=0;
	private static String request_1="", response_1="",request_2="",response_2="";
	private static boolean testResult;
	public static void createBook() throws FileNotFoundException, IOException {
		book= new HSSFWorkbook(new FileInputStream(file));
		sheet= (HSSFSheet) book.getSheet("Test Logs");
		count=sheet.getLastRowNum()+1;
		//System.out.println(count);
	}
	public static void logRequest(String request) {
		if(req==0) {
			request_1+=request;
			req++;
		}else {
			request_2+=request;
			req--;
		}
		
	}
	public static void logResponse(String response) {
		if(res==0) {
			response_1+=response;
			res++;
		}else {
			response_2+=response;
			res--;
		}
		
	}
	public static void logResult(boolean test) {
		testResult=test;
	}
	public static void RecordLog() throws FileNotFoundException, IOException {
		createBook();
		
		row=sheet.createRow(count);
		row.createCell(0).setCellValue(request_1);
		row.createCell(1).setCellValue(request_2);
		row.createCell(2).setCellValue(response_1);
		row.createCell(3).setCellValue(response_2);
		row.createCell(4).setCellValue(testResult);
		count++;
		response_1="";
		response_2="";
		request_1="";
		request_2="";
		book.write(new FileOutputStream(file));
		book.close();
	}
	
	

}
