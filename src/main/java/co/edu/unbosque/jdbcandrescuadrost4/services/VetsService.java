package co.edu.unbosque.jdbcandrescuadrost4.services;

import co.edu.unbosque.jdbcandrescuadrost4.dtos.Vet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VetsService {

    // Objects for handling connection
    private Connection conn;

    public VetsService(Connection conn){this.conn = conn;}

    public void listVets(){

        // Objects for handling SQL statement
        Statement stmt = null;

        // Data structure to map results from database
        List<Vet> vets = new ArrayList<Vet>();

        try {

            // Executing a SQL query
            System.out.println("=> Listing vets...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM Vet";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while(rs.next()) {
                // Extracting row values by column name
                String username = rs.getString("username");
                String name  = rs.getString("name");
                String address = rs.getString("address");
                String neighborhood = rs.getString("neighborhood");

                // Creating a new Vet class instance and adding it to the array list
                vets.add(new Vet(username,name, address, neighborhood));
            }

            // Printing results
            System.out.println("UserName | Email | Password | Role");
            for (Vet vet : vets) {
                System.out.print(vet.getUsername()+"|");
                System.out.print(vet.getName() + " | ");
                System.out.print(vet.getAddress() + " | ");
                System.out.println(vet.getNeighborhood());
            }

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
