/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.DOC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import static java.lang.System.out;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.CharacterRun;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 *
 * @author Kamal Bakri
 */
public class WriteDoc {

    /**
     * @param args the command line arguments
     */
    public void Write(String path, String namafile, String content) {
        File file = new File("D:\\xyz.doc");
        try {
            POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file));
            HWPFDocument doc = new HWPFDocument(fs);
            Range range = doc.getRange();
            CharacterRun run = range.insertBefore(content.replace("\n", "\013"));
            run.setBold(true);
            OutputStream outa = new FileOutputStream(new File(path + namafile + ".doc"));
            doc.write(outa);
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        WriteDoc wd = new WriteDoc();
        String content = "ini merupakan paragraph pertama\n"
                + "ini paragraph kedua\n"
                + "ini paragraph ketiga\n"
                + "ini paragraph keempat\n";
        wd.Write("D:\\", "sample1", content);
    }

}
