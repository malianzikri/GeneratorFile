/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLGenerator;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
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

    List list[] = null;
    List<Element> ele = new ArrayList<>();
    ArrayList al = new ArrayList();

    public ArrayList xmlParsing(String xml, String root, String nodes) {
        String result = "";
        xml = xml.replace("xmlns", "doc");
        xml = xml.replace("xmlns=\"\"", "");
        xml = xml.replaceAll(" /", "/");
        SAXBuilder builder = new SAXBuilder();
        try {
            Document documentparse = builder.build(new StringReader(xml));
            String[] split_root = root.split(">");
            int jml_root = split_root.length;
            list = new List[jml_root];
            ele = new ArrayList();
            al = new ArrayList();
            ele.add(documentparse.getRootElement());
            list[ele.size() - 1] = ele.get(0).getChildren(split_root[0]);
            int index = jml_root;

            for (int x = 1; x <= jml_root; x++) {
                if (index == 1) {
                    for (int j = 0; j < list[ele.size() - 1].size(); j++) {
                        Element node = (Element) list[ele.size() - 1].get(j);
                        String[] split_nodes = nodes.split(",");
                        int z = 0;
                        for (String node1 : split_nodes) {
                            if(node.getChildText(node1)==null){
                            result += "kosong";    
                            }else if(node.getChildText(node1).equalsIgnoreCase("")){
                            result += "kosong";
                            }else{
                            result +=  node.getChildText(node1);    
                            }
                            result += (z == (split_nodes.length - 1)) ? "" : ",";
                            z++;
                        }
                        al.add(result);
                    }
                } else {
                    for (int j = 0; j < list[(x - 1)].size(); j++) {
                        ele.add((Element) list[(x - 1)].get(j));
                        list[ele.size() - 1] = ele.get(ele.size() - 1).getChildren(split_root[x]);
                        index--;
                    }
                }
            }

        } catch (JDOMException ex) {
            Logger.getLogger(ParsingXML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ParsingXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return al;
    }

    public ArrayList getHeader(String fStream, String typedok) {
        ArrayList<String> doks = new ArrayList<String>();
        int i = 0;
        String data = fStream;
        String[] databaru = data.replace("</" + typedok + ">", "</" + typedok + ">" + "#").split("#");
        for (String dok : databaru) {
            i++;
            if (i == databaru.length) {
                dok = "";
            } else if (!dok.contains("<DOCUMENT>")) {

                doks.add("<DOCUMENT>" + dok + "</DOCUMENT>");
            } else {
                doks.add(dok + "</DOCUMENT>");
            }
        }
        return doks;
    }

    public static void main(String[] args) {
//        // TODO code application logic here
        String xmla = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                + "<DOCUMENT>"
                + "<LOADPLP><HEADER><KD_DOK>1</KD_DOK></HEADER><DETIL><CONT><NO_CONT>5</NO_CONT><A></A></CONT></DETIL></LOADPLP>"
//                + "<LOADPLP><HEADER><KD_DOK>9</KD_DOK></HEADER><DETIL><CONT><NO_CONT>3</NO_CONT></CONT></DETIL></LOADPLP>"
//                + "<LOADPLP><HEADER><KD_DOK>5</KD_DOK></HEADER><HEADER><KD_DOK>2</KD_DOK></HEADER><DETIL><CONT><NO_CONT>6</NO_CONT><A>1</A></CONT><CONT><NO_CONT>8</NO_CONT></CONT></DETIL></LOADPLP>"
                + "</DOCUMENT>";
        ParsingXML a = new ParsingXML();
        ArrayList<String> node = new ArrayList();
            node = a.xmlParsing(xmla, "LOADPLP>DETIL>CONT", "NO_CONT,A");
            for (String nodes : node) {
                    String[] split_isi = nodes.split(",");
                    System.out.println("NO_CONT : " + split_isi[0]);
                    System.out.println("A : " + split_isi[1]);
            }
    }
}
