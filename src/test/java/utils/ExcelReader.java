package utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;

import java.util.*;

public class ExcelReader {

        public static String path;
        public static FileInputStream fis = null;
        private static XSSFWorkbook workbook = null;
        private static XSSFSheet sheet = null;

        private static XSSFCell cell = null;

        public static void getPath(String pathToExcelFile) {

            path = pathToExcelFile;
            try {
                fis = new FileInputStream(path);
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);
                fis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // returns the row count in a sheet
        public static int getRowCount(String sheetName) {
            int index = workbook.getSheetIndex(sheetName);
            if (index == -1)
                return 0;
            else {
                sheet = workbook.getSheetAt(index);
                int number = sheet.getLastRowNum();
                return number;
            }
        }

        public static Map<String, String> getMap(String path, int rowNum) {
            getPath(path);
            XSSFRow xssfRow = sheet.getRow(rowNum);
            Map<String, String> data = new HashMap<>();
            //Reading data from excel to HashMap
            int cell = xssfRow.getLastCellNum();
            for (int i = 0; i < cell; i++) {

                XSSFCell cellKey = sheet.getRow(0).getCell(i);
                cellKey.setCellType(CellType.STRING);
                String key = cellKey.getStringCellValue();

                XSSFCell cellValue = sheet.getRow(rowNum).getCell(i);
                cellValue.setCellType(CellType.STRING);
                String value = cellValue.getStringCellValue();

                data.put(key, value);
            }

            return data;

        }


        public static List<Map<String, String>> listOfMaps(String path, String sheetName) {
            getPath(path);
            List<Map<String, String>> listOfMap = new ArrayList<>();
            int rows = getRowCount(sheetName);
            for (int r = 1; r < rows; r++) {
                listOfMap.add(getMap(path, r));
            }
            return listOfMap;
        }

    public static void main(String[] args) {

            String path = "MOCK_DATA.xlsx";

        System.out.println("getMap(path,5) = " + getMap(path, 5));
        System.out.println("listOfMaps(path,\"Sheet1\") = " + listOfMaps(path, "Sheet1"));
    }
}
