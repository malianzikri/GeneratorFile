/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File_TXT;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Malian
 */
public class ReadFile {

    /**
     * @param args the command line arguments
     */
    public String read(String path, String nama_file) {

        String hasil = "";
        File f = new File(path + nama_file);
        if (f.canRead()) {
            try {
                System.out.println("can read");
                hasil = FileUtils.readFileToString(f);
                return hasil;
            } catch (IOException ex) {
                hasil = ex.getMessage();
                Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return hasil;
    }

    public ArrayList read_all_files(String path) {
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        };
        ArrayList hasil = new ArrayList();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles(filter);
        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            try {
                if (file.canRead()) {
                    String content = FileUtils.readFileToString(file);
                    hasil.add(content);
                }
                else
                {
                    hasil.add("kosong");
                }
            } catch (IOException ex) {
                Logger.getLogger(ReadFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return hasil;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        ReadFile rf = new ReadFile();
        ArrayList hasil = new ArrayList();
        hasil = rf.read_all_files("E:\\malian\\Belajar");
        System.out.println(hasil.get(1));
    }

}
