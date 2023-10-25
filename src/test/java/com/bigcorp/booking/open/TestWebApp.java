package com.bigcorp.booking.open;

import java.util.Properties;

import org.apache.openejb.jee.WebApp;
import org.apache.openejb.jee.jpa.unit.PersistenceUnit;
import org.apache.openejb.testing.Application;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.Configuration;

import com.bigcorp.app.correction.dao.RestaurantDao;
import com.bigcorp.app.correction.dao.RestaurantTypeDao;
import com.bigcorp.app.correction.service.RestaurantService;
import com.bigcorp.app.dao.ExampleDao;
import com.bigcorp.app.service.ExampleService;

/**
 * Créée un serveur d'application JEE pour les tests.
 */
@Application
public class TestWebApp {
	
	@org.apache.openejb.testing.Module
	public PersistenceUnit persistence() {
		PersistenceUnit unit = new PersistenceUnit("PersisterPU");
		unit.setJtaDataSource("bookingTestDatabase");
		unit.setNonJtaDataSource("bookingTestDatabaseUnmanaged");
		unit.setProperty("jakarta.persistence.schema-generation.database.action", "drop-and-create");
		unit.setProperty("openjpa.jdbc.SynchronizeMappings", "buildSchema(ForeignKeys=true)");
		unit.setProperty("openjpa.Log", "DefaultLevel=WARN,Runtime=INFO,Tool=INFO,SQL=TRACE");

		return unit;
	}

	@org.apache.openejb.testing.Module
	@Classes(cdi = true, value = { 
			ExampleService.class, ExampleDao.class,
			RestaurantService.class, RestaurantDao.class,
			RestaurantTypeDao.class
			})
	public WebApp app() {
		return new WebApp();
	}

	@Configuration
	public Properties configInMemory() throws Exception {
		Properties p = new Properties();
		p.put("bookingTestDatabase", "new://Resource?type=DataSource");
		p.put("bookingTestDatabase.JdbcDriver", "org.h2.Driver");
		p.put("bookingTestDatabase.JdbcUrl", "jdbc:h2:file:~/h2-data/formation-test");
		
		return p;
	}

	public Properties configLocalDataBase() throws Exception {
		Properties p = new Properties();
		p.put("bookingTestDatabase", "new://Resource?type=DataSource");
		p.put("bookingTestDatabase.JdbcDriver", "org.apache.derby.client.ClientAutoloadedDriver");
		p.put("bookingTestDatabase.JdbcUrl", "jdbc:derby://localhost:1527/test-junit;create=true");
		p.put("bookingTestDatabase.UserName", "test");
		p.put("bookingTestDatabase.password", "test");
		return p;
	}
}
