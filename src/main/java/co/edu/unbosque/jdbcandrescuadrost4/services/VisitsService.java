package co.edu.unbosque.jdbcandrescuadrost4.services;
import co.edu.unbosque.jdbcandrescuadrost4.dtos.Visit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VisitsService {

    private Connection conn;
    private Integer petId;

    public VisitsService(Connection conn, Integer petId) {
        this.conn = conn;
        this.petId = petId;
    }

    public void listVisits(){
        // Objects for handling SQL statement
        Statement stmt = null;

        // Data structure to map results from database
        List<Visit> visits = new ArrayList<Visit>();

        try {
            // Executing a SQL query
            System.out.println("=> Listing visits...");
            stmt = conn.createStatement();
            String sql = "SELECT * FROM visit WHERE pet_id = petId";
            ResultSet rs = stmt.executeQuery(sql);

            // Reading data from result set row by row
            while(rs.next()) {
                // Extracting row values by column name
                Integer visit_id = rs.getInt("visit_id");
                String created_at  = rs.getString("created_at");
                String type = rs.getString("type");
                String description = rs.getString("description");
                Integer vet_id = rs.getInt("vet_id");
                Integer pet_id = rs.getInt("pet_id");

                // Creating a new Visit class instance and adding it to the array list
                visits.add(new Visit(visit_id,created_at,type,description,vet_id,pet_id));
            }
            // Printing results
            System.out.println("visit_id | created_at | type | description | vet_id | pet_id");
            for (Visit visit : visits) {
                System.out.print(visit.getVisit_id()+"|");
                System.out.print(visit.getCreated_at() + " | ");
                System.out.print(visit.getType() + " | ");
                System.out.print(visit.getDescription() + " | ");
                System.out.print(visit.getVet_id() + " | ");
                System.out.println(visit.getPet_id());
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
