/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLGenerator;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Malian class yang berfungsi memanggil metode atau function class ini
 * akan memanggil metode atau function yang dibutuhkan untuk menggenerate xml
 */
public class GeneratorXML {

    public String generatexml(String tagging, String isi) {
        String xml = "";
        xml = xml(isi, tagging);
        //proses membuat generator xml dengan memanggil metode lain
        return xml;
    }

    public String[] pisah_seperator_isi(String isi) {
        //proses mengubah isi dari string menjadi isi yang dinormalisasi berdsarkan separtor serta memanggil metode tagging
        String[] isi_normalisasi = null;
        isi_normalisasi = isi.split(",");
        return isi_normalisasi;
    }

    public String[] pisah_separator_tagging(String tangging) {
            //proses mengubah tanggi menjadi tangging xml yang akan dipakai untuk diisi dari isi separator
        //proses disini juga pembuatan node dari tanggi yang disediakan
        String[] tagging_normalisasi = null;
        tagging_normalisasi = tangging.split(",");
        return tagging_normalisasi;
    }

    public String xml(String isi, String tangging) {
        String xml = null;
            // proses disini untuk mengisi string isi dan tanggi menggunakan library jdom2 
        String[] isi_normalisasi =  pisah_seperator_isi(isi);
        String[] tangging_normalisasi = pisah_separator_tagging(tangging);
        try {
            Element tes = new Element("Data");
            Document doc = new Document(tes);

            if (tangging_normalisasi.length == isi_normalisasi.length) {
                int a = 0;
                for (String elemen : tangging_normalisasi) {
                    doc.getRootElement().addContent(new Element(elemen.toString()).setText(isi_normalisasi[a]));
                    a++;
                }

                xml = new XMLOutputter().outputString(doc);
            } else {
                xml = "Isi melebihi jumlah elemen !";
            }
        } catch (Exception e) {
            xml = e.getMessage();
        }
        return xml;
    }
}
