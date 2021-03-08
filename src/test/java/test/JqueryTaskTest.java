package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import page.JqueryUiMainPage;

public class JqueryTaskTest {
    WebDriver firefoxDriver;

    ExtentReports extent;
    ExtentSparkReporter spark;
    ExtentTest test;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        WebDriverManager.firefoxdriver().setup();
        firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().window().maximize();
    }

    @BeforeTest(alwaysRun = true)
    public void startReport() {
        spark = new ExtentSparkReporter("Report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("User Name", "Rysard P");
        extent.setSystemInfo("OS", "Windows 10");
        extent.setSystemInfo("programming language", "java 1.8.0_201");
        extent.setSystemInfo("Browser", "Firefox");

        spark.config().setDocumentTitle("Jquery test report");
        spark.config().setReportName("Jquery test report");
        spark.config().setTheme(Theme.STANDARD);
    }

    @Test(description = "Verify left menu bar containing Interactions section")
    public void scenarioInteractionsTest() {
        test = extent.createTest("Test 1. Interactions section Test");
        new JqueryUiMainPage(this.firefoxDriver)
                .openPage("https://jqueryui.com/demos/")
                .checkInteractionsInLeftMenu("Interactions");
    }

    @Test(description = "Verify left menu bar containing Widgets section")
    public void scenarioWidgetsTest() {
        test = extent.createTest("Test 1. Widgets section Test");
        new JqueryUiMainPage(this.firefoxDriver)
                .openPage("https://jqueryui.com/demos/")
                .checkWidgetsInLeftMenu("Widgets");
    }

    @Test(description = "Verify left menu bar containing Effects section")
    public void scenarioEffectsTest() {
        test = extent.createTest("Test 1. Effects section Test");
        new JqueryUiMainPage(this.firefoxDriver)
                .openPage("https://jqueryui.com/demos/")
                .checkEffectsInLeftMenu("Effects");
    }

    @Test(description = "Verify left menu bar containing Utilities section")
    public void scenarioUtilitiesTest() {
        test = extent.createTest("Test 1. Utilities section Test");
        new JqueryUiMainPage(this.firefoxDriver)
                .openPage("https://jqueryui.com/demos/")
                .checkUtilitiesInLeftMenu("Utilities");
    }

    @Test(description = "Verify value in spinner page")
    public void scenarioWithSpinnerPageTest() {
        test = extent.createTest("Test 2. Verify the values in field on the page Spinner");
        new JqueryUiMainPage(this.firefoxDriver)
                .openPage("https://jqueryui.com/demos/")
                .clickOnSpinnerLink()
                .typeValue(String.valueOf(12))
                .clickGetValueButton()
                .checkValueOnPopUp(String.valueOf(12));
    }

    @Test(description = "Verify value in Autocomplete page")
    public void scenarioWithAutocompletePageTest() throws InterruptedException {
        test = extent.createTest("Test 3. Verify tag field in in autocomplete page");
        new JqueryUiMainPage(this.firefoxDriver)
                .openPage("https://jqueryui.com/demos/")
                .clickOnAutocompletePage()
                .clickOnTagsField("a")
                .selectThirdBottomOption()
                .checkValueInField("Asp");
    }

    @AfterMethod(alwaysRun = true)
    public void getResult(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
            test.fail(result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " PASSED ", ExtentColor.GREEN));
        } else {
            test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
            test.skip(result.getThrowable());
        }

    }
    @AfterMethod(alwaysRun = true)
    public void closeBrowser() {
        firefoxDriver.quit();
        firefoxDriver = null;
    }

    @AfterTest
    public void tearDown() {
        extent.flush();
    }
}
