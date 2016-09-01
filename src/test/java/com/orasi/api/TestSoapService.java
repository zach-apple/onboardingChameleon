package com.orasi.api;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.api.uszip.operations.GetInfoByAreaCode;
import com.orasi.utils.AlertHandler;
import com.orasi.utils.TestReporter;

import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

public class TestSoapService {
    @Features("API")
    @Stories("SoapService")
    @Test(groups ={"regression", "interfaces", "textbox"})
    public void sandbox(){
	GetInfoByAreaCode getInfo = new GetInfoByAreaCode();
	getInfo.setAreaCode("336");
	System.out.println(getInfo.getRequest());
	getInfo.sendRequest();
	System.out.println(getInfo.getResponse());
	TestReporter.logAPI(getInfo.getNumberOfResults() == 0, "No records returned", getInfo);
;    }
}
