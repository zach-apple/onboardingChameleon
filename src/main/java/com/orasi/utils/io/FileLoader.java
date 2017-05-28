package com.orasi.utils.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.orasi.utils.TestReporter;

public class FileLoader {

	public static String loadFileFromProjectAsString(String filePath) throws IOException{
		TestReporter.logTrace("Entering FileLoader#loadFileFromProjectAsString");

		TestReporter.logTrace("Attempting to load file from path [ " + filePath + " ]");
		InputStream resource = FileLoader.class.getResourceAsStream(filePath);
		if(resource == null){
			resource = FileLoader.class.getResourceAsStream("/" + filePath);			
			if(resource == null){ 
				TestReporter.logTrace("Failed to locate file");
				throw new FileNotFoundException("Failed to find resource [ " + filePath + " ]");
			}
		}
		TestReporter.logTrace("File successfully loaded");

		BufferedReader br = null;
		StringBuilder sb = null;		
		try{
			TestReporter.logTrace("Loading file into Stream Reader");
			br = new BufferedReader(new InputStreamReader(resource));
			TestReporter.logTrace("Successfully loaded file into Stream Reader");
			String line;
			sb = new StringBuilder();

			TestReporter.logTrace("Attempting to load stream into a String Builder");
			try {
				while((line=br.readLine())!= null){
					sb.append(line.trim());
				}
			} catch (IOException e) {
				TestReporter.logTrace("Failed to read line. Stream only built following: " + sb.toString());
				throw new IOException("Failed to read from file [ " + filePath + " ]", e);
			}
		}finally{
			try {
				TestReporter.logTrace("Successfully read file and converted to String builder");
				TestReporter.logTrace("Closing Stream Reader");
				br.close();
			} catch (IOException e1) {
				throw new IOException("Failed to close buffer reader", e1);
			}
		}
		TestReporter.logTrace("Exiting FileLoader#loadFileFromProjectAsString");
		return sb.toString();
	}

}
