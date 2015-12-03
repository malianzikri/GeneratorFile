/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import java.io.File;

/**
 *
 * @author Kamal Bakri
 */
public class DeleteFile {

    public void DeleteAll(String source) {
        try {

            File directory = new File(source);

            File[] files = directory.listFiles();
            if (files.length != 0) {
                for (File file : files) {
                    if (file.delete()) {
                        System.out.println(file.getName() + " is deleted!");
                    } else {
                        System.out.println(file.getName() + " is failed.");
                    }
                }
            } else {
                System.out.println("Direktori Kosong atau Salah !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Delete(String source, String filename) {
        try {

            File file = new File(source + "\\" + filename);
            if (file.delete()) {
                System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println(file.getName() + " is failed.");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        DeleteFile df = new DeleteFile();
        //df.Delete("D:\\Hasil\\04-12-2015","7.txt");
        df.DeleteAll("D:\\Hasil\\03-12-2015");
    }

}
