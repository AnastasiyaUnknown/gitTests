import io.qameta.allure.Step;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProfileFunctions {

    String user = "";
    String password  = "";

    @Step("Авторизация")
    public void auth(){
        $(".HeaderMenu").$(byText("Sign in")).waitUntil(appear, 10000).click();
        $(".auth-form-body.mt-3").waitUntil(visible, 15000);
        $("#login_field").sendKeys(user);
        $("#password").sendKeys(password);
        $(byName("commit")).click();
        $(".mb-3.Details.js-repos-container.mt-5").shouldBe(visible);
    }

    @Step("Переход в Профиль")
    public void openProfile(){
        $$(".details-overlay.details-reset").get(1).$(".avatar").click();
        $(".header-nav-current-user").shouldBe(text(user));
        $(byText("Your profile")).click();
    }

    public void goToEdit(){
        $(".new-user-avatar-cta").$(byText("Edit profile")).click();
        $("#user_profile_bio").waitUntil(appear, 20000);
    }

    @Step("Редактирование биографии и локации")
    public void editBioLocation(String bio, String location){
        goToEdit();
        $("#user_profile_bio").clear();
        $("#user_profile_bio").sendKeys(bio);

        $("#user_profile_location").clear();
        $("#user_profile_location").sendKeys(location);
        $(byText("Update profile")).click();
        $("header").scrollTo();
        $(".flash.flash-full").waitUntil(appear, 10000);
    }

    @Step("Добавление аватарки")
    public void newAvatar(){
        goToEdit();
        int numberFile = (int)(Math.random()*2)+1;
        $("#avatar_upload").uploadFile(new File("resources/cat" + numberFile + ".jpg"));
        $(".Box-footer").waitUntil(appear, 10000);
        $(".Box-footer").$(byValue("save")).click();
        $("header").scrollTo();
        $(".flash.flash-full").waitUntil(appear, 10000);
    }
}