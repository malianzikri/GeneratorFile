/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.XLS;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Kamal Bakri
 */
public class CreateXLS {

    /**
     * @param args the command line arguments
     */
    public void CreateNew(String data, int Frow, int Fcol, String path, String namafile) {
        //Blank workbook
        HSSFWorkbook workbook = new HSSFWorkbook();

        //Create a blank sheet
        HSSFSheet sheet = workbook.createSheet("sheet 1");

        int rownum = Frow;
        String[] split_row = data.split(";");
        for (String data_row : split_row) {
            int cellnum = Fcol;
            Row row = sheet.createRow(rownum++);
            String[] split_cols = data_row.split(",");
            for (String data_cols : split_cols) {
                Cell cell = row.createCell(cellnum++);
                cell.setCellValue(data_cols);
            }
        }
        try {
            //Write the workbook in file system
            File file = new File(path + namafile + ".xls");
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            System.out.println("Berhasil membuat file");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        CreateXLS cxf = new CreateXLS();
        String data = "NAMA,UMUR,JENIS KELAMIN;KAMAL,22,Laki-laki;BAKRI,22,Laki-laki";
        cxf.CreateNew(data, 0, 0, "D:\\", "file1");
    }

}
