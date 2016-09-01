package com.api.uszip;

import com.orasi.api.soapServices.core.SoapService;

public class UsZipService extends SoapService{

	public UsZipService() {
	    setServiceName("UsZipService");
	    setServiceURL("http://www.webservicex.net/uszip.asmx?wsdl");
	}
}
