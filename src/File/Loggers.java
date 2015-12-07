/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import java.util.logging.Logger;
import java.util.logging.Level;

/**
 *
 * @author Kamal Bakri
 */
public class Loggers {

    
    private static Logger LOGGER = Logger.getLogger("");
    
    public void LogSuccess(String message) {
        LOGGER.info(message);
    }

    public void LogError(String error_message) {
        int line_error = Thread.currentThread().getStackTrace()[2].getLineNumber();
        LOGGER.warning(error_message+"; in Line :" + line_error);
    }

    public static void main(String[] args) {
        int a;
        Loggers log = new Loggers();
        log.LogError("Gagal menambahkan data");
        log.LogSuccess("Berhasil menambahkan data");

    }

}
