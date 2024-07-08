package org.study.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class TestTempTest {

    public static void main(String[] args) throws InterruptedException {
       int[] arr ={5,2,3,1,4};
       System.out.println("Array Before Sorting: " + Arrays.toString(arr));
       for (int i=0;i<arr.length;i++){
           for (int j=i; j<arr.length-1;j++){
               if (arr[i] > arr[j+1]){
                   int temp = arr[i];
                   arr[i] = arr[j+1];
                   arr[j+1] = temp;
               }
           }
       }
        System.out.println("Sorting of Array is: " + Arrays.toString(arr));
    }
}
