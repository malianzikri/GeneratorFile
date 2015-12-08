/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Loggers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Kamal Bakri
 */
public class Loggers {

    public String getTanggal(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
    
    public String LogSuccess(String message) {
        int line_error = Thread.currentThread().getStackTrace()[2].getLineNumber();
        String name_method = Thread.currentThread().getStackTrace()[2].getMethodName();
        String result = getTanggal()+", SUCCESS : " + message + "; Method : " + name_method + "; line : " + line_error;
        return result;
    }

    public String LogError(String error_message) {
        int line_error = Thread.currentThread().getStackTrace()[2].getLineNumber();
        String name_method = Thread.currentThread().getStackTrace()[2].getMethodName();
        String result = getTanggal() +", ERROR : " + error_message + "; Method : " + name_method + "; line : " + line_error;
        return result;
    }

    public static void main(String[] args) {
        Loggers log = new Loggers();
        String a = log.LogError("Gagal menambahkan data");
        System.out.println(a);
        String b = log.LogSuccess("Berhasil menambahkan data");
        System.out.println(b);
    }

}
