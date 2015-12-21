/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File.TXT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kamal Bakri
 */
public class CreateFile {

    public boolean newFileParsing(String path, String namafile, String isi) {
        PrintWriter writer;
        Boolean result;
        try {
            if (isi.equalsIgnoreCase("")) {
                result = false;
            } else {
                writer = new PrintWriter(path + namafile + ".txt", "UTF-8");
                String[] split_isi = isi.split(",");
                for (String data : split_isi) {
                    writer.println(data);
                }
                writer.close();
                result = true;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateFile.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CreateFile.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }
        return result;
    }

    public boolean newFile(String path, String namafile, String isi) {
        PrintWriter writer;
        Boolean result;
        try {
            if (isi.equalsIgnoreCase("")) {
                result = false;
            } else {
                writer = new PrintWriter(path + namafile , "UTF-8");
                writer.println(isi);
                writer.close();
                result = true;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CreateFile.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(CreateFile.class.getName()).log(Level.SEVERE, null, ex);
            result = false;
        }
        return result;
    }

    public boolean execute(String filename) throws Exception
    {
        boolean success = false;
        File f = null;
        try
        {
            f = new File(filename);
            try{
                f.delete();
                success = true;
            }catch(Exception e){
            
            }
            f.createNewFile();
        }catch(Exception e){
            success = false;
        }
        finally
        {
            f = null;
            return success;
        }
    }
    public boolean content(String filename,String content) throws Exception
    {
        boolean success = false;
        File f = null;
        RandomAccessFile rf = null;
        try
        {
            f = new File(filename);
            rf = new RandomAccessFile(f, "rwd");
            rf.writeBytes(content);
            rf.close();
            success = true;
        }catch(Exception e){success = false;}
        finally
        {
            f = null;
            rf = null;
            return success;
        }
    }
    public synchronized boolean content(String filename,byte[] content) throws Exception
    {
        boolean success = false;
        File f = null;
        RandomAccessFile rf = null;
        try
        {
            f = new File(filename);
            rf = new RandomAccessFile(f, "rwd");
            rf.write(content);
            rf.close();
            success = true;
        }catch(Exception e){success = false;}
        finally
        {
            f = null;
            rf = null;
            return success;
        }
    }
    public boolean content(String filename,byte[] content,int start,int end) throws Exception
    {
        boolean success = false;
        File f = null;
        RandomAccessFile rf = null;
        try
        {
            f = new File(filename);
            rf = new RandomAccessFile(f, "rwd");
            rf.write(content,start,end);
            rf.close();
            success = true;
        }catch(Exception e){success = false;}
        finally
        {
            f = null;
            rf = null;
            return success;
        }
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        CreateFile cf = new CreateFile();
        cf.newFile("D:\\", "text.txt", "ini masuk\r\nini baris kedua");
    }
}
