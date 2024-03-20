import org.example.Main;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import static org.example.Main.*;

public class CalcutatorTests {
    @BeforeEach
    public void setUp() {
        Main.setUp();
    }
    @AfterEach
    public void testDown() {
        Main.closeBrowser();
    }

// 	Naujo vartotojo registracija (pozityvus testas)
    @Test
    @Order(1)
    public void signUpPositive() {
        Main.singUpButton();
        String expectedTitle = "Registracija";
        String actual = browser.getTitle();
        Assertions.assertEquals(expectedTitle, actual, "Title is not correct");

        Main.signUp("Vilmo1");
        String expected = "Skaičiuotuvas";
        String actual_title = browser.getTitle();
        Assertions.assertEquals(expected, actual_title, "Title is not correct");
    }

//  Naujo vartotojo registracija (negatyvus testas)
    @Test
    @Order(2)
    public void signUpNegative() {
        Main.singUpButton();
        String expectedTitle = "Registracija";
        String actual = browser.getTitle();
        Assertions.assertEquals(expectedTitle, actual, "Title is not correct");

        Main.signUp("Vilm2");
        String expected = "Skaičiuotuvas";
        String actual_title = browser.getTitle();
        Assertions.assertNotEquals(expected, actual_title, "Title is not correct");

        String actual_message = browser.findElement(By.id("username.errors")).getText();
        String message = "Toks vartotojo vardas jau egzistuoja";
        Assertions.assertEquals(message, actual_message, "Message incorrect!");

        Assertions.assertFalse(browser.getPageSource().contains("Error"), "No error message.");

    }

// 	Esamo vartotojo prisijungimas (pozityvus testas)
    @Test
    @Order(3)
    public void logInPositive() {
        Main.logIn(NAME, PASSWORD);

        Assertions.assertEquals("Skaičiuotuvas", browser.getTitle(), "Login positive test failed.");
    }

// 	Esamo vartotojo prisijungimas (negatyvus testas)
    @Test
    @Order(4)
    public void logInNegative() {
        Main.logIn("Blue", "yellow");

        String expected = "Skaičiuotuvas";
        String actual = browser.getTitle();
        Assertions.assertNotEquals(expected, actual, "Title is not correct");

        String expected_message = "Įvestas prisijungimo vardas ir/ arba slaptažodis yra neteisingi";
        String actual_message = browser.findElement(By.cssSelector("body > div > form > div > span:nth-child(4)")).getText();
        Assertions.assertEquals(expected_message, actual_message, "Username or password incorrect.");

        Assertions.assertFalse(browser.getPageSource().contains("Error"), "Login negative test failed.");
    }

//  Naujo įrašo sukūrimas (pozityvus testas)
    @Test
    @Order(5)
    public void creatNewRecordPositive() {
        Main.logIn(NAME, PASSWORD);
        Main.creatNewRecord("6","3", "-");
        String expected = "6 - 3 = 3";
        String actual = browser.findElement(By.cssSelector("body > h4")).getText();
        Assertions.assertEquals(expected, actual, "Wrong answer");

        String expected_title = "Skaičiuoti";
        String actual_title = browser.getTitle();
        Assertions.assertEquals(expected_title, actual_title, "Wrong title.");
    }

//  Naujo įrašo sukūrimas (negatyvus testas)
    @Test
    @Order(6)
    public void creatNewRecordNegative() {
        Main.logIn(NAME, PASSWORD);
        Main.creatNewRecord("6","", "+");

        String expected = "Failed to convert property value of type java.lang.String to required type int for property sk2; nested exception is java.lang.NumberFormatException: For input string: \"\"";
        String actual = browser.findElement(By.className("error")).getText();
        Assertions.assertEquals(expected, actual, "Wrong answer");
    }

//  Esamo įrašo paieška (pozityvus testas)
    @Test
    @Order(7)
    public void findRecordPositive() {
        Main.logIn(NAME, PASSWORD);
        Main.findRecord();
        Assertions.assertEquals("Skaičiai", browser.getTitle(), "Title is incorrect.");
        Assertions.assertTrue(findRecord(), "Boolean condition is not correct.");
    }

//  Esamo įrašo paieška (negatyvus testas)
    @Test
    @Order(11)
    public void findRecordNegative() {
        Main.logIn(NAME, PASSWORD);
        boolean actual = Main.findRecord();
        Assertions.assertFalse(actual, "Boolean condition is not correct.");
    }

//  Esamo įrašo keitimas (pozityvus testas)
    @Test
    @Order(8)
    public void correctRecordPositive() {
        Main.logIn(NAME, PASSWORD);
        Main.findRecord();
        Main.correctRecord("4", "+", "7", "11");
        Assertions.assertEquals("Skaičius", browser.getTitle(), "Title is incorrect.");

        String actual = browser.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[2]")).getText();
        Assertions.assertNotNull(actual, "Record is empty.");
    }

    //  Esamo įrašo keitimas (negatyvus testas)
    @Test
    @Order(9)
    public void correctRecordNegative() {
        Main.logIn(NAME, PASSWORD);
        Main.findRecord();
        Main.correctRecord("4", "+", "", "11");
        Assertions.assertNotEquals("Skaičius", browser.getTitle(), "Title is incorrect.");

        Assertions.assertTrue(browser.getPageSource().contains("Error"), "Negative test failed.");
    }

//  Esamo įrašo ištrynimas (pozityvus testas)
    @Test
    @Order(10)
    public void deleteRecordPositive() {
        Main.logIn(NAME, PASSWORD);
        Main.deleteRecord();

        Alert alert = browser.switchTo().alert();
        String confirmationMessage = "Ar tikrai norite ištrinti šį įrašą?";
        String actual = alert.getText();
        Assertions.assertEquals(confirmationMessage, actual, "Alert is not working.");
        alert.accept();
    }
//  Esamo įrašo ištrynimas (negatyvus testas)
    @Test
    @Order(12)
    public void deleteRecordNegative() {
        Main.logIn(NAME, PASSWORD);
        Main.deleteRecord();

        Alert alert = browser.switchTo().alert();
        Assertions.assertEquals("Ar tikrai norite ištrinti šį įrašą?", alert.getText(), "Alert is not working.");
        alert.dismiss();
        Assertions.assertTrue(Main.findRecord(), "No records");
    }
}