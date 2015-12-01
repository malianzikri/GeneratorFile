/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import XMLGenerator.GeneratorXML;

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
          GeneratorXML fj = new GeneratorXML ("document","COCOCONT","cococont.xsd");
         String tag_header = "Header,Detil";
        fj.set_tag_header(tag_header);
        fj.set_attribute_header("description", "testing", 0);
        String tagging = "KD_DOK:3, KD_TPS:KOJA , NO_VOY_FLIGHT:0045 , CALL_SIGN:A8LE3"
                + " , KD_GUDANG:KOJA , ETA:20151121080000 , ETD:201511221400 , VESSEL_CODE:HALINA , VESSEL_NAME:HAMMONIA BEROLINA ";
        fj.isi_tag_header(tagging, 0);
        
         fj.add_content("1", 0, 0);
        fj.add_content("1", 1, 0);
          System.out.println(fj.getStringxml());
    }
}
