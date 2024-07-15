package org.study.tests;

import org.study.data.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterTest {


    @Test(dataProvider = "getData")
    public void registerUser(HashMap<String, String> data){
        System.out.println(data.get("First Name"));
        System.out.println(data.get("Last Name"));
        System.out.println(data.get("Email"));
        System.out.println(data.get("Phone Number"));
        System.out.println(data.get("Gender"));
        System.out.println(data.get("Occupation"));
        System.out.println(data.get("Password"));
        System.out.println(data.get("Confirm Password"));
    }

    @DataProvider
    public Object[] getData() throws IOException {
        ExcelReader excelReader = new ExcelReader();
        List<HashMap<String, String>> data =excelReader.getRegisterData();
        System.out.println(data.size());
        HashMap[] registerData = new HashMap[data.size()];

        for (int i=0;i<data.size();i++){
            registerData[i] = data.get(i);
        }

        return registerData;
    }
}
