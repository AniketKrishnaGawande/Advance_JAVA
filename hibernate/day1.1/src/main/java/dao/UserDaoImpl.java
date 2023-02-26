package dao;

import pojos.Role;
import pojos.User;
import org.hibernate.*;
import static utils.HibernateUtils.getFactory;

public class UserDaoImpl implements UserDao {

	@Override
	public String saveUserDetails(User newUser) {
		String mesg = "User reg failed !!!!!!!!!!!!";
		// 1. get session from SF
		Session session = getFactory().openSession();
		Session session2 = getFactory().openSession();
// openSession() gives new session obj from pool always
		System.out.println(session == session2);// false
		// 2. Begin a tx
		Transaction tx = session.beginTransaction();
		System.out.println("is open " + session.isOpen() + " is connected " + session.isConnected());// t t

		try {
			// newUser is in TRANSIENT state as it is not in L1 cache nor in db but present
			// in heap
			// save : insert
			Integer id = (Integer) session.createQuery(Role.class,10)
					save(newUser);
			// newUser : PERSISTENT => it's a part of L1 cache,not part of DB yet
			tx.commit();// Hibernate perform auto dirty checking -->
			// session.flush() --> makes the changes from
			// L1 cache ---> DB -- DML : insert
			mesg = "User regd with ID=" + id;
			System.out.println("is open " + session.isOpen() + " is connected " + session.isConnected());// t t

		} catch (RuntimeException e) {
			// roll back Tx
			if (tx != null)
				tx.rollback();
			// re throw the exc to the caller
			throw e;
		} finally {
			// close session
			if (session != null)
				session.close();// pooled out DB cn rets to
			// the pool n L1 cache is destroyed!
		}
		System.out.println("is open " + session.isOpen() + " is connected " + session.isConnected());// f f

		// newUser : DETACHED as it is not in l1 cache but present in DB
		return mesg;
	}

	@Override
	public String saveUserDetailsViaGetCurrentSession(User newUser) {
		String mesg = "User reg failed !!!!!!!!!!!!";
		// 1. get session from SF
		Session session = getFactory().getCurrentSession();
		Session session2 = getFactory().getCurrentSession();
		// as method is getCurrentSession() and session already present in method same
		// session is retuned by getCurrentSession*() method
		System.out.println(session == session2);// true
		// 2. Begin a tx
		Transaction tx = session.beginTransaction();
		System.out.println("is open " + session.isOpen() + " is connected " + session.isConnected());// t t

		try {
			// newUser : TRANSIENT
			// save : insert
			Integer id = (Integer) session.save(newUser);
			// newUser : PERSISTENT => it's a part of L1 cache,not part of DB yet
			tx.commit();// Hibernate perform auto dirty checking -->
			// session.flush() --> makes the changes from
			// L1 cache ---> DB -- DML : insert
			mesg = "User regd with ID=" + id;
			System.out.println("is open " + session.isOpen() + " is connected " + session.isConnected());// f f
			// as connection close when code of boundary method invoked ir commit or roll back

		} catch (RuntimeException e) {
			// roll back Tx
			if (tx != null)
				tx.rollback();
			System.out.println("is open " + session.isOpen() + " is connected " + session.isConnected());// f f

			// re throw the exc to the caller
			throw e;
		}

		// newUser : DETACHED
		return mesg;
	}

}
