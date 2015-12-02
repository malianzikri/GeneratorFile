/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLGenerator;

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

    public void xmlParsing(String xml, String root, String tagging) {
        String fStream = null;
        String[] header;
        List listchild = null;
        Element elemen[] = null;
        List list = null;
        fStream = xml;
        fStream = fStream.replace("xmlns=\"cococont.xsd\"", "");
        fStream = fStream.replace("xmlns=\"\"", "");
        fStream = fStream.replaceAll(" /", "/");

        SAXBuilder builder = new SAXBuilder();

        try {
            //String xmla = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><document><COCOCONT><Header /><Header><KD_DOK>3</KD_DOK><KD_TPS>KOJA</KD_TPS><NO_VOY_FLIGHT>0045</NO_VOY_FLIGHT><CALL_SIGN>A8LE3</CALL_SIGN><KD_GUDANG>KOJA</KD_GUDANG><ETA>20151121080000</ETA><ETD>201511221400</ETD><VESSEL_CODE>HALINA</VESSEL_CODE><VESSEL_NAME>HAMMONIA BEROLINA</VESSEL_NAME></Header><Detil /></COCOCONT></document>";
            Document documentparse = builder.build(new StringReader(fStream));
            int index = 0;
            header = root.split(">");
            int jml_header = header.length;
            for (String head : header) {
                switch (index) {
                    case 0:
                        Element rootNode = documentparse.getRootElement();
                        list = rootNode.getChildren(head);
                        jml_header--;
                        if (jml_header == 0) {
                            for (int j = 0; j < list.size(); j++) {
                                Element node = (Element) list.get(j);
                                String[] split_tagging = tagging.split(",");
                                for (String tag : split_tagging) {
                                    System.out.println(tag + " : " + node.getChildText(tag));
                                }
                            }
                        }
                        break;
                    case 1:
                        jml_header--;
                        if (list.size() == 1) {
                            Element childNode1 = (Element) list.get(0);
                            list = childNode1.getChildren(head);
                            if (jml_header == 0) {
                                for (int j = 0; j < list.size(); j++) {
                                    Element node = (Element) list.get(j);
                                    String[] split_tagging = tagging.split(",");
                                    for (String tag : split_tagging) {
                                        System.out.println(tag + " : " + node.getChildText(tag));
                                    }
                                }
                            }
                        } 
                        break;
                    case 2:
                        jml_header--;
                        Element childNode2 = (Element) list.get(0);
                        list = childNode2.getChildren(head);

                        if (jml_header == 0) {
                            for (int j = 0; j < list.size(); j++) {
                                Element node = (Element) list.get(j);
                                String[] split_tagging = tagging.split(",");
                                for (String tag : split_tagging) {
                                    System.out.println(tag + " : " + node.getChildText(tag));
                                }
                            }
                        }
                        break;
                    case 3:
                        jml_header--;
                        Element childNode3 = (Element) list.get(0);
                        list = childNode3.getChildren(head);

                        if (jml_header == 0) {
                            for (int j = 0; j < list.size(); j++) {
                                Element node = (Element) list.get(j);
                                String[] split_tagging = tagging.split(",");
                                for (String tag : split_tagging) {
                                    System.out.println(tag + " : " + node.getChildText(tag));
                                }
                            }
                        }
                        break;
                    default:
                        break;
                }
                index++;
            }

        } catch (JDOMException ex) {
            Logger.getLogger(XMLParsing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLParsing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Parsing(String stringxml, String root, String header, String tagging) {
        String fStream;
        SAXBuilder builder = new SAXBuilder();
        fStream = stringxml;
        fStream = fStream.replace("xmlns=\"cococont.xsd\"", "");
        fStream = fStream.replace("xmlns=\"\"", "");
        fStream = fStream.replaceAll(" /", "/");
        try {
            String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><document><COCOCONT><Header><KD_DOK>3</KD_DOK><KD_TPS>KOJA</KD_TPS><NO_VOY_FLIGHT>0045</NO_VOY_FLIGHT><CALL_SIGN>A8LE3</CALL_SIGN><KD_GUDANG>KOJA</KD_GUDANG><ETA>20151121080000</ETA><ETD>201511221400</ETD><VESSEL_CODE>HALINA</VESSEL_CODE><VESSEL_NAME>HAMMONIA BEROLINA</VESSEL_NAME></Header><Detil /></COCOCONT></document>";
            Document documentparse = builder.build(new StringReader(fStream));
            Element rootNode = documentparse.getRootElement();
            List list = rootNode.getChildren(root);

            for (int i = 0; i < list.size(); i++) {
                Element node = (Element) list.get(i);
                List node_list = node.getChildren(header);
                for (int j = 0; j < node_list.size(); j++) {
                    Element child = (Element) node_list.get(j);
                    String[] split_tagging = tagging.split(",");
                    for (String tag : split_tagging) {
                        System.out.println(tag + " : " + child.getChildText(tag));
                    }
                }
            }
        } catch (IOException io) {
            System.out.println(io.getMessage());
        } catch (JDOMException jdomex) {
            System.out.println(jdomex.getMessage());
        }
    }

}
