package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class Main {
    public static WebDriver browser;
    public static final String URL = "http://localhost:8080/prisijungti";
    public static final String NAME = "Justina";
    public static final String PASSWORD = "Lape.1";

    public static void setUp() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");

        browser = new ChromeDriver(chromeOptions);
        browser.get(URL);
    }
    public static void closeBrowser() {
        browser.quit();
    }
    public static void singUpButton() {
        browser.findElement(By.linkText("Sukurti naują paskyrą")).click();
    }
    public static void signUp(String username) {
        browser.findElement(By.id("username")).sendKeys(username);;
        browser.findElement(By.id("password")).sendKeys(PASSWORD);;
        browser.findElement(By.id("passwordConfirm")).sendKeys(PASSWORD);

        browser.findElement(By.className("btn")).click();
    }
    public static void logIn(String username, String password) {
        browser.findElement(By.name("username")).sendKeys(username);
        browser.findElement(By.name("password")).sendKeys(password);
        browser.findElement(By.className("btn")).click();
    }
    public static void creatNewRecord(String skaicius1, String skaicius2, String value) {
        WebElement sk1 = browser.findElement(By.id("sk1"));
        sk1.clear();
        sk1.sendKeys(skaicius1);

        WebElement sk2 = browser.findElement(By.id("sk2"));
        sk2.clear();
        sk2.sendKeys(skaicius2);

        WebElement zenklas = browser.findElement(By.name("zenklas"));
        Select select = new Select(zenklas);
        select.selectByValue(value);

        browser.findElement(By.cssSelector("#number > input[type=submit]:nth-child(10)")).click();
    }
    public static boolean findRecord() {
        browser.findElement(By.linkText("Atliktos operacijos")).click();
        try {
            WebElement irasas = browser.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[4]"));
            return irasas.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public static void correctRecord(String skaicius1, String value, String skaicius2, String rezultatas) {
        browser.findElement(By.linkText("Keisti")).click();

        WebElement sk1 = browser.findElement(By.name("sk1"));
        sk1.clear();
        sk1.sendKeys(skaicius1);

        WebElement zenklas = browser.findElement(By.name("zenklas"));
        zenklas.clear();
        zenklas.sendKeys(value);

        WebElement sk2 = browser.findElement(By.name("sk2"));
        sk2.clear();
        sk2.sendKeys(skaicius2);

        WebElement rezult = browser.findElement(By.name("rezult"));
        rezult.clear();
        rezult.sendKeys(rezultatas);

        browser.findElement(By.xpath("//*[@id=\"command\"]/p[5]/input")).click();
    }
    public static boolean deleteRecord() {
        browser.findElement(By.linkText("Atliktos operacijos")).click();
        try {
            WebElement find = browser.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[5]/a[2]"));
            if (find.isDisplayed()) {
                find.click();
                return true;
            }
            else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    public static void main(String[] args) {
        setUp();
        singUpButton();
        signUp("Vilm");
        logIn(NAME, PASSWORD);
        creatNewRecord("4", "4", "+");
        findRecord();
        correctRecord("4", "+", "7", "11");
        deleteRecord();
        closeBrowser();

        System.out.println("Egzaminas 2024-03-21");
    }
}