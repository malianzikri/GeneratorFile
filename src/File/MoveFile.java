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
public class MoveFile {

    /**
     * @param fileSource
     * @param fileDestination
     * @return
     */
    public boolean isExecute(String fileSource, String fileDestination) {
        boolean result = false;
        try {
            File oldfile = new File(fileSource);
            result = oldfile.renameTo(new File(fileDestination));
        } catch (Exception e) {
        }
        return result;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        MoveFile mf = new MoveFile();
        mf.isExecute("D:\\CONTOH\\4.txt", "D:\\HASIL\\4.txt");
    }

}
