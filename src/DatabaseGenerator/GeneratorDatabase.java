/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formatjdom;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Malian
 */
public class GeneratorDatabase {

    /**
     * @param args the command line arguments
     */
    private Connection connection;

    public boolean connectDatabase_MYSQL(String host, String dbname, String user, String password) throws ClassNotFoundException {
        String url = "jdbc:mysql://" + host + "/" + dbname;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);
            System.out.println("berhasil");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }

    }

    public boolean connectDatabase_Oracle(String host, String dbname, String user, String password) throws ClassNotFoundException {
        String url = "jdbc:oracle:thin:@" + host + ":" + dbname;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            connection = (Connection) DriverManager.getConnection(url, user, password);
            System.out.println("berhasil");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        }

    }

    public boolean query_insert(String table, String column, String values) {
        PreparedStatement state = null;
        String split_values[] = values.split(",");
        String value = "?";
        for (int i = 0; i < split_values.length - 1; i++) {
            value = value + ",?";
        }
        String query = "insert into " + table + "(" + column + ") values (" + value + ")";
        System.out.println(query);
        try {
            state = (PreparedStatement) connection.prepareStatement(query.trim());
            for (int i = 1; i <= split_values.length; i++) {
                state.setString(i, split_values[i - 1]);
            }
            state.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {

            System.out.println(ex.getLocalizedMessage());
            Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {

                if (state != null) {
                    state.close();
                }

            } catch (Exception e) {
                Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, e);
            }
        }

        return true;
    }

    public String[] query_select_without_where(String table, String column) {

        String hasil[] = null;
        int index = 0;
        ResultSet rs = null;
        ResultSet rb = null;
        Statement state = null;
        String query = "select " + column + " from " + table;
        String split_column[] = column.split(",");
        try {
            state = (Statement) connection.createStatement();
            rb = state.executeQuery("select count(*) from " + table);
            rb.next();
            hasil = new String[rb.getInt(1)];
            rs = state.executeQuery(query);
            while (rs.next()) {
                for (int i = 0; i < split_column.length; i++) {
                    hasil[index] = hasil[index] + " " + split_column[i] + ":" + rs.getString(split_column[i]);
                }
                hasil[index] = hasil[index].replace("null", "").trim();
                index++;
            }
            return hasil;
        } catch (SQLException ex) {

//            Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return hasil = null;
        } finally {
            try {

                if (rs != null) {
                    rs.close();
                }
                if (rb != null) {
                    rb.close();
                }
                if (state != null) {
                    state.close();
                }

            } catch (Exception e) {
                Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }

    public ArrayList query_select_with_where(String table, String column, String column_where, String value_where, String operan) {
        ArrayList a = new ArrayList();
        String hasil = "";
        ResultSet rs = null;
        PreparedStatement state = null;
        String query = "select " + column + " from " + table;
        String query_where = " where";
        String split_column_where[] = column_where.split(",");
        String split_value[] = value_where.split(",");
        String split_operan[] = operan.split(",");
        String split_column[] = column.split(",");
        for (int i = 0; i < split_value.length; i++) {
            query_where = query_where + split_operan[i] + " " + split_column_where[i] + "?";
        }
        query = query + query_where;
        System.out.println(query);
        try {
            state = (PreparedStatement) connection.prepareStatement(query.trim());
            for (int i = 1; i <= split_value.length; i++) {
                state.setString(i, split_value[i - 1]);
            }
            rs = state.executeQuery();
            while (rs.next()) {
                for (int i = 0; i < split_column.length; i++) {
                    hasil = hasil + " " + split_column[i] + ":" + rs.getString(i + 1);

                }
                hasil = hasil.replace("null", "").trim();
                a.add(hasil);
                hasil = "";
            }
            System.out.println(a);
            return a;
//            
        } catch (SQLException ex) {
            Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return a = null;
        } finally {
            try {

                if (rs != null) {
                    rs.close();
                }
                
                if (state != null) {
                    state.close();
                }
               

            } catch (Exception e) {
                Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }

    public boolean query_delete_without_where(String table) {

        boolean hasil = false;
        int index = 0;
        ResultSet rs = null;
        ResultSet rb = null;
        PreparedStatement state = null;
        PreparedStatement states = null;
        String query = "delete  from " + table;

        try {
            state = (PreparedStatement) connection.prepareStatement(query.trim());

            state.executeUpdate();
            return hasil;
        } catch (SQLException ex) {

            Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return hasil = false;
        } finally {
            try {

                if (rs != null) {
                    rs.close();
                }
                if (rb != null) {
                    rb.close();
                }
                if (state != null) {
                    state.close();
                }
                if (states != null) {
                    states.close();
                }

            } catch (Exception e) {
                Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }

    public boolean query_delete_with_where(String table, String column_where, String value_where, String operan) {

        boolean hasil = false;
        int index = 0;
        ResultSet rs = null;
        ResultSet rb = null;
        PreparedStatement state = null;
        PreparedStatement states = null;
        String query = "delete  from " + table;
        String query_where = " where";
        String split_column_where[] = column_where.split(",");
        String split_value[] = value_where.split(",");
        String split_operan[] = operan.split(",");

        for (int i = 0; i < split_value.length; i++) {
            query_where = query_where + split_operan[i] + " " + split_column_where[i] + "?";
        }
        query = query + query_where;
        System.out.println(query);
        try {
            state = (PreparedStatement) connection.prepareStatement(query.trim());
            for (int i = 1; i <= split_value.length; i++) {
                state.setString(i, split_value[i - 1]);
            }
            state.executeUpdate();
            return hasil;
        } catch (SQLException ex) {

            Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return hasil = false;
        } finally {
            try {

                if (rs != null) {
                    rs.close();
                }
                if (rb != null) {
                    rb.close();
                }
                if (state != null) {
                    state.close();
                }
                if (states != null) {
                    states.close();
                }

            } catch (Exception e) {
                Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }

    public boolean query_update_with_where(String table, String column_update, String column_value, String column_where, String value_where, String operan) {

        int index = 1;
        ResultSet rs = null;
        ResultSet rb = null;
        PreparedStatement state = null;
        PreparedStatement states = null;
        String query = "update " + table;
        String split_column[] = column_update.split(",");
        String split_value_column[] = column_value.split(",");
        String query_set = " set ";
        for (int i = 0; i < split_value_column.length; i++) {
            query_set = query_set + split_column[i] + " = " + "?, ";
        }
        query_set = query_set.trim();
        query_set = query_set.replaceAll("\\,$", "");
        String query_where = " where";
        String split_column_where[] = column_where.split(",");
        String split_value[] = value_where.split(",");
        String split_operan[] = operan.split(",");

        for (int i = 0; i < split_value.length; i++) {
            query_where = query_where + split_operan[i] + " " + split_column_where[i] + "?";
        }
        query = query + " " + query_set + query_where;
        System.out.println(query);
        try {
            state = (PreparedStatement) connection.prepareStatement(query.trim());
            for (int i = 0; i < split_value_column.length; i++) {
                state.setString(index, split_value_column[i]);
                index++;
            }
            for (int i = 0; i < split_value.length; i++) {
                state.setString(index, split_value[i]);
                index++;
            }
            state.executeUpdate();
            connection.commit();
            return true;
//            
        } catch (SQLException ex) {

            Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {

                if (rs != null) {
                    rs.close();
                }
                if (rb != null) {
                    rb.close();
                }
                if (state != null) {
                    state.close();
                }
                if (states != null) {
                    states.close();
                }

            } catch (Exception e) {
                Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, e);
            }
        }
//        return hasil;
    }

    public boolean query_update_without_where(String table, String column_update, String column_value) {

        int index = 1;
        ResultSet rs = null;
        ResultSet rb = null;
        PreparedStatement state = null;
        PreparedStatement states = null;
        String query = "update " + table;
        String split_column[] = column_update.split(",");
        String split_value_column[] = column_value.split(",");
        String query_set = " set ";
        for (int i = 0; i < split_value_column.length; i++) {
            query_set = query_set + split_column[i] + " = " + "?, ";
        }
        query_set = query_set.trim();
        query_set = query_set.replaceAll("\\,$", "");

        query = query + " " + query_set;
        System.out.println(query);
        try {
            state = (PreparedStatement) connection.prepareStatement(query.trim());
            for (int i = 0; i < split_value_column.length; i++) {
                state.setString(index, split_value_column[i]);
                index++;
            }

            state.executeUpdate();
            Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, "berhasil");
            connection.commit();
            return true;
//            
        } catch (SQLException ex) {

            Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            try {

                if (rs != null) {
                    rs.close();
                }
                if (rb != null) {
                    rb.close();
                }
                if (state != null) {
                    state.close();
                }
                if (states != null) {
                    states.close();
                }

            } catch (Exception e) {
                Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, e);
            }
        }
//        return hasil;
    }

    public void close_connection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public ArrayList query_select_raw(String table, String column, String sql) {
        String hasil = "";
        ArrayList a = new ArrayList();
        String split_column[] = column.split(",");
        Statement state = null;
        ResultSet rs = null;
        try {
            state = (Statement) connection.createStatement();
            rs = state.executeQuery(sql);
            while (rs.next()) {
                for (int i = 0; i < split_column.length; i++) {
                    hasil = hasil + " " + split_column[i] + ":" + rs.getString(i + 1);
                }
                hasil = hasil.replace("null", "").trim();
                a.add(hasil);
                hasil = "";
            }

            return a;
        } catch (SQLException ex) {
            Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, ex);
            return a = null;
        } finally {
            try {

                if (rs != null) {
                    rs.close();
                }

                if (state != null) {
                    state.close();
                }

            } catch (Exception e) {
                Logger.getLogger(GeneratorDatabase.class.getName()).log(Level.SEVERE, null, e);
            }
        }

    }

//    public static void main(String[] args) throws ClassNotFoundException {
//        // TODO code application logic here
//        GeneratorDatabase g = new GeneratorDatabase();
//        g.connectDatabase_MYSQL("localhost", "generatorcoba", "root", "");
////        g.query_select_raw("mahasiswa", "nama,nim", "select nama,nim from mahasiswa where nim> (select nim from mahasiswa where nama = 'test')");
////        g.query_insert("mahasiswa", "nama,nim", "dody,45");
////        String hasil[] = g.query_select_without_where("mahasiswa", "nim,alamat");
////        if (hasil == null) {
////            System.out.println("error");
////        } else {
////            for (int i = 0; i < hasil.length; i++) {
////                System.out.println(hasil[i]);
////            }
////        }
//
//        g.query_select_with_where("mahasiswa", "nama,nim", "nim >", "(select nim from mahasiswa where nama = 'test')", "");
////        g.query_delete_with_where("mahasiswa","nama =,nim = ","coba,10", ",and");
////        g.query_delete_without_where("mahasiswa");
////        g.query_update_with_where("mahasiswa", "nama,nim", "coba,11", "nama =", "tes", "");
////        g.query_update_without_where("mahasiswa", "nama", "tes");
//    }

}
