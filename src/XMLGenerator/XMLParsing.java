/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLGenerator;

import File_TXT.CreateFile;
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
public class XMLParsing {

    Element elemen[] = null;

    public String xmlParsing(String xml, String root, String tagging) {
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
//            String xmla = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><document><COCOCONT><Head><Header><KD_DOK>3</KD_DOK><KD_TPS>KOJA</KD_TPS><NO_VOY_FLIGHT>0045</NO_VOY_FLIGHT><CALL_SIGN>A8LE3</CALL_SIGN><KD_GUDANG>KOJA</KD_GUDANG><ETA>20151121080000</ETA><ETD>201511221400</ETD><VESSEL_CODE>HALINA</VESSEL_CODE><VESSEL_NAME>HAMMONIA BEROLINA</VESSEL_NAME></Header>"
//                    + "<Header><KD_DOK>4</KD_DOK><KD_TPS>KOJA</KD_TPS><NO_VOY_FLIGHT>0045</NO_VOY_FLIGHT><CALL_SIGN>A8LE3</CALL_SIGN><KD_GUDANG>KOJA</KD_GUDANG><ETA>20151121080000</ETA><ETD>201511221400</ETD><VESSEL_CODE>HALINA</VESSEL_CODE><VESSEL_NAME>HAMMONIA BEROLINA</VESSEL_NAME></Header></Head><Detil /></COCOCONT></document>";
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
                                result += tag + ":" + node.getChildText(tag);
                                result += (cek == split_tagging.length) ? "" : ",";
                                cek++;
                            }
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
                                    result += tag + ":" + node.getChildText(tag);
                                    result += (cek == split_tagging.length) ? "" : ",";
                                    cek++;
                                }
                            }
                        }
                    }
                }
                index++;
            }

        } catch (JDOMException ex) {
            Logger.getLogger(XMLParsing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLParsing.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public String getStringParsingXml(String xml, String root, String tagging) {
        String res = null;
        XMLParsing xp = new XMLParsing();
        res = xp.xmlParsing(xml, root, tagging);
        if (!res.equals("")) {
            return res;
        } else {
            return "FORMAT SALAH";
        }
    }

    public boolean CreateFileXml(String path, String namafile, String xml, String root, String tagging) {
        String isi = null;
        Boolean result;
        
        XMLParsing xp = new XMLParsing();
        isi = xp.xmlParsing(xml, root, tagging);
        CreateFile cf = new CreateFile();
        result = cf.newFileParsing(path, namafile, isi);
        
        return result;
    }

}
