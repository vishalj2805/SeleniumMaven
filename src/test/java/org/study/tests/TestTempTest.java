package org.study.tests;

import io.cucumber.java.hu.Ha;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class TestTempTest {

    public static void main(String[] args) throws InterruptedException, IOException {
        FileInputStream fis = new FileInputStream("/Users/vishalj/IdeaProjects/SeleniumMaven/src/test/java/org/study/data/TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet("Register");
        DataFormatter dataFormatter = new DataFormatter();


        Iterator<Row> rowIterator = sheet.rowIterator();

        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                System.out.println(dataFormatter.formatCellValue(cell));
                System.out.println(cell.getCellType());
            }

        }


    }
}
