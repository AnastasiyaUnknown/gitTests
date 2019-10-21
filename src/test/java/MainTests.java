import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.*;

public class MainTests extends AppManager{

    ProfileFunctions profFunc = new ProfileFunctions();
    String url = "https://github.com";

    @Test
    @DisplayName("Edit bio and location")
    public void editInfoAboutUser(){
        open(url);
        profFunc.auth();
        profFunc.openProfile();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String newBio = "Немного биографии обо мне на " + dateFormat.format(new Date());
        String location = "Kazan city";
        profFunc.editBioLocation(newBio, location);
        assertEquals(newBio, $("#user_profile_bio").getText());
        assertEquals(location, $("#user_profile_location").getValue());
    }

    @Test
    @DisplayName("Upload new avatar")
    public void uploadAvatar(){
        open(url);
        profFunc.auth();
        profFunc.openProfile();
        profFunc.newAvatar();
        assertEquals("Your profile picture has been updated. It may take a few moments to update across the site.", $("#js-flash-container").getText());
    }

    @Test
    @DisplayName("Set status")
    public void addNewStatus(){
        open(url);
        profFunc.auth();
        profFunc.goToStatus();
        profFunc.addStatus();
        assertEquals("I may be slow to respond.", $(".d-inline-block.text-gray-dark.v-align-text-top.text-left").getText());
    }
}