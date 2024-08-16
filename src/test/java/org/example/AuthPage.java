package org.example;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AuthPage {

    private By usernamePath = By.xpath("//*[@name='username']");
    private By passwordPath = By.xpath("//*[@name='password']");
    private By buttonPath = By.xpath("//button[@type='submit']");

    public AuthPage open(){
        Selenide.open("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        return this;
    }

    @Step("Авторизация")
    public void auth(){
        SelenideElement username = $(usernamePath);
        username.sendKeys("Admin");
        SelenideElement password = $(passwordPath);
        password.sendKeys("admin123");
        SelenideElement button = $(buttonPath);
        button.click();
    }

}
