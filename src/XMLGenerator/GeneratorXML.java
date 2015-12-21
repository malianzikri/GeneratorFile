/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLGenerator;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Malian class yang berfungsi memanggil metode atau function class ini
 * akan memanggil metode atau function yang dibutuhkan untuk menggenerate xml
 * kode 0: untuk memasukan data langsung ke header kode 1: untuk memasukan data
 * langsung ke child yang berada dibawah header kode 2: untuk memasukan data
 * langsung ke child yang berada didalam child
 */
public class GeneratorXML {

    private Element child[] = null;
    private Element root;
    private Document doc;
    private Element doclist;
    private List<Element> head = null;
    private String[] split_tag_header = null;

    public GeneratorXML(String Root, String xmlns) {
        root = new Element(Root, xmlns);
        doc = new Document(root);
        head = new ArrayList<Element>();
    }

    public boolean addXML(String tag_header, String tag_child, String isi) {
        boolean result = false;
        int index = 0;
        try {
            if (tag_child.equals("")) {
                result = false;
            } else {
                if (tag_header.equalsIgnoreCase(root.getName())) {
                    head.add(new Element(tag_child));
                    int x = head.size() - 1;
                    if (!isi.equals("")) {
                        String isi_split[] = isi.split(",");
                        for (int i = 0; i < isi_split.length; i++) {
                            String split_element[] = isi_split[i].split(":");
                            if (!isi_split[i].startsWith(":")) {
                                if (split_element.length == 1) {
                                    head.get(x).addContent(new Element(split_element[0].trim()).setText(""));
                                } else {
                                    head.get(x).addContent(new Element(split_element[0].trim()).setText(split_element[1].trim()));
                                }
                            }
                        }
                    }
                    doc.getRootElement().addContent(head.get(x));
                } else {
                    for (int i = 0; i < head.size(); i++) {
                        if (tag_header.equalsIgnoreCase(head.get(i).getName())) {
                            index = i;
                        } else {
                            result = false;
                        }
                    }
                    head.add(new Element(tag_child));
                    int x = head.size() - 1;
                    if (!isi.equals("")) {
                        String isi_split[] = isi.split(",");
                        for (int i = 0; i < isi_split.length; i++) {

                            String split_element[] = isi_split[i].split(":");
                            if (!isi_split[i].startsWith(":")) {
                                if (split_element.length == 1) {
                                    head.get(x).addContent(new Element(split_element[0].trim()).setText(""));
                                } else {
                                    head.get(x).addContent(new Element(split_element[0].trim()).setText(split_element[1].trim()));
                                }
                            }
                        }
                    }
                    head.get(index).addContent(head.get(x));
                }
                result = true;
            }

        } catch (Exception e) {
            result = false;
        }
        return result;
    }

    public String getStringxml() {
        String xml = new XMLOutputter().outputString(doc);
        return xml;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        GeneratorXML a = new GeneratorXML("DOKUMEN", "");
        a.addXML("DOKUMEN", "COCOCONT", "");
        a.addXML("COCOCONT", "HEADER", "");
        System.out.println(a.addXML("HEADER", "DETIL", "NAMA:a"));

        System.out.println(a.getStringxml());
    }

    

}
