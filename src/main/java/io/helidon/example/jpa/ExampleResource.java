package io.helidon.example.jpa;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
//import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.ws.rs.PathParam;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
//import org.hibernate.query.Query;

/**
 * Simple JAXRS resource class.
 */
@Dependent
@Path("/example")
public class ExampleResource {
	@PersistenceContext
	private EntityManager em;

	@Produces("text/plain")
	@Path("hello")
	@GET
	public String get() {
		return "It works!";
	}

	@GET
	@Path("response/{salutation}")
	@Produces("text/plain")
	@Transactional
	public String getResponse(@PathParam("salutation") String salutation) {
		System.out.println("in method getResponse...");
		final Greeting greeting = this.em.find(Greeting.class, salutation);
		final String returnValue;
		if (greeting == null) {
			System.out.println("greeting is null");
			returnValue = null;
		} else {
			returnValue = greeting.getResponse();
			System.out.println("response is: " + returnValue);
		}
		return returnValue;
	}

	@GET
	@Path("get")
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional
	public List<Greeting> getAll() {
		System.out.println("in method getResponse...");
		final Query greeting = this.em.createNativeQuery("Select SALUTATION,RESPONSE from GREETING");
		final List<Greeting> returnValue;
		if (greeting == null) {
			System.out.println("greeting is null");
			returnValue = null;
		} else {
			returnValue = greeting.getResultList();
			System.out.println("response is: " + returnValue);
		}
		return returnValue;
	}

	
	 @PersistenceUnit
	  private EntityManagerFactory entityManagerFactory;

	@POST
	@Path("post")
	@Consumes(MediaType.APPLICATION_JSON)
	@Transactional
	@PersistenceContext
	public Greeting insert(@RequestBody Greeting greeting) {
		System.out.println("in method getResponse..." + greeting);

//		EntityTransaction et = em.getTransaction();
//		EntityManagerFactory ef = em.getEntityManagerFactory();
//		et.begin();

//		Query query = this.em.createNativeQuery("INSERT INTO Greeting (SALUTATION, RESPONSE) VALUES (?,?)");
//		em.getTransaction().begin();
//		query.setParameter(1, greeting.getSalutation());
//		query.setParameter(2, greeting.getResponse());
//		query.executeUpdate();
//		this.em.persist(query);
		EntityManager entityManager = entityManagerFactory.createEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
	    
	    entityTransaction.begin();
	    entityManager.persist(greeting);
	    entityTransaction.commit();

	    entityManager.close();
//		this.em.persist(greeting);
//		et.commit();
//		ef.getPersistenceUnitUtil();

//		em.getTransaction().commit();

//		System.out.println("salutation "+greeting.getSalutation());
//		System.out.println("response "+greeting.getResponse());
		System.out.println("data successfully insert!!!" + greeting);
		return greeting;

	}

//	Query query = em.createNativeQuery("INSERT INTO Bike (id, name) VALUES (:id , :name);");
//	em.getTransaction().begin();
//	query.setParameter("id", "5");
//	query.setParameter("name", "Harley");
//	query.executeUpdate();
//	em.getTransaction().commit();

//	@POST
//	@Path("insert")
//	@Produces("text/plain")
//	@Transactional
//	public void getInsert() {
//		System.out.println("in method Insert()");
//		String insertQuery = "INSERT INTO GREETING (SALUTATION, RESPONSE) VALUES ('Hp', 'Dell')";
//		final Query greeting = this.em.createQuery(insertQuery);
//		final String returnValue;
//		if (greeting == null) {
//			System.out.println("greeting is null");
//			returnValue = null;
//		} else {
////			returnValue = greeting.getResponse();
//			System.out.println("response is: " + insertQuery);
//		}
////		return returnValue;
//	}

//	@GET
//	@Path("/getAll")
//	@Produces("text/plain")
//	@Transactional
//	public List<Greeting> getUser() {
//		Session sess = em.unwrap(Session.class);
//		Query emailFetchQuery = sess.createQuery("Select SALUTATION from GREETING");
////		emailFetchQuery.setParameter("email", email);
//		return emailFetchQuery.getResultList();
//	}
}
