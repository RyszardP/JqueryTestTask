package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.testng.Assert.assertEquals;

public class JqueryUiMainPage extends AbstractPage {

    private static final String LEFT_MENU_BAR = "//div[@id='sidebar']/";
    private static final String INTERACTIONS = "Interactions";
    private static final String WIDGETS = "Widgets";
    private static final String EFFECTS = "Effects";
    private static final String UTILITIES = "Utilities";

    @FindBy(xpath = LEFT_MENU_BAR + "/descendant-or-self::*[text()='" + INTERACTIONS + "']")
    private WebElement interactionsSection;

    @FindBy(xpath = LEFT_MENU_BAR + "/descendant-or-self::*[text()='" + WIDGETS + "']")
    private WebElement widgetsSection;

    @FindBy(xpath = LEFT_MENU_BAR + "/descendant-or-self::*[text()='" + EFFECTS + "']")
    private WebElement effectsSection;

    @FindBy(xpath = LEFT_MENU_BAR + "/descendant-or-self::*[text()='" + UTILITIES + "']")
    private WebElement utilitiesSection;

    @FindBy(xpath = "//a[text()='Spinner']")
    private WebElement linkToSpinnerPage;

    @FindBy(xpath = "//div[@id='sidebar']/descendant-or-self::a[text()='Autocomplete']")
    private WebElement linkToAutocomplete;

    public JqueryUiMainPage(WebDriver driver) {
        super(driver);
    }

    public JqueryUiMainPage openPage(String url) {
        driver.manage().window().maximize();
        driver.get(url);
        return this;
    }

    public String checkSectionInLeftMenu(WebElement webElement) {
        return webElement.getText();
    }

    public void checkInteractionsInLeftMenu(String string) {
        assertEquals(checkSectionInLeftMenu(interactionsSection), string);
    }

    public void checkWidgetsInLeftMenu(String string) {
        assertEquals(checkSectionInLeftMenu(widgetsSection), string);
    }

    public void checkEffectsInLeftMenu(String string) {
        assertEquals(checkSectionInLeftMenu(effectsSection), string);
    }

    public void checkUtilitiesInLeftMenu(String string) {
        assertEquals(checkSectionInLeftMenu(utilitiesSection), string);
    }

    public JqueryUiSpinnerPage clickOnSpinnerLink() {
        linkToSpinnerPage.click();
        return new JqueryUiSpinnerPage(driver);
    }

    public JqueryUiAutocompletePage clickOnAutocompletePage() {
        linkToAutocomplete.click();
        return new JqueryUiAutocompletePage(driver);
    }

}
