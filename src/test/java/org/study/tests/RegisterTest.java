package org.study.tests;

import org.study.data.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterTest {
    ExcelReader excelReader = new ExcelReader();

    @Test(dataProvider = "getData")
    public void registerUser(String fName, String lName, String email, String phoneNumber, String gender,
                             String occupation, String password, String confirmPassword){
        System.out.println(fName);
        System.out.println(lName);
        System.out.println(email);
        System.out.println(phoneNumber);
        System.out.println(gender);
        System.out.println(occupation);
        System.out.println(password);
        System.out.println(confirmPassword);
    }

    @DataProvider
    public Object[][] getData() throws IOException {

        return excelReader.getDataFromSheet("Register");
    }
}
