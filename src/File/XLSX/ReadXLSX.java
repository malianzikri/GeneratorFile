/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.XLSX;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Kamal Bakri
 */
public class ReadXLSX {

    /**
     * @param args the command line arguments
     */
    public void readXLSXFile(String path, String filename, int Fcol, int Lcol, int Frow, int Lrow) {
        try {
            //String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

            FileInputStream file = new FileInputStream(new File(path + filename + ".xlsx"));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first and last colomn in file
            //int Fcols = (int) upper.indexOf(Fcol.toUpperCase());
            //int Lcols = (int) upper.indexOf(Lcol.toUpperCase());
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int x = Frow; x < Lrow; x++) {
                Row rows = sheet.getRow(x);
                if (rows != null) {
                    String key = null;
                    for (int colIndex = Fcol; colIndex <= Lcol; colIndex++) {
                        Cell cell = rows.getCell(colIndex);
                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case Cell.CELL_TYPE_NUMERIC:
                                    System.out.print(cell.getNumericCellValue() + "\t");
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    System.out.print(cell.getStringCellValue() + "\t");
                                    break;
                            }
                        }
                    }
                }
                System.out.println("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        // TODO code application logic here
        ReadXLSX rx = new ReadXLSX();
        rx.readXLSXFile("D:\\", "coba", 0, 2, 0, 11);
    }

}
