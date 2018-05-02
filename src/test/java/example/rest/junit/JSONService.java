package example.rest.junit;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
	
}
