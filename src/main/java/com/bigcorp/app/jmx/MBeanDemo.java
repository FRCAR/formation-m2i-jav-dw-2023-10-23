package com.bigcorp.app.jmx;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class MBeanDemo {

	public static void main(String[] args) {
		MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

		ObjectName name = null;
		try {
			name = new ObjectName("com.bigcorp.app.jmx:type=SuperMBean");

			SuperMBean mbean = new SuperMBean();
			mbs.registerMBean(mbean, name);

			System.out.println("Lancement ...");
			while (true) {

				TimeUnit.SECONDS.sleep(1);
				System.out.println(mbean.getValue());
				mbean.increment();

			}
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (InstanceAlreadyExistsException e) {
			e.printStackTrace();
		} catch (MBeanRegistrationException e) {
			e.printStackTrace();
		} catch (NotCompliantMBeanException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
		}
	}
}