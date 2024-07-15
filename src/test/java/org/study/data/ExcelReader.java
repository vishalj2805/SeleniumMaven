package org.study.data;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ExcelReader {
    DataFormatter dataFormatter = new DataFormatter();


    public static void main(String[] args) throws IOException {
        ExcelReader excelReader = new ExcelReader();
        List<HashMap<String, String>> data = excelReader.getRegisterData();
        System.out.println(data.size());
        System.out.println(data.getFirst().size());
//        HashMap<String, String> registerData =
//
//
//        for (int i=0;i<data.size();i++){
//            for (int j=0;j<=data.getFirst().size()-1;j++){
//                registerData[i][j] = data.get(i).entrySet().stream().toList().get(j).getValue();
//            }
//        }
//
//        System.out.println(Arrays.deepToString(registerData));

    }


    public HashMap<String, String> getCredentialsData() throws IOException {
//        ExcelReader excelReader = new ExcelReader();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/org/study/data/TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = null;
        int noOfSheets = workbook.getNumberOfSheets();

        for (int i = 0; i < noOfSheets; i++) {
            if (workbook.getSheetName(i).equals("Credentials")) {
                sheet = workbook.getSheetAt(i);
            }
        }

        HashMap<String, String> credentials = new HashMap<>();
//        System.out.println(getIndexOfColumn(sheet, "Username"));
//        System.out.println(getCellValueOfColumn(sheet, getIndexOfColumn(sheet, "Password")));

        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            credentials.put(getCellValueOfColumn(sheet, getIndexOfColumn(sheet, "Username")).get(i),
                    getCellValueOfColumn(sheet, getIndexOfColumn(sheet, "Password")).get(i));

        }

        System.out.println(credentials);

        return credentials;

    }

    public List<HashMap<String, String>> getRegisterData() throws IOException {
//        ExcelReader excelReader = new ExcelReader();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/org/study/data/TestData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = null;
        int noOfSheets = workbook.getNumberOfSheets();

        for (int i = 0; i < noOfSheets; i++) {
            if (workbook.getSheetName(i).equals("Register")) {
                sheet = workbook.getSheetAt(i);
            }
        }

        List<HashMap<String, String>> credentials = new ArrayList<>();

//        System.out.println(getIndexOfColumn(sheet, "Username"));
//        System.out.println(getCellValueOfColumn(sheet, getIndexOfColumn(sheet, "Password")));

        for (int i = 0; i < sheet.getLastRowNum(); i++) {
            HashMap<String, String> data = new HashMap<>();
            data.put("First Name", String.valueOf(getCellValueOfColumn(sheet, getIndexOfColumn(sheet, "First Name")).get(i)));
            data.put("Last Name", String.valueOf(getCellValueOfColumn(sheet, getIndexOfColumn(sheet, "Last Name")).get(i)));
            data.put("Email", String.valueOf(getCellValueOfColumn(sheet, getIndexOfColumn(sheet, "Email")).get(i)));
            data.put("Phone Number", String.valueOf(getCellValueOfColumn(sheet, getIndexOfColumn(sheet, "Phone Number")).get(i)));
            data.put("Occupation", String.valueOf(getCellValueOfColumn(sheet, getIndexOfColumn(sheet, "Occupation")).get(i)));
            data.put("Gender", String.valueOf(getCellValueOfColumn(sheet, getIndexOfColumn(sheet, "Gender")).get(i)));
            data.put("Password", String.valueOf(getCellValueOfColumn(sheet, getIndexOfColumn(sheet, "Password")).get(i)));
            data.put("Confirm Password", String.valueOf(getCellValueOfColumn(sheet, getIndexOfColumn(sheet, "Confirm Password")).get(i)));
            credentials.add(data);
        }

//        System.out.println(credentials);

        return credentials;

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
                    if (data.entrySet().stream().toList().get(j).getValue().entrySet().stream().toList().get(k).getKey().equals(cell.getStringCellValue())) {
                        System.out.println(cell.getStringCellValue());
//                        columnNos.put(cell.getStringCellValue(), i);
                        row = sheet.getRow(rowNo);
                        cell = row.getCell(i);
                        System.out.println(cell.getNumericCellValue());
                        System.out.println(data.entrySet().stream().toList().get(j).getKey());
                        System.out.println(data.entrySet().stream().toList().get(j).getValue().entrySet().stream().toList().get(k).getValue().toString());
                        cell.setCellValue(data.entrySet().stream().toList().get(j).getValue().entrySet().stream().toList().get(k).getValue().toString());
                    }
                }
            }
        }
//        System.out.println(columnNos.entrySet());



    }
}
