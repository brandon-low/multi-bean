package example.rest.junit;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/json/product")
//@Path("jsonservice")
public class JSONService {
	
	

	@GET
	@Path("/get")
	@Produces("application/json")
	public Product getProductInJSON() {

		System.out.println("Got a get about to send stuff back");
		Product product = new Product();
		product.setName("iPad 3");
		product.setQty(999);
		
		return product; 

	}

	@POST
	@Path("/post")
	@Consumes("application/json")
	public Response createProductInJSON(Product product) {
		
		String result = "Product created : " + product;
		System.out.println("Got a POST about to send stuff back :" + result);

		return Response.status(201).entity(result).build();
		
	}
	

	private void dumpHeader(HttpHeaders headers) {
		
		 Set<String> headerKeys = headers.getRequestHeaders().keySet();
	     for(String header:headerKeys){
	    	 System.out.println(header+":"+ headers.getRequestHeader(header).get(0));
	     }
	}
	
	@GET
	@Path("/getWithHeader")
	@Produces("application/json")
	public Product getProductInJSONWithHeader(@Context HttpHeaders headers) {
	
		System.out.println("Got a get With Header about to send stuff back");
		dumpHeader(headers);
		
		
		Product product = new Product();
		product.setName("iPad 3 header stuff");
		product.setQty(999);
		
		return product; 
	
	}
	
	@POST
	@Path("/postWithHeader")
	@Consumes("application/json")
	public Response createProductInJSONWithHeader(@Context HttpHeaders headers, Product product) {
			
		String result = "Product created : " + product;
		System.out.println("Got a POST about to send stuff back :" + result);

		dumpHeader(headers);
	
		return Response.status(201).entity(result).build();
		
	}
	


	
}
