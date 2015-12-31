/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLGenerator;

import File.TXT.CreateFile;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Kamal Bakri
 */
public class ParsingXML {

    Element elemen[] = null;

    private String xmlParsing(String xml, String root, String tagging) {
        String[] header;
        List listchild = null;
        Element elemen[] = null;
        List list = null;
        String result = "";

        xml = xml.replace("xmlns", "doc");
        xml = xml.replace("xmlns=\"\"", "");
        xml = xml.replaceAll(" /", "/");

        SAXBuilder builder = new SAXBuilder();

        try {
            Document documentparse = builder.build(new StringReader(xml));
            int index = 0;
            header = root.split(">");
            int jml_header = header.length;
            elemen = new Element[jml_header];
            for (String head : header) {
                if (index == 0) {
                    elemen[index] = documentparse.getRootElement();
                    list = elemen[index].getChildren(head);
                    jml_header--;
                    if (jml_header == 0) {
                        for (int j = 0; j < list.size(); j++) {
                            Element node = (Element) list.get(j);
                            String[] split_tagging = tagging.split(",");
                            int cek = 1;
                            for (String tag : split_tagging) {
                                result += node.getChildText(tag);
                                result += (cek == split_tagging.length) ? "" : ",";
                                cek++;
                            }
                            result += ";";
                        }
                    }
                } else {
                    jml_header--;
                    if (list.size() == 1) {
                        elemen[index] = (Element) list.get(0);
                        list = elemen[index].getChildren(head);
                        if (jml_header == 0) {
                            for (int j = 0; j < list.size(); j++) {
                                Element node = (Element) list.get(j);
                                String[] split_tagging = tagging.split(",");
                                int cek = 1;
                                for (String tag : split_tagging) {
                                    result += node.getChildText(tag);
                                    result += (cek == split_tagging.length) ? "" : ",";
                                    cek++;
                                }
                                result += ";";
                            }
                        }
                    } else {
                        for (int i = 0; i < list.size(); i++) {
                            elemen[index] = (Element) list.get(i);
                            listchild = elemen[index].getChildren(head);
                            if (jml_header == 0) {
                                for (int j = 0; j < listchild.size(); j++) {
                                    Element node = (Element) listchild.get(j);
                                    String[] split_tagging = tagging.split(",");
                                    int cek = 1;
                                    for (String tag : split_tagging) {
                                        result += node.getChildText(tag);
                                        result += (cek == split_tagging.length) ? "" : ",";
                                        cek++;
                                    }
                                    result += ";";
                                }
                            }
                        }
                    }
                }
                index++;
            }

        } catch (JDOMException ex) {
            Logger.getLogger(ParsingXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParsingXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String getStringParsingXml(String xml, String root, String tagging) {
        String res = null;
        ParsingXML xp = new ParsingXML();
        res = xp.xmlParsing(xml, root, tagging);
        if (!res.equals("")) {
            return res;
        } else {
            return "datakosong";
        }
    }

    public boolean CreateFileXml(String path, String namafile, String xml, String root, String tagging) {
        String isi = null;
        Boolean result;

        ParsingXML xp = new ParsingXML();
        isi = xp.xmlParsing(xml, root, tagging);
        CreateFile cf = new CreateFile();
        result = cf.newFileParsing(path, namafile, isi);

        return result;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        String xmla = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                    + "<DOCUMENT>"
                    + "<LOADPLP><HEADER><KD_DOK>1</KD_DOK></HEADER><HEADER><KD_DOK>2</KD_DOK></HEADER><DETIL><CONT><NO_CONT>4</NO_CONT></CONT></DETIL></LOADPLP>"
                    + "<LOADPLP><HEADER><KD_DOK>5</KD_DOK></HEADER><DETIL><CONT><NO_CONT>4</NO_CONT></CONT></DETIL></LOADPLP>"
                    + "</DOCUMENT>";
        ParsingXML a = new ParsingXML();
//        String hasil = a.getStringParsingXml(xmla, "LOADPLP>HEADER", "KD_DOK");
        String hasil = a.getStringParsingXml(xmla, "LOADPLP", "");
        String[] hasil1 = hasil.split(";");
        for(int i=0;i<hasil1.length;i++){
            String hasil2 = a.getStringParsingXml(xmla, "LOADPLP>HEADER", "KD_DOK");
            System.out.println(hasil2);
        }
        
    }
}
