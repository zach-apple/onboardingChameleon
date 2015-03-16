package com.jenkins;

import org.junit.Test;
import com.jenkins.methods.*;

public class Sandbox {
    private String host = "jenkins.orasi.com";
    private String port = "80";
    private String jobPath = "job/OpenSandbox/job/";
    private String jobName = "Toyota-SauceLabs";
    
    private JenkinsClient jenkins = new JenkinsClient(host, port, jobPath);
    
    @Test
    public void getLastBuild(){
	LastBuild lastBuild  = jenkins.lastBuild(jobName);
	
	System.out.println("Build Number: " + lastBuild.getBuildNumber());	
	System.out.println("Job Name: " + lastBuild.getName());
	System.out.println("Job Display Name: " + lastBuild.getDisplayName());
	System.out.println("Job Desciption: " + lastBuild.getDescription());
	System.out.println("Job Result: " + lastBuild.getResult());
	System.out.println("Job URL: " + lastBuild.getUrl());
    }
    
    @Test
    public void getLastCompletedBuild(){
	LastCompletedBuild lastCompletedBuild  = jenkins.lastCompletedBuild(jobName);
	
	System.out.println("Build Number: " + lastCompletedBuild.getBuildNumber());	
	System.out.println("Job Name: " + lastCompletedBuild.getName());
	System.out.println("Job Display Name: " + lastCompletedBuild.getDisplayName());
	System.out.println("Job Desciption: " + lastCompletedBuild.getDescription());
	System.out.println("Job Result: " + lastCompletedBuild.getResult());
	System.out.println("Job URL: " + lastCompletedBuild.getUrl());
    }

    @Test
    public void getLastFailedBuild(){
	LastFailedBuild lastFailedBuild  = jenkins.lastFailedBuild(jobName);
	
	System.out.println("Build Number: " + lastFailedBuild.getBuildNumber());	
	System.out.println("Job Name: " + lastFailedBuild.getName());
	System.out.println("Job Display Name: " + lastFailedBuild.getDisplayName());
	System.out.println("Job Desciption: " + lastFailedBuild.getDescription());
	System.out.println("Job Result: " + lastFailedBuild.getResult());
	System.out.println("Job URL: " + lastFailedBuild.getUrl());	
    }

    
    @Test
    public void getLastStableBuild(){
	LastStableBuild lastStableBuild  = jenkins.lastStableBuild(jobName);
	
	System.out.println("Build Number: " + lastStableBuild.getBuildNumber());	
	System.out.println("Job Name: " + lastStableBuild.getName());
	System.out.println("Job Display Name: " + lastStableBuild.getDisplayName());
	System.out.println("Job Desciption: " + lastStableBuild.getDescription());
	System.out.println("Job Result: " + lastStableBuild.getResult());
	System.out.println("Job URL: " + lastStableBuild.getUrl());
    }

    @Test
    public void getLastSuccessfulBuild(){
	LastSuccessfulBuild lastSuccessfulBuild  = jenkins.lastSuccessfulBuild(jobName);
	
	System.out.println("Build Number: " + lastSuccessfulBuild.getBuildNumber());	
	System.out.println("Job Name: " + lastSuccessfulBuild.getName());
	System.out.println("Job Display Name: " + lastSuccessfulBuild.getDisplayName());
	System.out.println("Job Desciption: " + lastSuccessfulBuild.getDescription());
	System.out.println("Job Result: " + lastSuccessfulBuild.getResult());
	System.out.println("Job URL: " + lastSuccessfulBuild.getUrl());
    }

    @Test
    public void getLastUnstableBuild(){
	LastUnstableBuild lastUnstableBuild  = jenkins.lastUnstableBuild(jobName);
	
	System.out.println("Build Number: " + lastUnstableBuild.getBuildNumber());	
	System.out.println("Job Name: " + lastUnstableBuild.getName());
	System.out.println("Job Display Name: " + lastUnstableBuild.getDisplayName());
	System.out.println("Job Desciption: " + lastUnstableBuild.getDescription());
	System.out.println("Job Result: " + lastUnstableBuild.getResult());
	System.out.println("Job URL: " + lastUnstableBuild.getUrl());
    }

    @Test
    public void getLastUnsuccessfulBuild(){
	LastUnsuccessfulBuild lastUnsuccessfulBuild  = jenkins.lastUnsuccessfulBuild(jobName);
	
	System.out.println("Build Number: " + lastUnsuccessfulBuild.getBuildNumber());	
	System.out.println("Job Name: " + lastUnsuccessfulBuild.getName());
	System.out.println("Job Display Name: " + lastUnsuccessfulBuild.getDisplayName());
	System.out.println("Job Desciption: " + lastUnsuccessfulBuild.getDescription());
	System.out.println("Job Result: " + lastUnsuccessfulBuild.getResult());
	System.out.println("Job URL: " + lastUnsuccessfulBuild.getUrl());
    }
}
