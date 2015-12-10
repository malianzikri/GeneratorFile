/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.DOCX;

import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;

/**
 *
 * @author Kamal Bakri
 */
public class ReadDocx {

    /**
     * @param args the command line arguments
     */
    public void ReadParagraph(String path,String filename) {
        try {
            FileInputStream fis = new FileInputStream(path+filename+".docx");
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            List<XWPFParagraph> paragraphList = xdoc.getParagraphs();
            for (XWPFParagraph paragraph : paragraphList) {
                System.out.println(paragraph.getText());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void ReadTable(String path,String filename) {
        try {
            FileInputStream fis = new FileInputStream(path+filename+".docx");
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            Iterator<IBodyElement> bodyElementIterator = xdoc.getBodyElementsIterator();
            while (bodyElementIterator.hasNext()) {
                IBodyElement element = bodyElementIterator.next();
                if ("TABLE".equalsIgnoreCase(element.getElementType().name())) {
                    List<XWPFTable> tableList = element.getBody().getTables();
                    for (XWPFTable table : tableList) {
                        System.out.println("Total Number of Rows of Table:" + table.getNumberOfRows());
                        System.out.println(table.getText());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void ReadAll(String path,String filename) {
        try {
            FileInputStream fis = new FileInputStream(path+filename+".docx");
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
            System.out.println(extractor.getText());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        ReadDocx rd = new ReadDocx();
        rd.ReadAll("D:\\","hasil2");
        //rd.ReadParagraph();
        //rd.ReadTable();
    }

}
