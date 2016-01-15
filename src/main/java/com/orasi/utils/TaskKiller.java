package com.orasi.utils;

import java.io.IOException;

import org.openqa.selenium.Platform;

import com.orasi.exception.automation.NoSuchTaskException;

public class TaskKiller {
    final static String WINDOWS_KILL_BY_PROCESS = "taskkill /f /IM ";
    final static String WINDOWS_KILL_BY_PID = "taskkill /pid ";
    final static String NONWINDOWS_KILL_BY_PROCESS = "killall ";
    final static String NONWINDOWS_KILL_BY_PID = "kill ";
	
	public static void windowsTaskByName(String process) 
	{	  
	  try {
	    Runtime.getRuntime().exec(WINDOWS_KILL_BY_PROCESS + process);
	  	} catch (IOException e) {
	  	    throw new NoSuchTaskException( "No task found with process name [" + process + "]");
	  	} 
    	  Sleeper.sleep(2000); //Allow OS to kill the process
	} 
	
	public static void windowsTaskByPid(String pid) 
	{	  
	    try {
		    Runtime.getRuntime().exec(WINDOWS_KILL_BY_PID + pid);
		} catch (IOException e) {
		    throw new NoSuchTaskException( "No task found with process name [" + pid + "]");
		}
	  Sleeper.sleep(2000); //Allow OS to kill the process
	} 
	
	public static void nonWindowsTaskByName(String process) 
	{	  
	    try {
		    Runtime.getRuntime().exec(NONWINDOWS_KILL_BY_PROCESS + process);
		} catch (IOException e) {
		    throw new NoSuchTaskException( "No task found with process name [" + process + "]");
		}
	  Sleeper.sleep(2000); //Allow OS to kill the process
	} 
	
	public static void nonWindowsTaskByPid(String pid) 
	{	  
	    try {
		    Runtime.getRuntime().exec(NONWINDOWS_KILL_BY_PID + pid);
		} catch (IOException e) {
		    throw new NoSuchTaskException( "No task found with process name [" + pid + "]");
		}
	  Sleeper.sleep(2000); //Allow OS to kill the process
	} 
	
	public static void killTaskByNameUsingPlatform(String process, Platform platform ){	 
	    if(platform.name().toLowerCase().contains("windows")) windowsTaskByName(process);
	    else nonWindowsTaskByName(process);		
	}
	
	public static void killTaskByNameUsingPid(String pid, Platform platform ){	 
	    if(platform.name().toLowerCase().contains("windows")) windowsTaskByPid(pid);
	    else nonWindowsTaskByPid(pid);		
	}
}
