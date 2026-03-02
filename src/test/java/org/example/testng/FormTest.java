package org.example.testng;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pom.FormPom;
import org.example.utils.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.time.Duration;

public class FormTest {

    private static final Logger logger = LogManager.getLogger(FormTest.class);

    public static WebDriver driver;
    public static String Url = "https://demoqa.com/";
    public static String FirstName = "Cucta";
    public static String LastName = "Valeria";
    public static String Email = "valeria.cucta@gmail.com";
    public static String Gender = "Female";
    public static String NUMBER = "0123456789";
    public static String DATE = "12 Sep 2006";
    public static String SUBJECT = "Maths";
    public static String HOBBY = "Sports";
    public static String STATE = "Rajasthan";
    public static String CITY = "Jaipur";

    @BeforeMethod
    public void beforeMethod() {
        logger.info("Start Before Method");

        driver = Driver.getAutoLocalDriver();  // ← changed from getRemoteDriver()
        driver.manage().window().maximize();
    }

    @Test
    public void studentFormTest() throws InterruptedException {
        logger.info("Start Test");

        driver.get(Url);

        FormPom formPom = new FormPom(driver);

        formPom.pause(2000);
        formPom.clickForms();
        formPom.pause(2000);
        formPom.clickPracticeForm();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#firstName")));

        formPom.setFirstName(FirstName);
        logger.info("First Name set");

        formPom.setLastName(LastName);
        formPom.setUserEmail(Email);
        formPom.setGender(Gender);
        formPom.setUserNumber(NUMBER);
        formPom.setDate(DATE);
        formPom.setSubject(SUBJECT);
        formPom.setHobby(HOBBY);
        formPom.pause(2000);
        formPom.setState(STATE);
        formPom.setCity(CITY);
        formPom.clickSubmit();

        String actualName = formPom.getFinalData("Student Name");

        Assert.assertEquals(actualName, FirstName + " " + LastName);
        formPom.pause(5000);

        logger.info("Finish Test");
    }

    @AfterMethod
    public void afterMethod() {
        logger.info("Start After Method");
        driver.quit();
    }
}