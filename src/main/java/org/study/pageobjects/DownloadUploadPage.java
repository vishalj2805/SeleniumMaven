package org.study.pageobjects;

import com.aventstack.extentreports.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.study.abstractcomponents.AbstractComponents;

import java.io.File;
import java.text.Format;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DownloadUploadPage extends AbstractComponents {
    WebDriver driver;
    WebDriverWait wait;

    public DownloadUploadPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }

    @FindBy(xpath = "//button[@id='downloadButton']")
    WebElement downloadBTN;

    @FindBy(xpath = "(//div[@class='Toastify']//div)[5]")
    WebElement successToastMSG;

    @FindBy(xpath = "//input[@id='fileinput']")
    WebElement uploadBTN;

    @FindBy(xpath = "//div[contains(@class,'TableHead')]//div[@role='columnheader']/div")
    List<WebElement> fruitsTableColumnNames;

    @FindBy(xpath = "//div[contains(@class,'TableBody')]")
    WebElement fruitsTableBody;

    public void clickOnDownloadBTN(){
        downloadBTN.click();
    }

    public boolean verifyFileIsDownloadedOnComputer(String fileName){
        File folder = new File("/Users/vishalj/Downloads");
        File[] listOfFiles = folder.listFiles();

        for(File file: listOfFiles){
            if (file.getName().equals(fileName)){
                return true;
            }
        }
        return false;
    }

    public String getSuccessToastMSG(){
        wait.until(ExpectedConditions.visibilityOf(successToastMSG));
        String successMSG = successToastMSG.getText();
        wait.until(ExpectedConditions.invisibilityOf(successToastMSG));
        return successMSG;

    }

    public void uploadFile(String path){
        uploadBTN.sendKeys(path);
    }

    public void checkPricesOfFruits(ArrayList<String> fruits){
        int fruitNameColumnIndex = 1;
        for (int i=0;i<fruitsTableColumnNames.size();i++){
            if(fruitsTableColumnNames.get(i).getText().equals("Fruit Name")){
                fruitNameColumnIndex = i+1;
            }

        }

        int fruitPriceColumnIndex = 1;
        for (int i=0;i<fruitsTableColumnNames.size();i++){
            if(fruitsTableColumnNames.get(i).getText().equals("Price")){
                fruitPriceColumnIndex = i+1;
            }

        }

        List<WebElement> fruitsColumn = fruitsTableBody.findElements(By.xpath(String.format("./div//div[%d]/div", fruitNameColumnIndex)));

        System.out.println(fruits.get(0));
        for (int i=0;i<fruits.size();i++){
            for (int j=0;j<fruitsColumn.size();j++){
                if (fruits.get(i).equals(fruitsColumn.get(j).getText())){
                    List<WebElement> pricesColumn = fruitsTableBody.findElements(By.xpath(String.format("./div//div[%d]/div", fruitPriceColumnIndex)));
                    System.out.println("Current Price of " + fruits.get(i) + " is " + pricesColumn.get(j).getText());
                }
            }
        }

    }
}
