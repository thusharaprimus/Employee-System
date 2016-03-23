/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package employeemanagment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Thushara
 */
public class DBoperations {

    String url = "jdbc:mysql://localhost:3306/employee";
    String username = "root";
    String password = "";
    Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs=null;

    boolean addemployee(EmployeeDetails as) {
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            System.out.println("snh");
            String query = "Insert into employeedetails values(?,?,?,?,?,?,?,?)";
            pst = (PreparedStatement) con.prepareStatement(query);
            System.out.println("sbf");

            pst.setInt(1, as.getRegid());
            pst.setString(2, as.getFirstname());
            pst.setString(3, as.getLastname());
            pst.setInt(4, as.getAge());
            pst.setString(5, as.getCountry());
            pst.setString(6, as.getEmail());
            pst.setString(7, as.getUsername());
            pst.setString(8, as.getPassword());

            pst.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;

        } finally {
            try {

                if (pst != null) {
                    pst.close();

                }
                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
            }
        }
    }

    int checkusername(String username) {
        try {
            con = (Connection) DriverManager.getConnection(url, username, password);
            String query="SELECT username FROM employeedetails";
             pst = (PreparedStatement) con.prepareStatement(query);
             rs = pst.executeQuery();
             
             while(rs.next()){
                 if(username.equals(rs.getString(1))){
                     return 0;
                 }
             }
             return 1;
            
            
        } catch (Exception e) {
            System.out.print(e);
            return 2;

        } finally {
            try {

                if (pst != null) {
                    pst.close();

                }
                if (con != null) {
                    con.close();
                }

            } catch (Exception e) {
            }
        }
    }
}
