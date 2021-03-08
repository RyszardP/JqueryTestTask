package page;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class JqueryUiSpinnerPage extends AbstractPage {

    private static final String FRAME_XPATH = "/html/body/div[2]/div[2]/div/div[1]/iframe";
    public String enteredValue;

    @FindBy(xpath = "//*[@id='getvalue']")
    private WebElement getValueButton;

    @FindBy(xpath = "//*[@id='spinner']")
    private WebElement valueField;

    public JqueryUiSpinnerPage(WebDriver driver) {
        super(driver);
    }

    public JqueryUiSpinnerPage openPage(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        return this;
    }

    public JqueryUiSpinnerPage typeValue(String enteredValue) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(FRAME_XPATH)));
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(valueField))
                .sendKeys(enteredValue);
        return this;
    }

    public JqueryUiSpinnerPage clickGetValueButton() {
        new WebDriverWait(driver, 5);
        getValueButton.click();
        return this;
    }

    public void checkValueOnPopUp(String enteredValue) {
        assertEquals(driver.switchTo().alert().getText(), enteredValue);
    }

}
