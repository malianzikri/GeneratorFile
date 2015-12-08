/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.XLS;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author Kamal Bakri
 */
public class ReadXLS {

    public void readXLSFile(String path, String filename, int Fcol, int Lcol, int Frow, int Lrow) {
        try {
            //String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

            FileInputStream file = new FileInputStream(new File(path + filename + ".xls"));

            //Create Workbook instance holding reference to .xlsx file
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            
            HSSFSheet sheet = workbook.getSheetAt(0);
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
        ReadXLS rx = new ReadXLS();
        rx.readXLSFile("D:\\", "file", 0, 2, 0, 11);
    }

}
