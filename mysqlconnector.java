//install jar all files including jar file path

//package sql;

import java.util.*;

import java.sql.*;



public class mysql8 {

	public static void main(String args[]) {

		Connection connection = null;

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");

			connection = DriverManager.getConnection("jdbc:mysql://10.10.15.96/sql33db","sql33","sql33");

			Statement statement = connection.createStatement();

//			creating table in database test
			String createTable = "CREATE TABLE IF NOT EXISTS asgn (id VARCHAR(255), name VARCHAR(255));";
            		statement.executeUpdate(createTable);

			
			ResultSet result;

			Scanner in = new Scanner(System.in);

			String ch = "y";

			while(ch != "n") {

				System.out.println("----------------------------------------------------------");

				System.out.println("1. Print Records");

				System.out.println("2. Add Records");

				System.out.println("3. Update Record");

				System.out.println("4. Delete Record");

				int c = in.nextInt();

				if(c == 1) {

					String print = "select * from asgn;";

					result = statement.executeQuery(print);

					while(result.next()) {

						System.out.print(result.getString("id"));

						System.out.print(" ");

						System.out.print(result.getString("name"));

						System.out.println("");

					}

				}

				else if(c == 2) {

					String name , id;

					System.out.println("Enter id and name of the employee - ");

					id = in.next();

					name = in.next();

					String insert = "insert into asgn values(" + id + "," +" ' "  + name + "');";

					statement.executeUpdate(insert);
				}

				else if(c == 3) {

					String name,id;

					System.out.println("Enter id of the employee who's record to be updated - ");

					id = in.next();

					System.out.println("Enter new name - ");

					name = in.next();

					String update = "update asgn set name = " + "'" + name + "'" + " where id = " + id + ";";

					statement.executeUpdate(update);

				}

				else if(c==4) {

					String name , id;

					System.out.println("Enter id  of the employee who's record to be deleted - ");

					id = in.next();

					String del = "delete from asgn where id = " + id + ";";

					statement.executeUpdate(del);

				}

				else {

					System.out.println("You have entered a wrong choice.");

				}

				

				System.out.println("Do you want to continue (y/n) - ");

				ch = in.next();

				if(ch == "n"){

					ch = "n";

				}

			}

			

 		}

		catch(Exception e) {

			System.out.print(e);

		}

	}

}
