/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Kamal Bakri
 */
public class CopyFile {

    /**
     * @param args the command line arguments
     */
    public boolean isExecute(String fileSource, String fileDestination) {

        boolean result = false;
        byte[] buffer = new byte[1024];
        InputStream inStream = null;
        OutputStream outStream = null;
        File inputFile = null;
        File outputFile = null;

        try {
            inputFile = new File(fileSource);
            outputFile = new File(fileDestination);

            inStream = new FileInputStream(inputFile);
            outStream = new FileOutputStream(outputFile);

            int length;
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }

            inStream.close();
            outStream.close();

            result = true;
        } catch (Exception e) {
            result = false;
            e.printStackTrace();
        } finally {

            inputFile = null;
            outputFile = null;
            buffer = null;

            return result;
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
