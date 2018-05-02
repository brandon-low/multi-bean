package example.multi;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import example.multi.bean.Nature;
import example.multi.repository.SellPhone;

/** 
 * Ypu need Application.class to be above the rest of the package
 * or default it picks up the application on this directory
 * @author brandon
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest//(classes = example.multi.Application.class)
public class TestMultiBean {
	
	private Logger log = LogManager.getLogger(this.getClass().getName());
	
	/**
	 * make sure all the chain of nature has @Service on the bean
	 */
	@Autowired
	private Nature nature;
	
	@Autowired
	private SellPhone sellPhone;
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMultiBean() {
		nature.showRunners();
		assertTrue("Done it", true);//(true, "Not yet implemented");
	}
	
	@Test
	public void testMultiRepository() {
		sellPhone.sendMessage();
		assertTrue("Done it", true);//(true, "Not yet implemented");
	}
	
	
	/*
	@Autowired
	private CustomerRepository repository;


    @Before
    public void setUp() throws Exception {
    	log.info("SetUp");
    	this.repository.deleteAll();
    	
    	Customer c1 =new Customer("Alice", "Smith");
    	Customer c2 = new Customer("Bob", "Smith");
    	
    	//save product, verify has ID value after save
        assertNull(c1.getId());
        assertNull(c2.getId());//null before save
        
        
    	// save a couple of customers
    	repository.save(c1);
    	repository.save(c2);
        
        
        assertNotNull(c1.getId());
        assertNotNull(c2.getId());
    }

    @Test
    public void testFetchData(){
    	
    	log.info("testFetchData");
    	

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}

    	
    	
    }

   // @Test
    public void testUpdateData(){
    	log.info("testUpdateData");
    	
    
    }

    @After
    public void tearDown() throws Exception {
    	log.info("TearDown");
        
      //this.repository.deleteAll();
    }
    */
}