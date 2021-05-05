import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import resources.Base;
import resources.ExtentReporterNG;

import java.io.IOException;



public class Listeners extends Base implements ITestListener {

    ExtentTest test;
    ExtentReports extent = ExtentReporterNG.getReportObject();
    ThreadLocal<ExtentTest> t = new ThreadLocal<ExtentTest>();
    public void onTestStart(ITestResult iTestResult) {
        test = extent.createTest(iTestResult.getName());
        t.set(test);
    }

    public void onTestSuccess(ITestResult iTestResult) {
        t.get().log(Status.PASS,"Test passed");
    }

    public void onTestFailure(ITestResult iTestResult) {
        t.get().fail(iTestResult.getThrowable());
        String methodName = iTestResult.getName();
        WebDriver driver = null;
        try {
            driver =(WebDriver)iTestResult.getTestClass().getRealClass().getDeclaredField("driver").get(iTestResult.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            takeScreenshot(methodName,driver);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {

    }

    public void onFinish(ITestContext iTestContext) {
        extent.flush();
    }
}
