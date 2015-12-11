/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.DOCX;

import java.io.FileOutputStream;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTR;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTSectPr;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTText;

/**
 *
 * @author Kamal Bakri
 */
public class WriteDocx {

    /**
     * @param args the command line arguments
     */
    XWPFDocument docx = null;
    CTSectPr sectPr   = null;
    XWPFHeaderFooterPolicy policy = null;

    public WriteDocx(){
        docx = new XWPFDocument();
        sectPr = docx.getDocument().getBody().addNewSectPr();
        policy = new XWPFHeaderFooterPolicy(docx, sectPr);
    }
    
    public void CreateHeader(String header) {
        try {
            CTP ctpHeader = CTP.Factory.newInstance();
            CTR ctrHeader = ctpHeader.addNewR();
            CTText ctHeader = ctrHeader.addNewT();
            ctHeader.setStringValue(header);
            XWPFParagraph headerParagraph = new XWPFParagraph(ctpHeader, docx);
            XWPFParagraph[] parsHeader = new XWPFParagraph[1];
            parsHeader[0] = headerParagraph;
            policy.createHeader(XWPFHeaderFooterPolicy.DEFAULT, parsHeader);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void CreateFooter(String footer) {
        try {

            CTP ctpFooter = CTP.Factory.newInstance();
            CTR ctrFooter = ctpFooter.addNewR();
            CTText ctFooter = ctrFooter.addNewT();
//            String footerText = "This is footer";
            ctFooter.setStringValue(footer);
            XWPFParagraph footerParagraph = new XWPFParagraph(ctpFooter, docx);
            XWPFParagraph[] parsFooter = new XWPFParagraph[1];
            parsFooter[0] = footerParagraph;
            policy.createFooter(XWPFHeaderFooterPolicy.DEFAULT, parsFooter);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void Write(String header,String footer,String kalimat, String alignment,String path) {
        try {

            CreateHeader(header);
            CreateFooter(footer);
            ParagraphAlignment  align = null;
            if(alignment.equalsIgnoreCase("left")){
                align = ParagraphAlignment.LEFT;    
            }else if(alignment.equalsIgnoreCase("right")){
                align = ParagraphAlignment.RIGHT;
            }else if(alignment.equalsIgnoreCase("center")){
                align = ParagraphAlignment.CENTER;
            }
            //write body content
            String[] split_kalimat = kalimat.split("\n");
            for (String text : split_kalimat) {
                XWPFParagraph bodyParagraph = docx.createParagraph();
                bodyParagraph.setAlignment(align);
                XWPFRun r = bodyParagraph.createRun();
                r.setText(text);
            }

            FileOutputStream out = new FileOutputStream(path);
            docx.write(out);
            out.close();
            System.out.println("Done");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        WriteDocx wd = new WriteDocx();
        String kalimat = "Just the thought of another day\n"
                + "How did we end up this way\n"
                + "What did we do wrong?\n"
                + "God\n"
                + "\n"
                + "Even though the days go on\n"
                + "So far, so far away from\n"
                + "It seems so close\n"
                + "\n"
                + "Always weighing on my shoulder\n"
                + "A time like no other\n"
                + "It all changed on that day\n"
                + "Sadness and so much pain\n"
                + "\n"
                + "You can touch the sorrow here\n"
                + "I don’t know what to blame\n"
                + "I just watch and watch again\n"
                + "O...\n"
                + "\n"
                + "Even though the days go on\n"
                + "So far, so far away from\n"
                + "It seems so close\n"
                + "\n"
                + "Even though the days go on\n"
                + "So far, so far away from\n"
                + "It seems so close\n"
                + "\n"
                + "What did it leave behind?\n"
                + "What did it take from us and wash away?\n"
                + "It may be long\n"
                + "But with our hearts start a new\n"
                + "And keep it up and not give up\n"
                + "With our heads held high\n"
                + "\n"
                + "You have seen hell and made it back again\n"
                + "How to forget? We can’t forget\n"
                + "The lives that were lost along the way\n"
                + "And then you realize that wherever you go\n"
                + "There you are\n"
                + "Time won’t stop\n"
                + "So we keep moving on\n"
                + "\n"
                + "Yesterday’s night turns to light\n"
                + "Tomorrow’s night returns to light\n"
                + "O... Be the light\n"
                + "\n"
                + "Always weighing on my shoulder\n"
                + "A time like no other\n"
                + "It all changed on that day\n"
                + "Sadness and so much pain\n"
                + "\n"
                + "Anyone can close their eyes\n"
                + "Pretend that nothing is wrong\n"
                + "Open your eyes\n"
                + "And look for light\n"
                + "O...\n"
                + "\n"
                + "What did it leave behind?\n"
                + "What did it take from us and wash away?\n"
                + "It may be long\n"
                + "But with our hearts start a new\n"
                + "And keep it up and not give up\n"
                + "With our heads held high\n"
                + "\n"
                + "Yeah, yeah...\n"
                + "\n"
                + "You have seen hell and made it back again\n"
                + "How to forget? We can’t forget\n"
                + "The lives that were lost along the way\n"
                + "And then you realize that wherever you go\n"
                + "There you are\n"
                + "Time won’t stop\n"
                + "So we keep moving on\n"
                + "\n"
                + "Yesterday’s night turns to light\n"
                + "Tomorrow’s night returns to light\n"
                + "O... Be the light\n"
                + "\n"
                + "Some days just pass by and\n"
                + "Some days are unforgettable\n"
                + "We can’t choose the reason why\n"
                + "But we can choose what to do from the day after\n"
                + "So with that hope, with that determination\n"
                + "Let’s make tomorrow a brighter and better day\n"
                + "\n"
                + "O...\n"
                + "Yeah...\n"
                + "O...\n"
                + "Yeah... Yeah...\n"
                + "Uh Ooo...";
        wd.Write("BE THE LIGHT","ONE OK ROCK",kalimat,"center","D:\\test.dox");
    }

}
