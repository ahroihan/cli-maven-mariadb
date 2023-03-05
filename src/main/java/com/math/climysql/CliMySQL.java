/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.math.climysql;
import java.sql.*;
import java.util.Scanner;
/**
 *
 * @author Ahroi
 */
public class CliMySQL {

    public static void main( String[] args ) throws SQLException {
        //create connection for a server installed in localhost, with a user "root" with no password
        try (Connection conn = DriverManager.getConnection("jdbc:mariadb://localhost/javalearning", "root", null)) {
            System.out.println("Connected With the database successfully"); //Message after successful connection
            Statement stmt = conn.createStatement();
            System.out.println("SIMPLE PROGRAM JAVA CONNECT AND CRUD MARIA DB");
            System.out.println("1. CREATE TABLE NAME");
            System.out.println("2. INSERT DATA TABLE NAME");
            System.out.println("3. SELECT DATA TABLE NAME");
            System.out.println("4. UPDATE DATA TABLE NAME");
            System.out.println("5. DELETE DATA TABLE NAME");
            System.out.println("6. DROP TABLE NAME");
            System.out.println("7. SHOW ALL TABLES IN DATABASE");
            System.out.println("8. EXIT");
            System.out.println("----------------------------");
            System.out.print("Choose your option (enter): ");
            Scanner input = new Scanner(System.in);
            loop: while(input.hasNextInt()){
                String res = "";
                String table_name = "my_table_name";
                int optS = input.nextInt();
                switch(optS){
                    case 0:{
                        //execute query
                        ResultSet rs = stmt.executeQuery("SELECT 'Hello World!'");
                        //position result to first
                        rs.first();
                        res = rs.getString(1); //result is "Hello World!"
                        break;
                    }
                    case 1:{
                        ResultSet rs = stmt.executeQuery("CREATE TABLE " + table_name + " (`id` int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,`number` text NOT NULL,`name` text NOT NULL);");
                        rs.first();
                        res = "\ntable_name created Successfully";
                        break;
                    }
                    case 2:{
                        PreparedStatement preparedStatement =conn.prepareStatement("INSERT INTO " + table_name + " VALUES(null,?,?)");
                        System.out.print("Number (enter): ");
                        String numb = input.next();
                        System.out.print("Name (enter to insert): ");
                        String nam = input.next();
                        preparedStatement.setString(1,numb);
                        preparedStatement.setString(2,nam);
                        preparedStatement.executeQuery();
                        res = "\nData inserted Successfully";
                        break;
                    }
                    case 3:{
                        PreparedStatement preparedStatement =conn.prepareStatement("SELECT * FROM " + table_name);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        System.out.println("ID \t NUMBER \t NAME");
                        while(resultSet.next()){
                            String idTable=resultSet.getString("id");
                            String numTable=resultSet.getString("number");
                            String nameTable=resultSet.getString("name");
                            System.out.println(idTable + "\t" + numTable + "\t" + nameTable);
                        }
                        res = "\nData read Successfully";
                        break;
                    }
                    case 4:{
                        PreparedStatement preparedStatement =conn.prepareStatement("UPDATE " + table_name + " SET number=?, name=? WHERE id=?");
                        System.out.print("ID (enter): ");
                        int idX = input.nextInt();
                        System.out.print("Number (enter): ");
                        String numb = input.next();
                        System.out.print("Name (enter to update): ");
                        String nam = input.next();
                        preparedStatement.setString(1,numb);
                        preparedStatement.setString(2,nam);
                        preparedStatement.setInt(3,idX);
                        preparedStatement.executeUpdate();
                        res = "\nData updated Successfully";
                        break;
                    }
                    case 5:{
                        PreparedStatement preparedStatement =conn.prepareStatement("DELETE FROM " + table_name + " WHERE id=?");
                        System.out.print("ID (enter to delete): ");
                        int idX = input.nextInt();
                        preparedStatement.setInt(1,idX);
                        preparedStatement.execute();
                        res = "\nData deleted Successfully";
                        break;
                    }
                    case 6:{
                        ResultSet rs = stmt.executeQuery("DROP TABLE " + table_name);
                        rs.first();
                        res = "\ntable_name dropped Successfully";
                        break;
                    }
                    case 7:{
                        PreparedStatement preparedStatement =conn.prepareStatement("SHOW TABLES");
                        ResultSet resultSet = preparedStatement.executeQuery();
                        System.out.println("TABLE IN DATABASE");
                        while(resultSet.next()){
                            String tableJ = resultSet.getString("Tables_in_javalearning");
                            System.out.println(tableJ);
                        }
                        res = "\ntables list Successfully";
                        break;
                    }
                    case 8:
                        System.out.println("THANK YOU");
                        break loop;
                    default: 
                        System.out.println("Option Error!");
                }
                System.out.println(res);
                System.out.print("\nChoose your option (enter): ");
            }
        }
    }
}
