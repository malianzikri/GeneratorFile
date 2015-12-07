/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kamal Bakri
 */
public class CreateFile {

    public boolean newFile(String path, String namafile, String isi) {
        PrintWriter writer;
        Boolean result;
        try {
             if (isi.equalsIgnoreCase("")) {
                result = false;
            } else {
                writer = new PrintWriter(path + namafile + ".txt", "UTF-8");
                String[] split_isi = isi.split(",");
                for (String data : split_isi) {
                    writer.println(data);
                }
                writer.close();
                result = true;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateFile.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CreateFile.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }
        return result;
    }

    public static void main(String[] args) {
        // TODO code application logic here

    }
}
