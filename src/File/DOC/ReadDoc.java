/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.DOC;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;

/**
 *
 * @author Kamal Bakri
 */
public class ReadDoc {

    /**
     * @param args the command line arguments
     */
    public void ReadParagraph() {
        try {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void ReadTable() {
        try {
            FileInputStream fistream = new FileInputStream("D:\\test.doc");
            HWPFDocument document = new HWPFDocument(fistream);
            System.out.println("Total Number of Rows of Table :"
                    + document.characterLength());
            System.out.println(document.getText());
            //System.out.println(document.getDocumentText());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void ReadAll() {
        try {
            File file = new File("D:\\test.doc");
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());

            HWPFDocument doc = new HWPFDocument(fis);

            WordExtractor we = new WordExtractor(doc);

            String[] paragraphs = we.getParagraphText();

            System.out.println("Total no of paragraph " + paragraphs.length);
            for (String para : paragraphs) {
                System.out.println(para.toString());
            }
            fis.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        ReadDoc rd = new ReadDoc();
        rd.ReadTable();
    }

}
