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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import page.JqueryUiMainPage;

import java.util.concurrent.TimeUnit;

public class JqueryTaskTest {
    WebDriver driver;
    ExtentReports extent;
    ExtentSparkReporter spark;
    ExtentTest test;

    @BeforeTest(alwaysRun = true)
    @Parameters("browser")
    public void startReport(String browser) {
        spark = new ExtentSparkReporter("Report.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("User Name", "Rysard P");
        extent.setSystemInfo("OS", "Windows 10");
        extent.setSystemInfo("programming language", "java 1.8.0_201");
        extent.setSystemInfo("Browser", "browser");

        spark.config().setDocumentTitle("Jquery test report");
        spark.config().setReportName("Jquery test report");
        spark.config().setTheme(Theme.STANDARD);
    }

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void launchBrowser(String browser) throws Exception {
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        } else if (browser.equalsIgnoreCase("internet explorer")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
            driver.manage().window().maximize();
        } else {
            throw new Exception("Incorrect Browser");
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test(description = "Verify left menu bar containing Interactions section")
    public void scenarioInteractionsTest() {
        test = extent.createTest("Test 1. Interactions section Test" , "PASSED test case");
        new JqueryUiMainPage(this.driver)
                .openPage("https://jqueryui.com/demos/")
                .checkInteractionsInLeftMenu("Interactions");
    }

    @Test(description = "Verify left menu bar containing Widgets section")
    public void scenarioWidgetsTest() {
        test = extent.createTest("Test 2. Widgets section Test", "PASSED test case");
        new JqueryUiMainPage(this.driver)
                .openPage("https://jqueryui.com/demos/")
                .checkWidgetsInLeftMenu("Widgets");
    }

    @Test(description = "Verify left menu bar containing Effects section")
    public void scenarioEffectsTest() {
        test = extent.createTest("Test 3. Effects section Test","PASSED test case");
        new JqueryUiMainPage(this.driver)
                .openPage("https://jqueryui.com/demos/")
                .checkEffectsInLeftMenu("Effects");
    }

    @Test(description = "Verify left menu bar containing Utilities section")
    public void scenarioUtilitiesTest() {
        test = extent.createTest("Test 4. Utilities section Test","PASSED test case");
        new JqueryUiMainPage(this.driver)
                .openPage("https://jqueryui.com/demos/")
                .checkUtilitiesInLeftMenu("Utilities");
    }

    @Test(description = "Verify value in spinner page")
    public void scenarioWithSpinnerPageTest() {
        test = extent.createTest("Test 5. Verify the values in field on the page Spinner","PASSED test case");
        new JqueryUiMainPage(this.driver)
                .openPage("https://jqueryui.com/demos/")
                .clickOnSpinnerLink()
                .typeValue(String.valueOf(12))
                .clickGetValueButton()
                .checkValueOnPopUp(String.valueOf(12));
    }

    @Test(description = "Verify value in Autocomplete page")
    public void scenarioWithAutocompletePageTest() {
        test = extent.createTest("Test 6. Verify tag field in in autocomplete page","PASSED test case");
        new JqueryUiMainPage(this.driver)
                .openPage("https://jqueryui.com/demos/")
                .clickOnAutocompletePage()
                .clickOnTagsField("a")
                .selectThirdBottomOption()
                .checkValueInField("Asp");
    }

    @AfterMethod()
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
        driver.quit();
        driver = null;
    }

    @AfterTest
    public void tearDown() {
        extent.flush();
    }
}
