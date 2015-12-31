/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Kamal Bakri
 */
public class ClusterFile {

    public void moveFile(String source, String destination, String newFolder, String namaFile) {
        try {

            File oldfile = new File(source + "\\" + namaFile);

            if (oldfile.renameTo(new File(destination + "\\" + newFolder + "\\" + oldfile.getName()))) {
                System.out.println(namaFile + " is moved successful!");
            } else {
                System.out.println(namaFile + " is failed to move!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createDirektori(String direktori, String newFolder) {
        File file = new File(direktori + "\\" + newFolder);
        if (file.mkdirs()) {
            return true;
        } else {
            return false;
        }
    }

    public void cekDirektori(String source, String destination, String newFolder, String namaFile) {
        File file = new File(destination + "\\" + newFolder);
        if (!file.exists()) {
            if (createDirektori(destination, newFolder)) {
                moveFile(source, destination, newFolder, namaFile);
            }
        } else {
            moveFile(source, destination, newFolder, namaFile);
        }
    }

    public void execute(String source, String destination) {
        File directory = new File(source);

        if (directory.exists()) {
            File[] files = directory.listFiles();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            if (files.length == 0) {
                System.out.println("Direktori Kosong !");
            } else {
                for (File file : files) {
                    Date lastMod = new Date(file.lastModified());
                    String tanggal = sdf.format(lastMod);
                    String namaFile = file.getName();
                    cekDirektori(source, destination, tanggal, namaFile);
                }
            }
        } else {
            System.out.println("Direktori tidak ada !");
        }
    }

    public static void main(String[] args) throws IOException {
        ClusterFile cf = new ClusterFile();
        cf.execute("D:\\CONTOH", "D:\\HASIL");
    }
}
