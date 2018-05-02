package example.json;

import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

public class TestJSON {

	private Person createDummy() {
		Person p = new Person();
		p.setName("mkyong");
		p.setAge(33);
		p.setPosition("Developer");
		p.setSalary(new BigDecimal("7500"));

		List<String> skills = new ArrayList<>();
		skills.add("java");
		skills.add("python");

		p.setSkills(skills);

		List<Person> friends = new ArrayList<>();
		
		Person p1 = new Person();
		p1.setName("king tree");
		p1.setAge(22);
		p1.setPosition("Tree Cutter");
		p1.setSalary(new BigDecimal("500"));

		List<String> skills1 = new ArrayList<>();
		skills1.add("chain saw");
		skills1.add("angle grinder");
		p1.setSkills(skills1);
		
		Person p2 = new Person();
		p2.setName("Cheater");
		p2.setAge(22);
		p2.setPosition("Chief");
		p2.setSalary(new BigDecimal("0"));

		List<String> skills2 = new ArrayList<>();
		skills2.add("lies");
		skills2.add("none of your business");
		p2.setSkills(skills2);
		
		friends.add(p1);
		friends.add(p2);
		// friends.add(p);
		
		p.setFriends(friends);
		
		
		
		return p;
	}
	@Test
	public void test() {
		
		
		try {
			
			ObjectMapper mapper = new ObjectMapper();

			Person p = createDummy();
			// Convert object to JSON string and save into a file directly
			//mapper.writeValue(new File("D:\\staff.json"), staff);

			// Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(p);
			//System.out.println(jsonInString);

			// Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(p);
			System.out.println(jsonInString);
			
			Person p1 = mapper.readValue(mapper.writeValueAsString(p), Person.class);
			System.out.println(p1);


		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue("Done Good", true);
	}

}
