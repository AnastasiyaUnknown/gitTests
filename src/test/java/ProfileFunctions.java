import io.qameta.allure.Step;

import java.io.File;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProfileFunctions {

    final String  USER = "AnastasiyaUnknown";
    final String PASSWORD  = "unknown2109";

    @Step("Auth")
    public void auth(){
        $(".HeaderMenu").$(byText("Sign in")).waitUntil(appear, 10000).click();
        $(".auth-form-body.mt-3").waitUntil(visible, 15000);
        $("#login_field").sendKeys(USER);
        $("#password").sendKeys(PASSWORD);
        $(byName("commit")).click();
        $(".mb-3.Details.js-repos-container.mt-5").shouldBe(visible);
    }

    @Step("Go to Profile")
    public void openProfile(){
        $$(".details-overlay.details-reset").get(1).$(".avatar").click();
        $(".header-nav-current-user").shouldBe(text(USER));
        $(byText("Your profile")).click();
    }

    public void goToEdit(){
        $(".avatar-before-user-status").click();
//        $(".d-none.d-md-block").$(byText("Edit profile")).click();
        $("#user_profile_bio").waitUntil(appear, 20000);
    }

    @Step("Edit bio and location")
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

    @Step("Add avatar")
    public void newAvatar(){
        goToEdit();
        int numberFile = (int)(Math.random()*2)+1;
        $("#avatar_upload").uploadFile(new File("resources/cat" + numberFile + ".jpg"));
        $(".Box-footer").waitUntil(appear, 10000);
        $(".Box-footer").$(byValue("save")).click();
        $("header").scrollTo();
        $(".flash.flash-full").waitUntil(appear, 10000);
    }

    @Step("Go to change status")
    public void goToStatus(){
        $$(".details-overlay.details-reset").get(1).$(".avatar").click();
        $(".header-nav-current-user").shouldBe(text(USER));
        $(".js-user-status-container").click();
    }

    @Step("Add status")
    public void addStatus(){
        $(byName("message")).waitUntil(appear, 10000);
        if($(byText("Clear status")).is(disabled)) {
            $(".d-flex.flex-items-center.flex-justify-between.p-3.border-top").$(byText("Clear status")).click();
            $(".js-user-status-container").click();
            $("#limited-availability-truncate-true-compact-true").click();
        }

        $(byName("message")).shouldBe(value("I may be slow to respond."));
        $("div.dropdown-caret").click();
        $(byText("in 30 minutes")).click();
        $(byText("Set status")).click();
    }
}