import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class LoginTest {

    Login login = new Login("John", "Doe");

    @Test
    public void testCheckUserName_CorrectFormat() {
        assertTrue(login.checkUserName("john_"));
    }

    @Test
    public void testCheckUserName_IncorrectFormat() {
        assertFalse(login.checkUserName("john!"));
    }

    @Test
    public void testCheckPasswordComplexity_CorrectFormat() {
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexity_IncorrectFormat() {
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCheckCellPhoneNumber_CorrectFormat() {
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCheckCellPhoneNumber_IncorrectFormat() {
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }

    @Test
    public void testLoginUser_Successful() {
        login.checkUserName("user_");
        login.checkPasswordComplexity("Secure123!");
        assertTrue(login.loginUser("user_", "Secure123!"));
    }

    @Test
    public void testLoginUser_Failed() {
        login.checkUserName("user_");
        login.checkPasswordComplexity("Secure123!");
        assertFalse(login.loginUser("wrong_user", "wrongPass"));
    }
}