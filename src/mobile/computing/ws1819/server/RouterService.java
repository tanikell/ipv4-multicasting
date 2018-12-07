package mobile.computing.ws1819.server;

import java.io.IOException;
import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mobile.computing.ws1819.Message;
import mobile.computing.ws1819.client.Host;

@Path("routerService")
public class RouterService {
    static ArrayList<Object> multicastGroup = new ArrayList<Object>();
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getMessage() throws JsonProcessingException {
		System.out.println("\nReceived GET Request");
		// Generate message
		Message message = Message.generateExampleMessage();

		// Serialise Message 
		ObjectMapper mapper = new ObjectMapper();
		String messageAsJSONstring = mapper.writeValueAsString(message);

		return messageAsJSONstring;
	}
	
	@GET
	@Path("/hosts")
	@Produces(MediaType.APPLICATION_JSON)
	public String getHosts() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();

		String mgHosts = mapper.writeValueAsString(multicastGroup);	
		
		return mgHosts;
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateMessage(@PathParam("id") String id, String messageAsJSONstring)
			throws JsonParseException, JsonMappingException, IOException {
		System.out.println(
				"\nReceived PUT Request with JSON String:\n" + messageAsJSONstring + " and Parameter ID: " + id);

		// Deserialise JSON message
		ObjectMapper mapper = new ObjectMapper();
		Message message = mapper.readValue(messageAsJSONstring, Message.class);
		System.out.println("Updating Message Object with ID: " + id + "...\n" + message);

		return "OK";
	}

	@POST	
	@Path("/register")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String registerHost(String hostString) throws JsonParseException, JsonMappingException, IOException {
		//ObjectMapper mapper = new ObjectMapper();
		
		//Host hostObj = mapper.readValue(hostString, Host.class);
		
		multicastGroup.add(hostString);		

		System.out.println("Creating Message Object...\n" + multicastGroup.size() + multicastGroup);

		return "Registered";
		
	}

	@DELETE
	@Path("/deregister/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deregisterHost(@PathParam("id") int id) {
		System.out.println("Deleted at: "+id);

		multicastGroup.remove(id);
		System.out.println("Deleted List: "+multicastGroup);
		return "Host Unregistered";
	}

}