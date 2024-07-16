package org.study.data;

import io.cucumber.java.an.E;
import io.cucumber.java.sl.Ce;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.*;
import java.util.*;

public class ExcelReader {
    DataFormatter dataFormatter = new DataFormatter();

    public String[][] getDataFromSheet(String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/org/study/data/TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        String[][] data = new String[sheet.getPhysicalNumberOfRows()-1][sheet.getRow(0).getPhysicalNumberOfCells()];
        for (int i=1;i<sheet.getPhysicalNumberOfRows();i++){
            XSSFRow row = sheet.getRow(i);
            for (int j=0;j<row.getPhysicalNumberOfCells();j++){
                data[i-1][j] = dataFormatter.formatCellValue(row.getCell(j));
            }
        }
        return data;
    }

    public int getIndexOfColumn(XSSFSheet sheet, String columnName) {
        int index = 1;
        Iterator<Row> rows = sheet.iterator();
        while (rows.hasNext()) {
            Row row = rows.next();
            Iterator<Cell> cells = row.cellIterator();
            while (cells.hasNext()) {
                Cell cellValue = cells.next();
//                System.out.println(cellValue.getStringCellValue());
                if (cellValue.getStringCellValue().equals(columnName)) {
                    break;
                }
                index++;
            }
            break;
        }
        return index;
    }

    public ArrayList<String> getCellValueOfColumn(XSSFSheet sheet, int columnIndex) {
        ArrayList<String> value = new ArrayList<>();
        Iterator<Row> rows = sheet.iterator();
        rows.next();
        while (rows.hasNext()) {
            Row row = rows.next();
            Iterator<Cell> cells = row.cellIterator();
            Cell cellValue = null;
            for (int i = 0; i < columnIndex; i++) {
                cellValue = cells.next();
            }
//            System.out.println(cellValue.getStringCellValue());
            value.add(cellValue.getStringCellValue());
        }

        return value;

    }


    public <T> void updateEntryDetails(String path, HashMap<T, HashMap<T, T>> data) throws IOException {
        ExcelReader excelReader = new ExcelReader();
        FileInputStream fis = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rowNo = 0;
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            XSSFRow row = sheet.getRow(i);
            for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                XSSFCell cell = row.getCell(j);
                if (cell.getCellType().toString().equals("NUMERIC")) {
                    continue;
                } else {
                    for (int k=0;k<data.size();k++){
                        if (cell.getStringCellValue().equals(data.entrySet().stream().toList().get(k).getKey())) {
                            System.out.println(cell.getStringCellValue());
                            rowNo = i;
                            break;
                        }
                    }

                }
            }

        }
        System.out.println(rowNo);

//        HashMap<String, Integer> columnNos = new HashMap<>();
        XSSFRow row = sheet.getRow(0);
        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            XSSFCell cell = row.getCell(i);
            for (int j = 0; j < data.size(); j++) {
                for (int k=0;k<data.entrySet().stream().toList().get(j).getValue().size();k++){
                    if (data.entrySet().stream().toList().get(j).getValue().entrySet().stream().toList().get(k).getKey().equals(cell.getRawValue())) {
                        System.out.println(cell.getStringCellValue());
//                        columnNos.put(cell.getStringCellValue(), i);
                        row = sheet.getRow(rowNo);
                        cell = row.getCell(i);
                        System.out.println(cell.getNumericCellValue());
                        System.out.println(data.entrySet().stream().toList().get(j).getKey());
                        System.out.println(data.entrySet().stream().toList().get(j).getValue().entrySet().stream().toList().get(k).getValue().toString());
                        cell.setCellValue(Integer.parseInt(data.entrySet().stream().toList().get(j).getValue().entrySet().stream().toList().get(k).getValue().toString()));
                    }
                }
            }
        }
        FileOutputStream fos = new FileOutputStream(path);
        workbook.write(fos);
        workbook.close();
        fis.close();
        fos.close();
//        System.out.println(columnNos.entrySet());



    }
}
