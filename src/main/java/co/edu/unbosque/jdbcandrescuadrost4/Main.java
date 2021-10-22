package co.edu.unbosque.jdbcandrescuadrost4;

import co.edu.unbosque.jdbcandrescuadrost4.dtos.Owner;
import co.edu.unbosque.jdbcandrescuadrost4.services.PetCasesService;
import co.edu.unbosque.jdbcandrescuadrost4.services.UsersService;
import co.edu.unbosque.jdbcandrescuadrost4.services.VetsService;
import co.edu.unbosque.jdbcandrescuadrost4.services.VisitsService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static final String JDBC_DRIVER = "org.postgressql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/FourPaws4";

    static final String USER = "postgres";
    static final String PASS = "";
    private static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        Connection conn = null;

        try {

            // Registering the JDBC driver
            Class.forName(JDBC_DRIVER);

            // Opening database connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Welcome \t Enter the roll you want to Search: ");
            String O = sc.nextLine();
            System.out.println("list of registered users for the given role");
            UsersService usersService = new UsersService(conn, O);
            usersService.listUsers();
            System.out.println("list of veterinarians registered on the platform");
            VetsService vetsService = new VetsService(conn);
            vetsService.listVets();
            System.out.println("Enter the ID of the pet you want to see visits");
            Integer VI = sc.nextInt();
            System.out.println("visits that have been logged for the given pet ID");
            VisitsService visitsService = new VisitsService(conn, VI);
            visitsService.listVisits();
            System.out.println("Enter the ID of the stolen pet");
            Integer PI = sc.nextInt();
            PetCasesService petCasesService = new PetCasesService(conn, PI);
            petCasesService.addPetCase();


            // Closing database connection
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handling errors from JDBC driver
        } finally {
            // Cleaning-up environment
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
