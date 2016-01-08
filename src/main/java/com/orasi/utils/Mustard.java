package com.orasi.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ResourceBundle;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;

import com.orasi.api.restServices.core.RestService;
import com.saucelabs.common.SauceOnDemandAuthentication;

public class Mustard {
	private static String mustardURL = "http://mustard.orasi.com/results";
	private static String mustardKey = "c73fbfed815904a032a5cec113bfe85f";
	
	protected static ResourceBundle appURLRepository = ResourceBundle.getBundle(Constants.ENVIRONMENT_URL_PATH);
	protected static SauceOnDemandAuthentication authentication = new SauceOnDemandAuthentication(
			Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_USERNAME")),
			Base64Coder.decodeString(appURLRepository.getString("SAUCELABS_KEY")));
	
	public static void postResultsToMustard(OrasiDriver driver, ITestResult result, String runLocation){
		RestService request = new RestService();
		
		String device_id = driver.getDriverCapability().browserName() + "_" + driver.getDriverCapability().browserVersion();
		String os_version = driver.getDriverCapability().platformOS();
		String test_name = result.getTestClass().getName();
		test_name = test_name.substring(test_name.lastIndexOf(".") + 1, test_name.length())+ "-" +result.getMethod().getMethodName() ;
		String status = "";
		if (result.getStatus() == ITestResult.SUCCESS) status = "pass";
		else status = "fail";
		String sauceURL = "https://saucelabs.com/beta/tests/" + driver.getSessionId().toString();
		JSONObject json = new JSONObject();
		try {
			json.put("project_id", mustardKey);
			json.put("device_id", test_name);
			json.put("os_version", os_version);
			json.put("test_name", device_id);
			json.put("status", status);
			
			if(runLocation.toLowerCase().equals("sauce")) json.put("link", sauceURL);
			if(status.equals("fail")) {
				json.put("comment", result.getThrowable().getMessage());
				json.put("stacktrace", ExceptionUtils.getFullStackTrace(result.getThrowable()));
				sendScreenshotToMustard(test_name, device_id, driver.getScreenshotAs(OutputType.BYTES));
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		 
		 try {
			request.sendPostRequest(mustardURL, new StringEntity(json.toString()));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void sendScreenshotToMustard(String device_id, String test_name, byte[] screenshot){
		JSONObject json = new JSONObject();
		
		try {
			json.put("device_id", test_name);
			json.put("test_name", device_id);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
		multipartEntity.addBinaryBody("someName", screenshot, ContentType.create("image/jpeg"), Randomness.randomAlphaNumeric(32));
		multipartEntity.addPart("someName", new StringBody(json.toString(), ContentType.TEXT_PLAIN));
		
		RestService request = new RestService();
		request.sendPostRequest(mustardURL, multipartEntity.build());
	}
}
