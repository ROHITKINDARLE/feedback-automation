package com.rohit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

public class Parth {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        try {
            testCase1_ValidSubmission();
            testCase2_EmptyForm();
            testCase3_InvalidEmail();
            testCase4_InvalidMobile();
            testCase5_FeedbackTooShort();
            testCase6_DepartmentNotSelected();
            testCase7_ResetButton();
        } finally {
            driver.quit();
        }
    }

    // ---------------- HELPER METHODS ----------------

    static void openPage() throws InterruptedException {
        String path = System.getProperty("user.dir") + "/feedback.html";
        driver.get("file:///" + path.replace("\\", "/"));
        Thread.sleep(1000);
    }

    static void fillValidForm() {
        driver.findElement(By.id("name")).sendKeys("Rohit Kindarle");
        driver.findElement(By.id("email")).sendKeys("rohit@example.com");
        driver.findElement(By.id("mobile")).sendKeys("9876543210");

        Select deptDropdown = new Select(driver.findElement(By.id("department")));
        deptDropdown.selectByVisibleText("Computer Science");

        driver.findElement(By.id("genderMale")).click();

        driver.findElement(By.id("feedback")).sendKeys(
                "The course content is excellent and faculty are supportive throughout the semester");
    }

    static void clickSubmit() {
        driver.findElement(By.id("submitBtn")).click();
    }

    static void clickReset() {
        driver.findElement(By.id("resetBtn")).click();
    }

    static String handleAlert() throws InterruptedException {
        Thread.sleep(500);
        Alert alert = driver.switchTo().alert();
        String text = alert.getText();
        alert.accept();
        return text;
    }

    static void printResult(String testName, String alertText) {
        System.out.println("--------------------------------------");
        System.out.println(testName);
        System.out.println("Alert Message: " + alertText);
        System.out.println("--------------------------------------");
    }

    // ---------------- TEST CASES ----------------

    static void testCase1_ValidSubmission() throws InterruptedException {
        openPage();
        fillValidForm();
        clickSubmit();

        String alertText = handleAlert();
        printResult("TEST CASE 1: Valid Submission", alertText);
    }

    static void testCase2_EmptyForm() throws InterruptedException {
        openPage();
        clickSubmit();

        String alertText = handleAlert();
        printResult("TEST CASE 2: Empty Form", alertText);
    }

    static void testCase3_InvalidEmail() throws InterruptedException {
        openPage();

        driver.findElement(By.id("name")).sendKeys("Rohit");
        driver.findElement(By.id("email")).sendKeys("invalid-email");
        driver.findElement(By.id("mobile")).sendKeys("9876543210");

        new Select(driver.findElement(By.id("department")))
                .selectByVisibleText("IT");

        driver.findElement(By.id("genderMale")).click();

        driver.findElement(By.id("feedback")).sendKeys(
                "This feedback is long enough to pass validation");

        clickSubmit();

        String alertText = handleAlert();
        printResult("TEST CASE 3: Invalid Email", alertText);
    }

    static void testCase4_InvalidMobile() throws InterruptedException {
        openPage();

        driver.findElement(By.id("name")).sendKeys("Rohit");
        driver.findElement(By.id("email")).sendKeys("rohit@example.com");
        driver.findElement(By.id("mobile")).sendKeys("abcd12");

        new Select(driver.findElement(By.id("department")))
                .selectByVisibleText("Mechanical");

        driver.findElement(By.id("genderFemale")).click();

        driver.findElement(By.id("feedback")).sendKeys(
                "This feedback is long enough to pass validation");

        clickSubmit();

        String alertText = handleAlert();
        printResult("TEST CASE 4: Invalid Mobile", alertText);
    }

    static void testCase5_FeedbackTooShort() throws InterruptedException {
        openPage();

        driver.findElement(By.id("name")).sendKeys("Rohit");
        driver.findElement(By.id("email")).sendKeys("rohit@example.com");
        driver.findElement(By.id("mobile")).sendKeys("9876543210");

        new Select(driver.findElement(By.id("department")))
                .selectByVisibleText("Civil");

        driver.findElement(By.id("genderMale")).click();

        driver.findElement(By.id("feedback")).sendKeys("Good");

        clickSubmit();

        String alertText = handleAlert();
        printResult("TEST CASE 5: Feedback Too Short", alertText);
    }

    static void testCase6_DepartmentNotSelected() throws InterruptedException {
        openPage();

        driver.findElement(By.id("name")).sendKeys("Rohit");
        driver.findElement(By.id("email")).sendKeys("rohit@example.com");
        driver.findElement(By.id("mobile")).sendKeys("9876543210");

        driver.findElement(By.id("genderMale")).click();

        driver.findElement(By.id("feedback")).sendKeys(
                "This feedback is long enough to pass validation");

        clickSubmit();

        String alertText = handleAlert();
        printResult("TEST CASE 6: Department Not Selected", alertText);
    }

    static void testCase7_ResetButton() throws InterruptedException {
        openPage();
        fillValidForm();
        Thread.sleep(500);

        clickReset();
        Thread.sleep(500);

        String nameVal = driver.findElement(By.id("name")).getAttribute("value");
        String emailVal = driver.findElement(By.id("email")).getAttribute("value");

        System.out.println("TEST CASE 7: Reset Button");
        System.out.println("Name cleared: " + nameVal.isEmpty());
        System.out.println("Email cleared: " + emailVal.isEmpty());
    }
}