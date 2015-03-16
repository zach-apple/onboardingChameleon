package com.jenkins;

import java.io.IOException;

import com.jenkins.methods.*;
import com.orasi.api.restServices.core.RestService;

public class JenkinsClient {
    private String host = "localhost";
    private String port  = "80";
    private String jobPath = "job/";
    
    public String getBaseUrl() {return "http://" + getHost() + ":" + getPort() + "/";}
    //private void setBaseUrl(String url) {this.baseUrl = url;}
    
    public String getHost() {return host;}
    private void setHost(String host) {this.host = host;}
    
    public String getPort() {return port;}
    private void setPort(String port) { this.port = port;}
    
    public String getJobPath() {return jobPath ;}
    private void setJobPath(String jobsPath) {this.jobPath = jobsPath;}

    public String getJobsURL(){return this.getBaseUrl() + this.getJobPath();}
    
    private RestService restService = new RestService();
	
    public JenkinsClient(String host, String port){ 
	setHost(host);
	setPort(port);
    }
    
    public JenkinsClient(String host, String port, String jobPath){ 
	setHost(host);
	setPort(port);
	setJobPath(jobPath);
    }
    
    public LastBuild lastBuild(String job){
	LastBuild lastBuild = null;
	
	try {
	    restService.sendGetRequest(getJobsURL() +  job + "/lastBuild");
	    lastBuild = restService.mapJSONToObject(LastBuild.class);
		
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return lastBuild;	
    }
    

    public LastCompletedBuild lastCompletedBuild(String job){
	LastCompletedBuild lastCompletedBuild = null;
	
	try {
	    restService.sendGetRequest(getJobsURL() +  job + "/lastCompletedBuild");
	    lastCompletedBuild = restService.mapJSONToObject(LastCompletedBuild.class);
		
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return lastCompletedBuild;	
    }
    

    public LastFailedBuild lastFailedBuild(String job){
	LastFailedBuild lastFailedBuild = null;
	
	try {
	    restService.sendGetRequest(getJobsURL() +  job + "/lastFailedBuild");
	    lastFailedBuild = restService.mapJSONToObject(LastFailedBuild.class);
		
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return lastFailedBuild;	
    }

    public LastStableBuild lastStableBuild(String job){
	LastStableBuild lastStableBuild = null;
	
	try {
	    restService.sendGetRequest(getJobsURL() +  job + "/lastStableBuild");
	    lastStableBuild = restService.mapJSONToObject(LastStableBuild.class);
		
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return lastStableBuild;	
    }
    
    public LastSuccessfulBuild lastSuccessfulBuild(String job){
	LastSuccessfulBuild lastSuccessfulBuild = null;
	
	try {
	    restService.sendGetRequest(getJobsURL() +  job + "/lastSuccessfulBuild");
	    lastSuccessfulBuild = restService.mapJSONToObject(LastSuccessfulBuild.class);
		
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return lastSuccessfulBuild;	
    }
    
    public LastUnstableBuild lastUnstableBuild(String job){
	LastUnstableBuild lastUnstableBuild = null;
	
	try {
	    restService.sendGetRequest(getJobsURL() +  job + "/lastUnstableBuild");
	    lastUnstableBuild = restService.mapJSONToObject(LastUnstableBuild.class);
		
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return lastUnstableBuild;	
    }

    public LastUnsuccessfulBuild lastUnsuccessfulBuild(String job){
	LastUnsuccessfulBuild lastUnsuccessfulBuild = null;
	
	try {
	    restService.sendGetRequest(getJobsURL() +  job + "/lastUnstableBuild");
	    lastUnsuccessfulBuild = restService.mapJSONToObject(LastUnsuccessfulBuild.class);
		
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return lastUnsuccessfulBuild;	
    }
}
