package org.example;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class MainTest {

    @Test(invocationCount = 3)
    public void testMain() {
        Assert.assertTrue(true);
    }

    @Test(dataProvider = "SomeData")
    public void paramsExample(String a, String b){
        System.out.println("a : " + a );
        System.out.println("b : " + b );
        Assert.assertEquals(a, b);
    }

    @Test
    @Description("Проверка стартовой страницы")
    public void orangeHrmTesting() throws InterruptedException, FileNotFoundException {
        open("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        SelenideElement username = $(By.name("username"));
        username.sendKeys("Admin");
        username.pressEnter();
        SelenideElement error = $(By.xpath("//span[text()='Required']"));
        File file = screenshot(OutputType.FILE);
        Allure.addAttachment("name_screen", new FileInputStream(file));
        error.shouldHave(text("Required"));
    }


    @Test
    @Description("Проверка авторизации + фейл")
    public void orangeHrmPageObjectTesting(){
        AuthPage page = new AuthPage();
        page.open().auth();
        SelenideElement element = $(By.id("ID"));
        element.click();
    }

    @DataProvider(name = "SomeData")
    public Iterator<Object[]> someDataGenerator() throws IOException {
        List<Object []> testCases = new ArrayList<>();

        String line;
        String[] data;
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader("/Users/alisa_school/java_lessons/mvn/MavenExample/src/test/resources/data.csv"));
        while ((line = bufferedReader.readLine()) != null){
            data = line.split(",");
            testCases.add(data);
        }
        return testCases.iterator();
    }
}