package use_case.login;

import data_access.AccountInfoDAO;
import entities.CommonUserFactory;
import entities.User;
import entities.UserFactory;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginInteractorTest {

    @Test
    void successTest() throws IOException {
        LoginInputData inputData = new LoginInputData("bill", "password1");
        AccountInfoDAO userRepo = new AccountInfoDAO();

        UserFactory factory = new CommonUserFactory();
        User user = factory.create("bill", "billybill@gmail.com", "password1");
        userRepo.addUser(user);

    }
}
