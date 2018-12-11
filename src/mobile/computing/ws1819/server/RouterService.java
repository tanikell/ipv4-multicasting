package mobile.computing.ws1819.server;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.soap.MessageFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mobile.computing.ws1819.Message;
import mobile.computing.ws1819.client.Host;

@Path("routerService")
public class RouterService {
    //static ArrayList<Object> multicastGroup = new ArrayList<Object>();
    static Hashtable multicastGroup7 = new Hashtable();
    static Hashtable multicastGroup9 = new Hashtable();

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
	@Path("/hosts/mcg{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getHostsFromMCG7(@PathParam("id") int id) throws JsonProcessingException {
		System.out.println("Getting Hosts in MCG: "+id);
		ObjectMapper mapper = new ObjectMapper();
		Object mgHosts = null;
		if (id == 7) {
			mgHosts = mapper.writeValueAsString(multicastGroup7.values());	
		} else {
			mgHosts = mapper.writeValueAsString(multicastGroup9.values());	
		}
		
		return mgHosts;
	}
	
	
	// @TODO: Implement ping for all registered clients
	/*Client client = ClientBuilder.newClient();
	WebTarget target = client.target("http://localhost:8080");
	Response response = target.path("api").path("server").path("ping").request(MediaType.TEXT_PLAIN_TYPE).get();
	System.out.println("Response: " + response.getStatus() + " - " + response.readEntity(String.class));*/
	
	
	/*me
	 * public void sendToOneClient (String userName, String ipAddress, Map<String, Client> clients)
{
    Client c = clients.get(userName + ":" + ipAddress);

    java.net.Socket socket = c.getSocket();

    // Sending the response back to the client.
    // Note: Ideally you want all these in a try/catch/finally block
    OutputStream os = socket.getOutputStream();
    OutputStreamWriter osw = new OutputStreamWriter(os);
    BufferedWriter bw = new BufferedWriter(osw);
    bw.write("Some message");
    bw.flush();
}
	 */
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
				
		ObjectMapper mapper = new ObjectMapper();
		Host host = mapper.readValue(hostString, Host.class);
		System.out.println("hostID: "+host.getId());
		System.out.println("hostID: "+host.getMessage().getGroup_address());
		
		if (host.getMessage().getGroup_address().equals("224.7.7.7")) {
			multicastGroup7.put(host.getId(), hostString);		
		} else {
			multicastGroup9.put(host.getId(), hostString);		
		}
		
		return "Registered";
	}
	
	@POST	
	@Path("/broadcastMessage")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String broadcastMessage(String message) throws JsonParseException, JsonMappingException, IOException {

		System.out.println("broadcastMessage: "+message);

		return "Message Posted";
	}

	@DELETE
	@Path("/deregister/{group}/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deregisterHost(@PathParam("group") int group, @PathParam("id") int id) throws NoSuchFieldException, SecurityException {
		System.out.println("Group : "+group+" id: "+id);
		
		if(group == 7) {
			multicastGroup7.remove(id);
		} else {
			multicastGroup9.remove(id);
		}
		
		return "Host Unregistered";
	}

}