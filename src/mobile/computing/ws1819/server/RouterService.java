package mobile.computing.ws1819.server;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;

import javax.swing.SortingFocusTraversalPolicy;
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
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import mobile.computing.ws1819.Message;
import mobile.computing.ws1819.client.Host;
import mobile.computing.ws1819.client.Host1;
import mobile.computing.ws1819.client.Host2;
import mobile.computing.ws1819.client.Host3;
import mobile.computing.ws1819.client.Host4;


@Path("routerService")
public class RouterService {
    //static ArrayList<Object> multicastGroup = new ArrayList<Object>();
    static Hashtable multicastGroup7 = new Hashtable();
    static Hashtable multicastGroup9 = new Hashtable();
    
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getMessage() throws JsonProcessingException, IOException {
		System.out.println("\nReceived GET Request");
		// Generate message
		Message message = Message.generateExampleMessage();

		// Serialise Message 
		ObjectMapper mapper = new ObjectMapper();
		String messageAsJSONstring = mapper.writeValueAsString(message);

		return messageAsJSONstring;
	}

	@POST	
	@Path("/multicastMessage/{group}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String multicastMessage(@PathParam("group") int group, String message) throws JsonProcessingException, IOException {

		System.out.println("Multicasting: "+message+" to Group: "+group);
		String[] a = new String[] {message};
		Enumeration<Integer> keys;
		
		if(group == 7) {
			keys = multicastGroup7.keys();
		} else {
			keys = multicastGroup9.keys();
		}

		while(keys.hasMoreElements()){
			int key = keys.nextElement();
			if (key==1) {
				Host1.main(a);
			} else if (key==2) {
				Host2.main(a);
			}else if (key==3) {
				Host3.main(a);
			}else{
				Host4.main(a);
			}
		}

		return "Message multicased to clients";
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
	@Path("/sendMessage/{group}")
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public String sendMessage(@PathParam("group") int group, String message) throws JsonParseException, JsonMappingException, IOException {

		//this.hostMessage = message;
		System.out.println("sendMessage: "+message);
		//this.multicastMessage(group, message);
		Client create = Client.create();
		WebResource service = create.resource("http://localhost:8080/api");
		String response = service.path("routerService/multicastMessage").path(String.valueOf(group)).type(MediaType.APPLICATION_JSON).post(String.class, message);
		System.out.println(response);
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