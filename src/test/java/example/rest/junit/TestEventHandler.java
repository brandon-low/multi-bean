package example.rest.junit;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
 
@Path("testEvent")
public class TestEventHandler {
	public TestEventHandler() {
		super();
	}
	 
	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	public String postMovieEvent(@Context Request request, String json) {
		System.out.println("received event:" + json);
		//TestEventRouter.sendPost(json);
		return "event received " + json;
	}
	 
	@GET
	@Produces("text/plain")
	public String getMovieEvent(@Context Request request) {
		return "nothing to report from getMovieEvent";
	}
	 
}