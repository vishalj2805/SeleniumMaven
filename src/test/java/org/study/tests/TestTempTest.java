package org.study.tests;

import io.cucumber.java.hu.Ha;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class TestTempTest {

    public static void main(String[] args) throws InterruptedException, IOException {
        FileInputStream fis = new FileInputStream("/Users/vishalj/IdeaProjects/SeleniumMaven/src/test/java/org/study/data/TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Register");
        DataFormatter dataFormatter = new DataFormatter();
        String phoneNumberField = "Mobile Number";


        XSSFRow row = sheet.getRow(0);
        XSSFCell cell = row.getCell(3);
        System.out.println("Before Update: " + cell.getStringCellValue());

        cell.setCellValue(phoneNumberField);
        FileOutputStream fos = new FileOutputStream("/Users/vishalj/IdeaProjects/SeleniumMaven/src/test/java/org/study/data/TestData.xlsx");
        workbook.write(fos);
        System.out.println("After Update: " + cell.getStringCellValue());
        workbook.close();
    }
}
