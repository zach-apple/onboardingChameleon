package com.jenkins.methods;

public class LastUnstableBuild {
	private String description;
	private String displayName;
	private String name;
	private String buildable;
	private String url;
	private String nextBuildNumber;
	private String result;
	private String number;
	
	public String getBuildable() {return buildable;}
	public String getBuildNumber() {return number;}
	public String getDescription() {return description;}
	public String getDisplayName() {return displayName;}
	public String getName() {return name;}
	public String getNextBuildNumber() {return nextBuildNumber;}
	public String getResult(){return result;}
	public String getUrl() {return url;}
}
