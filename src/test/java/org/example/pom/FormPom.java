package org.example.pom;

import org.example.utils.Utils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



public class FormPom {

    private WebDriver driver;
    public JavascriptExecutor js;

    @FindBy(xpath = "//*[text()='Forms']")
    WebElement form;

    @FindBy(xpath = "//*[text()='Practice Form']")
    WebElement practiceForm;

    @FindBy(id = "firstName")
    WebElement firstName;

    @FindBy(id = "lastName")
    WebElement lastName;

    @FindBy(id = "userEmail")
    WebElement userEmail;

    @FindBy(id = "userNumber")
    WebElement userNumber;

    @FindBy(id = "subjectsInput")
    WebElement subjectsInput;

    @FindBy(id = "dateOfBirthInput")
    WebElement dateOfBirthInput;

    @FindBy(id = "state")
    WebElement state;

    @FindBy(id = "city")
    WebElement city;

    @FindBy(id = "submit")
    WebElement submit;

    public FormPom(WebDriver driverParam){
        driver = driverParam;
        js = (JavascriptExecutor) driver;
        PageFactory.initElements(driverParam, this);
    }

    public void clickSubmit() {
        submit.click();
    }

    public String getFinalData(String labelParam){
        Utils.explicitWait(driver, ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//table//*[text()='" + labelParam + "']/../*[2]")), 10);
        WebElement name = driver.findElement(By.xpath("//table//*[text()='" + labelParam + "']/../*[2]"));
        return name.getText();
    }

    public void setState(String stateParam){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        scrollToElement(state);


        wait.until(ExpectedConditions.elementToBeClickable(state));
        state.click();


        WebElement dropState = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(@class,'menu')]//*[text()='" + stateParam + "']")
                )
        );


        scrollToElement(dropState);


        dropState.click();
    }

    public void setCity(String cityParam){
        city.click();
        WebElement dropCity = driver.findElement(
                By.xpath("//*[text()='" + cityParam + "']")
        );
        dropCity.click();
    }

    public void setHobby(String hobbyParam){
        WebElement hobby = driver.findElement(
                By.xpath("//*[@id='hobbiesWrapper']//label[text()='" + hobbyParam + "']")
        );
        hobby.click();
    }

    public void setUserNumber(String numberParam){
        userNumber.clear();
        userNumber.sendKeys(numberParam);
    }

    public void setSubject(String subjectParam){
        subjectsInput.clear();
        subjectsInput.sendKeys(subjectParam);
        subjectsInput.sendKeys(Keys.ENTER);
    }

    public void setDate(String dateParam){
        dateOfBirthInput.sendKeys(Keys.CONTROL, "a");
        dateOfBirthInput.sendKeys(dateParam);
        dateOfBirthInput.sendKeys(Keys.ENTER);
    }

    public void setGender(String genderParam){
        WebElement gender = driver.findElement(
                By.xpath("//*[@id='genterWrapper']//label[text()='" + genderParam + "']")
        );
        gender.click();
    }

    public void setFirstName(String firstNameParam){
        firstName.clear();
        firstName.sendKeys(firstNameParam);
    }

    public void setLastName(String lastNameParam){
        lastName.clear();
        lastName.sendKeys(lastNameParam);
    }

    public void setUserEmail(String userEmailParam){
        userEmail.clear();
        userEmail.sendKeys(userEmailParam);
    }

    public void clickForms(){
        js.executeScript("document.querySelector('.ad-container')?.remove();");
        js.executeScript("arguments[0].scrollIntoView(true);", form);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(form));

        form.click();
    }

    public void clickPracticeForm(){
        practiceForm.click();
    }

    public void pause(int msParam) throws InterruptedException {
        Thread.sleep(msParam);
    }

    public void scrollToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void closeAdvert() {
        try {
            js.executeScript(
                    "var elem = document.evaluate(\"//*[@id='adplus-anchor']\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                            "if(elem) elem.remove();"
            );
        } catch (Exception ignored) {}

        try {
            js.executeScript(
                    "var elem = document.evaluate(\"//footer\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;" +
                            "if(elem) elem.remove();"
            );
        } catch (Exception ignored) {}
    }
}