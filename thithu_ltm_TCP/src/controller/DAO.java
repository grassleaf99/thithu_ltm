/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.SV;

/**
 *
 * @author ADMIN
 */
public class DAO {
    private Connection connection;

    public DAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thithu_ltm", "root", "root4cnpm");
            } catch (SQLException ex) {
                Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<SV> getDSSV(String ten)
    {
        ArrayList<SV> result = new ArrayList<SV>();
        try {
            String query = "select * from thithu_ltm.sv where name like '%" + ten + "%'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next())
            {
                SV sv = new SV(resultSet.getInt(1), resultSet.getString(2), resultSet.getDate(3), resultSet.getString(4), resultSet.getString(5));
                result.add(sv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
          return result;
    }
    
    public void updateSV(SV sv)
    {
        try {
            String query = "update thithu_ltm.sv set name = ?, dob = ?, khoa = ?, que = ? where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, sv.getTen());
            preparedStatement.setDate(2, sv.getDob());
            preparedStatement.setString(3, sv.getKhoa());
            preparedStatement.setString(4, sv.getQue());
            preparedStatement.setInt(5, sv.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
