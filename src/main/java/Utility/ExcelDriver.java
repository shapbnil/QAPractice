package Utility;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.IOException;

 

import org.apache.poi.ss.usermodel.DataFormatter;

import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

 

public class ExcelDriver {

    

     /**

     * (NOTES)

     * First you have to take control of workbook

     * If you open workbook which is the same as excel in the beginning.

      * To initate you have to traverse from parent to child

     * First you have to open workbook

     * In workbook you have to pick out the sheet, then the row in that sheet, then the column in that row.

     * Then you have to grab the value in that column or set the value in that column.

      * @throws IOException

      *

      */

    

     public static FileInputStream fis;

     public static XSSFWorkbook wb;

     public static XSSFSheet sheet; 

     public static XSSFRow row; 

     public static XSSFCell cell; 

    

     //Use this method to get data from excel sheet.

    

     public static String getCelldata(int rownum, int col, String Sheet) throws IOException

     {

         

          //Use Fileinputstream to create class called fis and set path for excel sheet in our local system.

          fis = new FileInputStream("src/main/java/test-data/QAData.xlsx");

          //To create workbook, or excel you have to have to start with XSSF class and pass the class from path from previous step.

          wb = new XSSFWorkbook(fis);

          //pass the sheet name by using workbook class (wb.getSheet) then pass it into a sheet object

          sheet = wb.getSheet(Sheet);

         DataFormatter formatter = new DataFormatter();

          return formatter.formatCellValue(sheet.getRow(rownum).getCell(col));

          /*//pass the row name by using sheet class then pass the row object.

          row = sheet.getRow(rownum);

          //pass the column/cell name by using the row class and pass the column/cell object.

          cell = row.getCell(col);*/

          ////return cell string value globally

          //return cell.getStringCellValue();

         

     }

    

     ///Use this method to set data into excel sheet.

    

     @SuppressWarnings({ "deprecation", "static-access" })

     public static String setCelldata(String data, int rownum, int col, String NameSheet) throws IOException

     {

          //Use Fileinputstream to create class called fis and set path for excel sheet in our local system.

          fis = new FileInputStream("\\test-data\\QAData.xlsx");

          //To create workbook, or excel you have to have to start with XSSF class and pass the class from path from previous step.

          wb = new XSSFWorkbook(fis);

          //pass the sheet name by using workbook class (wb.getSheet) then pass it into a sheet object

          sheet = wb.getSheet(NameSheet);

          //pass the row name by using sheet class then pass the row object.

          row = sheet.getRow(rownum);

          //pass the column/cell name by using the row class and pass the column/cell object.

          cell=row.getCell(col);

          cell.setCellType(cell.CELL_TYPE_STRING);

          cell.setCellValue(data);

          FileOutputStream fos = new FileOutputStream("\\test-data\\QAData.xlsx");

          wb.write(fos);

          fos.close();

          //return cell string value globally

          String celldata1 = cell.getStringCellValue();

          return celldata1;

     }

 

 

}
