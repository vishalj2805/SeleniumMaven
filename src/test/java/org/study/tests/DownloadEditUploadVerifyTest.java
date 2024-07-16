package org.study.tests;

import org.study.data.ExcelReader;
import org.study.pageobjects.DownloadUploadPage;
import org.study.testcomponents.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;

public class DownloadEditUploadVerifyTest extends TestBase {
    ExcelReader excelReader = new ExcelReader();

   @Test
    public void downloadEditUploadVerify() throws InterruptedException, IOException {
       DownloadUploadPage downloadUploadPage = new DownloadUploadPage(driver);
       String path;
       if(System.getProperty("os.name").equals("Windows 11")){
           path = "C:\\Users\\Visha\\Downloads";
       }else{
           path = "/Users/vishalj/Downloads";
       }
       downloadUploadPage.clickOnDownloadBTN();
       Assert.assertTrue(downloadUploadPage.verifyFileIsDownloadedOnComputer("download.xlsx", path));
//       downloadUploadPage.uploadFile("/Users/vishalj/Downloads/download.xlsx");
//       String successMSG = downloadUploadPage.getSuccessToastMSG();
//       System.out.println(successMSG);

       HashMap<String, Integer> updatePrices = new HashMap<>(){{
           put("Apple", 400);
           put("Orange", 300);
       }};

       downloadUploadPage.checkPricesOfFruits(new ArrayList<String>(updatePrices.keySet().stream().toList()));

       excelReader.updateEntryDetails(path + "/download.xlsx", new HashMap<String, HashMap<String, String>>(){{
           put("Orange", new HashMap<>(){{
               put("price", "400");
           }});
           put("Mango", new HashMap<>(){{
               put("price", "300");
               put("season", "All");
           }});
       }});






       Thread.sleep(3000);

    }

}
