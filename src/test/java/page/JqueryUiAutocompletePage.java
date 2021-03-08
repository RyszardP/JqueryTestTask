package page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.testng.Assert.assertEquals;


public class JqueryUiAutocompletePage extends AbstractPage {

    private static final String FRAME_XPATH = "/html/body/div[2]/div[2]/div/div[1]/iframe";

    @FindBy(xpath = "//*[@id='tags']")
    private WebElement tagsField;

    @FindBy(xpath = "//*[@id='ui-id-1']")
    private WebElement listOfValuesInField;

    @FindBy(xpath = "//*[@class='ui-menu-item'][1]")
    private WebElement selectValuesInField;

    public JqueryUiAutocompletePage(WebDriver driver) {
        super(driver);
    }

    public JqueryUiAutocompletePage openPage(String url) {
        driver.get(url);
        driver.manage().window().maximize();
        return this;
    }

    public JqueryUiAutocompletePage clickOnTagsField(String string) {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(FRAME_XPATH)));
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(tagsField))
                .sendKeys(string);

        return this;
    }

    public JqueryUiAutocompletePage selectThirdBottomOption() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.elementToBeClickable(listOfValuesInField));
        if (listOfValuesInField.isDisplayed()) {
            tagsField.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ENTER);
        }
        return this;
    }

    public void checkValueInField(String valueInField) {
        assertEquals(tagsField.getAttribute("value"), valueInField);
    }

}
