package com.casey;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

/**
 * Created by casey on 4/21/15.
 */
public class CoefficientModel {

    // Variables to create a connection to the DB
    private static String protocol = "jdbc:derby:";
    private static String dbName = "coefficientDB";


    //  Database credentials
    private static final String USER = "temp";
    private static final String PASS = "password";


    com.casey.CoefficientController myController;

    Statement statement = null;

    Connection conn = null;

    ResultSet rs = null;

    LinkedList<Statement> allStatements = new LinkedList<Statement>();

    PreparedStatement psQuery = null;


    public CoefficientModel(com.casey.CoefficientController controller) {
        this.myController = controller;
    }


    //Set up database method
    public boolean setupDatabase() {

        try {
            createConnection();
        } catch (Exception e) {

            System.err.println("Unable to connect to database. Error message and stack trace follow");
            System.err.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

//        try {
//            createTable();
//
//        } catch (SQLException sqle) {
//            System.err.println("Unable to create database. Error message and stack trace follow");
//            System.err.println(sqle.getMessage() + " " + sqle.getErrorCode());
//            sqle.printStackTrace();
//            return false;
//        }
//
////        Add test data
//        try {
//            addTestData();
//        }
//        catch (Exception sqle) {
//
//            System.err.println("Unable to add test data to database. Error message and stack trace follow");
//            System.err.println(sqle.getMessage());
//            sqle.printStackTrace();
//            return false;
//        }

        //clean up and exit.

        cleanup();
        return true;
    }

    private void createTable() throws SQLException {

        String createCoefficientTableSQL = "CREATE TABLE coefficientsDB (material varchar(90), onetwofiveHz double, twofivezeroHz double, fivehundredHz double, onekHz double, twokHz double, fourkHz double)";

        try {
            statement.executeUpdate(createCoefficientTableSQL);
            System.out.println("Created coefficient table");


        } catch (SQLException sqle) {
            //Seems the table already exists, or some other error has occurred.
            //Let's try to check if the DB exists already by checking the error code returned. If so, delete it and re-create it

            throw sqle;
        }
    }

    private void createConnection() throws Exception {

        try {
            conn = DriverManager.getConnection(protocol + dbName + ";create=true", USER, PASS);
            statement = conn.createStatement();
            allStatements.add(statement);
        } catch (Exception e) {
            //There are a lot of things that could go wrong here. Should probably handle them all separately but have not done so here.
            //Should put something more helpful here...
            throw e;
        }

    }

    private void addTestData() throws Exception {
        // Add data.
        if (statement == null) {
            //This isn't going to work
            throw new Exception("Statement not initialized");
        }
        try {

            //floor materials
            String addRecord01 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('carpet', 0.01, 0.02, 0.06, 0.15, 0.25, 0.45)" ;
            statement.executeUpdate(addRecord01);
            String addRecord02 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('concrete (unpainted)', 0.01, 0.02, 0.04, 0.06, 0.08, 0.1)" ;
            statement.executeUpdate(addRecord02);
            String addRecord03 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('concrete (painted/sealed)', 0.01, 0.01, 0.02, 0.02, 0.02, 0.02)" ;
            statement.executeUpdate(addRecord03);
            String addRecord04 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('marble/glazed tile', 0.01, 0.01, 0.01, 0.01, 0.02, 0.02)" ;
            statement.executeUpdate(addRecord04);
            String addRecord05 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('vinyl/linoleum tile', 0.02, 0.03, 0.03, 0.03, 0.03, 0.02)" ;
            statement.executeUpdate(addRecord05);
            String addRecord06 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('wood on concrete', 0.04, 0.04, 0.07, 0.06, 0.06, 0.07)" ;
            statement.executeUpdate(addRecord06);
            String addRecord07 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('wood on joists', 0.15, 0.11, 0.1, 0.07, 0.06, 0.07)" ;
            statement.executeUpdate(addRecord07);

            //wall materials
            //reflective
            String addRecord08 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('brick (natural)', 0.03, 0.03, 0.03, 0.04, 0.05, 0.07)" ;
            statement.executeUpdate(addRecord08);
            String addRecord09 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('brick (painted)', 0.01, 0.01, 0.02, 0.02, 0.02, 0.03)" ;
            statement.executeUpdate(addRecord09);
            String addRecord10 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('concrete block (natural)', 0.36, 0.44, 0.31, 0.29, 0.39, 0.25)" ;
            statement.executeUpdate(addRecord10);
            String addRecord11 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('concrete block (painted)', 0.1, 0.05, 0.06, 0.07, 0.09, 0.08)" ;
            statement.executeUpdate(addRecord11);
            String addRecord12 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('concrete (poured, unpainted)', 0.01, 0.02, 0.04, 0.06, 0.08, 0.1)" ;
            statement.executeUpdate(addRecord12);
            String addRecord13 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('doors (solid wood)', 0.1, 0.07, 0.05, 0.04, 0.04, 0.04)" ;
            statement.executeUpdate(addRecord13);
            String addRecord14 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('glass (1/4in. large pane)', 0.18, 0.06, 0.04, 0.03, 0.02, 0.02)" ;
            statement.executeUpdate(addRecord14);
            String addRecord15 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('glass (small pane)', 0.04, 0.04, 0.03, 0.03, 0.02, 0.02)" ;
            statement.executeUpdate(addRecord15);
            String addRecord16 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('plasterboard', 0.29, 0.1, 0.06, 0.05, 0.04, 0.04)" ;
            statement.executeUpdate(addRecord16);
            String addRecord17 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('plaster on masonry', 0.01, 0.02, 0.02, 0.03, 0.04, 0.05)" ;
            statement.executeUpdate(addRecord17);
            String addRecord18 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('plaster on wood lathe', 0.14, 0.1, 0.06, 0.05, 0.04, 0.04)" ;
            statement.executeUpdate(addRecord18);
            String addRecord19 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('plywood (1/8in.) over 1-1/4 in. airspace', 0.15, 0.25, 0.12, 0.08, 0.08, 0.08)" ;
            statement.executeUpdate(addRecord19);
            String addRecord20 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('plywood (1/8in.) over 2-1/4 in. airspace', 0.28, 0.2, 0.1, 0.1, 0.08, 0.08)" ;
            statement.executeUpdate(addRecord20);
            String addRecord21 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('plywood (3/16in.) over 2in. airspace', 0.38, 0.24, 0.17, 0.1, 0.08, 0.05)" ;
            statement.executeUpdate(addRecord21);
            String addRecord22 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('plywood (3/16in.) over 1in. fiberglass in 2in. airspace', 0.42, 0.36, 0.19, 0.1, 0.08, 0.05)" ;
            statement.executeUpdate(addRecord22);
            String addRecord23 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('plywood (1/4in.) with airspace, light bracing)', 0.03, 0.25, 0.15, 0.1, 0.1, 0.1)" ;
            statement.executeUpdate(addRecord23);
            String addRecord24 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('plywood (3/8in.) with airspace, light bracing)', 0.28, 0.22, 0.17, 0.09, 0.1, 0.11)" ;
            statement.executeUpdate(addRecord24);
            String addRecord25 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('plywood (3/4in.) with airspace, light bracing)', 0.2, 0.18, 0.15, 0.12, 0.1, 0.1)" ;
            statement.executeUpdate(addRecord25);

            //absorptive
            String addRecord26 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('drapery (10 oz/yd2, flat against wall)', 0.4, 0.5, 0.11, 0.18, 0.3, 0.35)" ;
            statement.executeUpdate(addRecord26);
            String addRecord27 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('drapery (14 oz/yd2, flat against wall)', 0.05, 0.07, 0.13, 0.22, 0.32, 0.35)" ;
            statement.executeUpdate(addRecord27);
            String addRecord28 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('drapery (18 oz/yd2, flat against wall)', 0.05, 0.12, 0.35, 0.48, 0.38, 0.36)" ;
            statement.executeUpdate(addRecord28);
            String addRecord29 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('drapery (14 oz/yd2, pleated 50%)', 0.07, 0.31, 0.49, 0.75, 0.7, 0.6)" ;
            statement.executeUpdate(addRecord29);
            String addRecord30 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('drapery (18 oz/yd2, pleated 50%)', 0.14, 0.35, 0.53, 0.75, 0.7, 0.6)" ;
            statement.executeUpdate(addRecord30);
            String addRecord31 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('fiberglass board (1in.thick)', 0.06, 0.2, 0.65, 0.9, 0.95, 0.98)" ;
            statement.executeUpdate(addRecord31);
            String addRecord32 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('fiberglass board (2in. thick)', 0.18, 0.76, 0.99, 0.99, 0.99, 0.99)" ;
            statement.executeUpdate(addRecord32);
            String addRecord33 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('fiberglass board (3in. thick)', 0.53, 0.99, 0.99, 0.99, 0.99, 0.99)" ;
            statement.executeUpdate(addRecord33);
            String addRecord34 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('fiberglass board (4in. thick)', 0.99, 0.99, 0.99, 0.99, 0.99, 0.99)" ;
            statement.executeUpdate(addRecord34);
            String addRecord35 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('open brick pattern over 3 in. fiberglass', 0.4, 0.65, 0.85, 0.75, 0.65, 0.6)" ;
            statement.executeUpdate(addRecord35);
            String addRecord36 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('pageboard over 1in. fiberglass board', 0.08, 0.32, 0.99, 0.76, 0.34, 0.12)" ;
            statement.executeUpdate(addRecord36);
            String addRecord37 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('pageboard over 2in. fiberglass board', 0.26, 0.97, 0.99, 0.66, 0.34, 0.14)" ;
            statement.executeUpdate(addRecord37);
            String addRecord38 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('pageboard over 3in. fiberglass board', 0.49, 0.99, 0.99, 0.69, 0.37, 0.15)" ;
            statement.executeUpdate(addRecord38);
            String addRecord39 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('perforated metal (13% open, over 2in. fiberglass)', 0.25, 0.64, 0.99, 0.97, 0.88, 0.92)" ;
            statement.executeUpdate(addRecord39);

            //ceiling materials
            String addRecord40 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('plasterboard (1/2in.) in suspended ceiling grid)', 0.15, 0.11, 0.04, 0.04, 0.07, 0.08)" ;
            statement.executeUpdate(addRecord40);
            String addRecord41 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('underlay in perforated metal panels (1in. batts)', 0.51, 0.78, 0.57, 0.77, 0.9, 0.79)" ;
            statement.executeUpdate(addRecord41);
            String addRecord42 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('metal deck (perforated channels, 1in. batts)', 0.19, 0.69, 0.99, 0.88, 0.52, 0.27)" ;
            statement.executeUpdate(addRecord42);
            String addRecord43 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('metal deck (perforated channels, 3in. batts)', 0.73, 0.99, 0.99, 0.89, 0.52, 0.31)" ;
            statement.executeUpdate(addRecord43);
            String addRecord44 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('plaster (gypsum, lime, on masonary)', 0.01, 0.02, 0.02, 0.03, 0.04, 0.05)" ;
            statement.executeUpdate(addRecord44);
            String addRecord45 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('plaster (gypsum or lime, rough finish or timber lathe)', 0.14, 0.1, 0.06, 0.05, 0.04, 0.04)" ;
            statement.executeUpdate(addRecord45);
            String addRecord46 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('sprayed cellulose fiber (5/8in.) on solid backing)', 0.05, 0.16, 0.44, 0.79, 0.9, 0.91)" ;
            statement.executeUpdate(addRecord46);
            String addRecord47 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('sprayed cellulose fiber (1in.) on solid backing)', 0.08, 0.29, 0.75, 0.98, 0.93, 0.76)" ;
            statement.executeUpdate(addRecord47);
            String addRecord48 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('sprayed cellulose fiber (1in.) on timber lathe)', 0.47, 0.9, 1.1, 1.03, 1.05, 1.03)" ;
            statement.executeUpdate(addRecord48);
            String addRecord49 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('sprayed cellulose fiber (1-1/4in.) on solid backing)', 0.1, 0.3, 0.73, 0.92, 0.98, 0.98)" ;
            statement.executeUpdate(addRecord49);
            String addRecord50 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('sprayed cellulose fiber (3in.) on solid backing)', 0.7, 0.95, 1, 0.85, 0.85, 0.9)" ;
            statement.executeUpdate(addRecord50);
            String addRecord51 = "INSERT INTO coefficientsDB (material, onetwofiveHz, twofivezeroHz, fivehundredHz, onekHz, twokHz, fourkHz) VALUES ('wood tongue-and-groove roof decking', 0.24, 0.19, 0.14, 0.08, 0.13, 0.1)" ;
            statement.executeUpdate(addRecord51);

        }
        catch (SQLException sqle) {
            System.err.println("Unable to add test data, check validity of SQL statements?");
            System.err.println("Unable to create database. Error message and stack trace follow");
            System.err.println(sqle.getMessage() + " " + sqle.getErrorCode());
            sqle.printStackTrace();

            throw sqle;
        }
    }

    //wall material coefficient LinkedList
    public LinkedList<Double> wallMaterialCoefficients(String wallMaterial) {

        LinkedList<Double> wallMaterialCoefficients = new LinkedList<Double>();
        try{

            String materialLower = wallMaterial.toLowerCase();
            String materialLookup = "SELECT * FROM coefficientsDB WHERE LOWER(material) LIKE ?";
            psQuery = conn.prepareStatement(materialLookup);
            psQuery.setString(1, materialLower);
            rs = psQuery.executeQuery();

        }catch (SQLException e){
            System.out.println("Error in search operation:");
            e.printStackTrace();

        }catch (NullPointerException e) {
            System.out.println("Null pointer exception:");
            e.printStackTrace();
        }
        try{
            while (rs.next()){
                double coefficient125 = rs.getDouble("onetwofiveHz");
                wallMaterialCoefficients.add(coefficient125);
                double coefficient250 = rs.getDouble("twofivezeroHz");
                wallMaterialCoefficients.add(coefficient250);
                double coefficient500 = rs.getDouble("fivehundredHz");
                wallMaterialCoefficients.add(coefficient500);
                double coefficient1k = rs.getDouble("onekHz");
                wallMaterialCoefficients.add(coefficient1k);
                double coefficient2k = rs.getDouble("twokHz");
                wallMaterialCoefficients.add(coefficient2k);
                double coefficient4k = rs.getDouble("fourkHz");
                wallMaterialCoefficients.add(coefficient4k);
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }

        return wallMaterialCoefficients;

    }

    //floor material coefficient LinkedList
    public LinkedList<Double> floorMaterialCoefficients(String floorMaterial) {

        LinkedList<Double> floorMaterialCoefficients = new LinkedList<Double>();
        try{
            String materialLower = floorMaterial.toLowerCase();
            String materialLookup = "SELECT * FROM coefficientsDB WHERE LOWER(material) LIKE ?";
            psQuery = conn.prepareStatement(materialLookup);
            psQuery.setString(1, materialLower);
            rs = psQuery.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error in search operation");

        } catch (NullPointerException e) {
            System.out.println("Null pointer exception:");
            e.printStackTrace();
        }
        try{

            while (rs.next()){
                double coefficient125 = rs.getDouble("onetwofiveHz");
                floorMaterialCoefficients.add(coefficient125);
                double coefficient250 = rs.getDouble("twofivezeroHz");
                floorMaterialCoefficients.add(coefficient250);
                double coefficient500 = rs.getDouble("fivehundredHz");
                floorMaterialCoefficients.add(coefficient500);
                double coefficient1k = rs.getDouble("onekHz");
                floorMaterialCoefficients.add(coefficient1k);
                double coefficient2k = rs.getDouble("twokHz");
                floorMaterialCoefficients.add(coefficient2k);
                double coefficient4k = rs.getDouble("fourkHz");
                floorMaterialCoefficients.add(coefficient4k);
            }

        }catch(SQLException e) {
            e.printStackTrace();
        }

        return floorMaterialCoefficients;
    }

    //ceiling material coefficient LinkedList
    public LinkedList<Double> ceilingMaterialCoefficients(String ceilingMaterial) {

        LinkedList<Double> ceilingMaterialCoefficients = new LinkedList<Double>();
        try {

            String materialLower = ceilingMaterial.toLowerCase();
            String materialLookup = "SELECT * FROM coefficientsDB WHERE LOWER(material) LIKE ?";
            psQuery = conn.prepareStatement(materialLookup);
            psQuery.setString(1, materialLower);
            rs = psQuery.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in search operation");
        }
        try {
            while (rs.next()) {
                double coefficient125 = rs.getDouble("onetwofiveHz");
                ceilingMaterialCoefficients.add(coefficient125);
                double coefficient250 = rs.getDouble("twofivezeroHz");
                ceilingMaterialCoefficients.add(coefficient250);
                double coefficient500 = rs.getDouble("fivehundredHz");
                ceilingMaterialCoefficients.add(coefficient500);
                double coefficient1k = rs.getDouble("onekHz");
                ceilingMaterialCoefficients.add(coefficient1k);
                double coefficient2k = rs.getDouble("twokHz");
                ceilingMaterialCoefficients.add(coefficient2k);
                double coefficient4k = rs.getDouble("fourkHz");
                ceilingMaterialCoefficients.add(coefficient4k);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }catch (NullPointerException e) {
            System.out.println("Null pointer exception:");
            e.printStackTrace();
        }

        return ceilingMaterialCoefficients;
    }

    //door material coefficient LinkedList
    public LinkedList<Double> doorMaterialCoefficients(String doorMaterial) {

        LinkedList<Double> doorMaterialCoefficients = new LinkedList<Double>();
        try {

            String materialLower = doorMaterial.toLowerCase();
            String materialLookup = "SELECT * FROM coefficientsDB WHERE LOWER(material) LIKE ?";
            psQuery = conn.prepareStatement(materialLookup);
            psQuery.setString(1, materialLower);
            rs = psQuery.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error in search operation");
        }
        try {
            while (rs.next()) {
                double coefficient125 = rs.getDouble("onetwofiveHz");
                doorMaterialCoefficients.add(coefficient125);
                double coefficient250 = rs.getDouble("twofivezeroHz");
                doorMaterialCoefficients.add(coefficient250);
                double coefficient500 = rs.getDouble("fivehundredHz");
                doorMaterialCoefficients.add(coefficient500);
                double coefficient1k = rs.getDouble("onekHz");
                doorMaterialCoefficients.add(coefficient1k);
                double coefficient2k = rs.getDouble("twokHz");
                doorMaterialCoefficients.add(coefficient2k);
                double coefficient4k = rs.getDouble("fourkHz");
                doorMaterialCoefficients.add(coefficient4k);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }catch (NullPointerException e) {
            System.out.println("Null pointer exception:");
            e.printStackTrace();
        }

        return doorMaterialCoefficients;
    }

    //cleanup method
    public void cleanup() {
        try {
            if (rs != null) {
                rs.close();  //Close result set
                System.out.println("ResultSet closed");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

        //Close all of the statements. Stored a reference to each statement in allStatements so we can loop over all of them and close them all.
        for (Statement s : allStatements) {

            if (s != null) {
                try {
                    s.close();
                    System.out.println("Statement closed");
                } catch (SQLException se) {
                    System.out.println("Error closing statement");
                    se.printStackTrace();
                }
            }
        }
        try {
            if (conn != null) {
                conn.close();  //Close connection to database
                System.out.println("Database connection closed");
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

}
