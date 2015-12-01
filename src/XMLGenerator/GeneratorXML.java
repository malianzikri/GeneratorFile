/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLGenerator;

import java.io.FileWriter;
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

    Element header[] = null;
    Element child[] = null;
    Element root ;
    Document doc ;
    Element doclist ;
    public GeneratorXML(String Root,String nama_dokument,String xmlns )
    {
         root = new Element(Root, xmlns);
         doc= new Document(root);
         doclist= new Element(nama_dokument);
    }
    public void set_tag_header(String tag_header) {
        String[] split_tag_header = tag_header.split(",");
        header = new Element[split_tag_header.length];
        for (int i = 0; i < split_tag_header.length; i++) {
            header[i] = new Element(split_tag_header[i]);
        }
    }

    public void isi_tag_header(String tagging, int index_header) {
        String tagging_split[] = tagging.split(",");
        for (int i = 0; i < tagging_split.length; i++) {
            String split_element[] = tagging_split[i].split(":");
            header[index_header].addContent(new Element(split_element[0].trim()).setText(split_element[1].trim()));
        }
//        doclist.addContent(header[index_header]);
    }

    public void set_attribute_header(String nama_attrib, String isi_attrib, int index_header) {
        header[index_header].setAttribute(new Attribute(nama_attrib, isi_attrib));
    }

    public void set_attribute_child(String nama_attrib, String isi_attrib, int index_child) {
        child[index_child].setAttribute(new Attribute(nama_attrib, isi_attrib));
    }

    public void set_tag_child(String tag_child) {
        String[] split_tag_header = tag_child.split(",");
        child = new Element[split_tag_header.length];
        for (int i = 0; i < split_tag_header.length; i++) {
            child[i] = new Element(split_tag_header[i]);
        }

    }

    public void isi_tag_child(String tagging_cont, int index_child) {
        String tagging_spliter[] = tagging_cont.split(",");

        for (int i = 0; i < tagging_spliter.length; i++) {
            String split_element[] = tagging_spliter[i].split(":");
            child[index_child].addContent(new Element(split_element[0].trim()).setText(split_element[1]));

        }
//       
//        doclist.addContent(header[index_header]);
    }

    public void add_content(String kode, int index_header, int index_child) {
        if (kode.equals("1")) {
            doclist.addContent(header[index_header]);
        } else if (kode.equals("2")) {
            header[index_header].addContent(child[index_child]);
        } else if (kode.equals("3")) {
            child[index_header].addContent(child[index_child]);
        }
    }

    public String getStringxml() {
        doc.getRootElement().addContent(doclist);
        String xml = new XMLOutputter().outputString(doc);
        return xml;
    }

    public Boolean saveXml(String path, String nama_file) {
        try {
            doc.getRootElement().addContent(doclist);
            XMLOutputter xmlOutput = new XMLOutputter();
            xmlOutput.setFormat(Format.getPrettyFormat());
            xmlOutput.output(doc, new FileWriter(path + nama_file+".xml"));
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    

}
