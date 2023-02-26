package utils;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
public class HibernateUtils {
	private static SessionFactory factory;
	static { // static block to create singleTon object of sessionfactory
		System.out.println("in static init block");
		factory = new Configuration().configure().buildSessionFactory(); 
		// config method present in Configuration class will call hibernate.cfg.xml file to run
		// and configure all settings and then using buildSessionFactory method build pool
	}
	// getter
	public static SessionFactory getFactory() {
		return factory;
	}
}