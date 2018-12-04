package mobile.computing.ws1819.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

@Path("harsha")
public class MessageResource {
    Object messageArray[] = new Object[3];
    ArrayList<Object> messageList = new ArrayList<Object>();
    
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
	public int getHosts() throws JsonProcessingException {
		return messageList.size();
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
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String createMessage(String messageAsJSONstring)
			throws JsonParseException, JsonMappingException, IOException {
		//ArrayList<Message > list=new ArrayList<>(); 
    
		// Deserialise JSON message
		//ObjectMapper mapper = new ObjectMapper();
		//Message message = mapper.readValue(messageAsJSONstring, Message.class);
		
		
		messageList.add(0, messageAsJSONstring);
		messageList.add(1, messageAsJSONstring);
		messageList.add(2, messageAsJSONstring);
		messageList.add(3, messageAsJSONstring);

			//messageArray = messageList.toArray(messageArray);
		
		System.out.println("Creating Message Object...\n" + messageList.size() + messageList);

		
		return "OK";
		
		
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteMessage(@PathParam("id") int id) {
		System.out.println("\nReceived DELETE Request for Resource with ID: " + id);
		System.out.println("Current List: "+messageList);
		messageList.remove(id);
		System.out.println("Current List: "+messageList);
		return "OK";
	}

}