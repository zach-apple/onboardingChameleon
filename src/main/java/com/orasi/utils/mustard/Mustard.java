package com.orasi.utils.mustard;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;
import static org.testng.ITestResult.SKIP;
import static org.testng.ITestResult.SUCCESS;

import java.util.ResourceBundle;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.testng.ITestResult;

import com.orasi.api.restServices.Headers.HeaderType;
import com.orasi.api.restServices.RestService;
import com.orasi.core.Beta;
import com.orasi.utils.Constants;
import com.orasi.utils.OrasiDriver;

@Beta
public class Mustard {

    private static ResourceBundle appURLRepository = ResourceBundle.getBundle(Constants.ENVIRONMENT_URL_PATH);

    private static String mustardURL = appURLRepository.getString("MUSTARD_URL") + "results";
    private static String mustardKey = appURLRepository.getString("MUSTARD_PROJECT_KEY");

    @Beta
    public static void postResultsToMustard(OrasiDriver driver, ITestResult result, String runLocation, String base64Screenshot) {

        RestService request = new RestService();

        String device_id = driver.getDriverCapability().platform().name() + "_" + driver.getDriverCapability().browserName() + "_" + driver.getDriverCapability().browserVersion().replace(".", "_");
        String test_name = result.getTestClass().getName();
        test_name = test_name.substring(test_name.lastIndexOf('.') + 1, test_name.length()) + "_" + result.getMethod().getMethodName();
        String status = "";

        if (result.getStatus() == SUCCESS) {
            status = "pass";
        } else if (result.getStatus() == SKIP) {
            status = "skip";
        } else {
            status = "fail";
        }

        String sauceURL = "";
        MustardResult mustardResult = new MustardResult();

        mustardResult.getResult().setProjectId(mustardKey);
        mustardResult.getResult().setResultType("automated");
        mustardResult.getResult().setEnvironmentId(device_id);
        mustardResult.getResult().setTestcaseId(test_name);
        mustardResult.getResult().setStatus(status);

        if ("sauce".equalsIgnoreCase(runLocation)) {
            sauceURL = appURLRepository.getString("SAUCELABS_URL") + driver.getSessionId();
            mustardResult.getResult().setLink(sauceURL);
        }

        if ("fail".equalsIgnoreCase(status)) {
            mustardResult.getResult().setComment(result.getThrowable().getMessage());
            mustardResult.getResult().setStacktrace(ExceptionUtils.getFullStackTrace(result.getThrowable()));
        }

        if (isNotEmpty(base64Screenshot)) {
            mustardResult.getResult().setScreenshot("data:image/png;base64," + base64Screenshot);
        }

        request.sendPostRequest(mustardURL, HeaderType.JSON, RestService.getJsonFromObject(mustardResult));

    }
}
