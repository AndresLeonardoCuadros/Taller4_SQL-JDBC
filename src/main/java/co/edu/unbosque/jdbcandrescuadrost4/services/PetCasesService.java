package co.edu.unbosque.jdbcandrescuadrost4.services;

import co.edu.unbosque.jdbcandrescuadrost4.dtos.PetCase;
import co.edu.unbosque.jdbcandrescuadrost4.dtos.UserApp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetCasesService {

    private Connection conn;
    private Integer id;
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm");
    private String date = dtf.format(LocalDateTime.now());
    private PetCase petCase;

    public PetCasesService(Connection conn, Integer id) {
        this.conn = conn;
        this.id = id;
    }

    public void addPetCase(){
        // Objects for handling SQL statement
        Statement stmt = null;


        try {
            // Executing a SQL query
            System.out.println("=> Registering new PetCase...");
            stmt = conn.createStatement();
            String sql = "INSERT INTO PetCase(created_at,type,description,pet_id) VALUES (date,'robo','the pet was stolen',id)";
            ResultSet rs = stmt.executeQuery(sql);

            // Printing results
            System.out.println("The Pet Case was registered");

            // Closing resources
            rs.close();
            stmt.close();

        }catch (SQLException se){
            se.printStackTrace(); // Handling errors from database
        }finally {
            // Cleaning-up environment
            try {
                if(stmt != null) stmt.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
