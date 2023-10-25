package com.bigcorp.app.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Exemple d'utilisation de JDBC pour créer une 
 * table en base et la requêter.
 * Attention, nécessite un driver JDBC h2 dans le classpath
 * (enlever <scope>test</scope> pour la dépendance h2 dans le pom.xml)
 */
public class ExampleJdbc {

	public static void main(String[] args) {
		Properties connectionProps = new Properties();
		connectionProps.put("user", "test");
		connectionProps.put("password", "test");
		try (Connection connection = DriverManager.getConnection(
				"jdbc:h2:file:C:/dev/h2-data/sample-jdbc",
				connectionProps)) {
			ExampleJdbc exampleJdbc = new ExampleJdbc();
			exampleJdbc.launchQueries(connection);
		} catch (SQLException e) {
			throw new RuntimeException("Could not connect to database ", e);
		}
	}

	public void launchQueries(Connection con) {
		String dropTableQuery = "drop table COMPANY IF EXISTS ";
		String createTableQuery = "create table COMPANY ("
				+ "       ID bigint not null,"
				+ "        NAME varchar(255),"
				+ "        primary key (ID) "
				+ "    )";
		String insertQuery = "insert into COMPANY(ID, NAME) VALUES (1, 'bigCorp')";
		String selectQuery = "select ID, NAME from COMPANY";
		try (Statement stmt = con.createStatement()) {
			stmt.executeUpdate(dropTableQuery);
			stmt.executeUpdate(createTableQuery);
			stmt.executeUpdate(insertQuery);
			ResultSet rs = stmt.executeQuery(selectQuery);
			while (rs.next()) {
				long id = rs.getLong("ID");
				String name = rs.getString("NAME");
				System.out.println("id : " + id + " , name : " + name);
			}
		} catch (SQLException e) {
			throw new RuntimeException("SQL Error", e);
		}
	}

}
