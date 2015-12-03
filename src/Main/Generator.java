/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import XMLGenerator.GeneratorXML;
import XMLGenerator.XMLParsing;

/**
 *
 * @author Malian Class ini digunakan untuk memanggil class-class lain berupa
 * generator file
 */
public class Generator {

    public String generatorXML(String tagging, String isi) {
        String xml = null;
        System.out.println("XMl" + xml);
        /*
         proses memanggil class lain yang berupa class generator xml
         */
        return xml;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        GeneratorXML fj = new GeneratorXML("document", "COCOCONT", "cococont.xsd");
        String tag_header = "Header,Detil";
        fj.set_tag_header(tag_header);
        fj.set_attribute_header("description", "testing", 0);
        String tagging = "KD_DOK:3, KD_TPS:KOJA , NO_VOY_FLIGHT:0045 , CALL_SIGN:A8LE3"
                + " , KD_GUDANG:KOJA , ETA:20151121080000 , ETD:201511221400 , VESSEL_CODE:HALINA , VESSEL_NAME:HAMMONIA BEROLINA ";
        fj.isi_tag_header(tagging, 0);

        fj.add_content("1", 0, 0);
        fj.add_content("1", 1, 0);
        String xml = fj.getStringxml();
        System.out.println("------------------XML FILE-------------------");
        System.out.println(xml);
        
        
        XMLParsing xp = new XMLParsing();
        System.out.println("-----------------PARSING XML-----------------");
        String out = xp.getStringParsingXml(xml, "COCOCONT>Header", "KD_DOK,KD_TPS,NO_VOY_FLIGHT,CALL_SIGN,KD_GUDANG");
        System.out.println(out);
        //xmlParsing(1,2,3)
        //1. String xml yang akan di parsing
        //2. root dan header data yang akan di parsing pisahkan dengan tanda ">"
        //3. Nama tagging yang akan di parsing (yang memiliki data)
        //maksimal 4 taging COCOCONT>Header>Detil>Cont untuk penambahan jumlah di lakukan dengan menambahkan case 4 dst.
        
        Boolean x = xp.CreateFileXml("D:\\", "file", xml, "COCOCONT>Header", "KD_DOK,KD_TPS,NO_VOY_FLIGHT,CALL_SIGN,KD_GUDANG");
        System.out.println(x);
    }
}
