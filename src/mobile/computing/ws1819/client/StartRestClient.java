package mobile.computing.ws1819.client;

import java.io.IOException;

import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import mobile.computing.ws1819.Message;

public class StartRestClient
{

	public static void main(String[] args) throws IOException
	{
		doGetRequest();
		
		doPostRequest();

		doPutRequest();

		doDeleteRequest();
	}


	public static void doGetRequest() throws IOException
	{
		// Send GET request
		WebResource service = Client.create().resource("http://localhost:8080/api");
		
		String response = service.path("harsha").accept(MediaType.APPLICATION_JSON).get(String.class);

		System.out.println("Received JSON String:\n" + response);

		// Deserialise Message
		ObjectMapper mapper = new ObjectMapper();
		Message message = mapper.readValue(response, Message.class);
		System.out.println("Creating Message Object...\n" + message);
	}




	public static void doPostRequest() throws JsonProcessingException
	{
		Message message = Message.generateExampleMessage();

		// Serialise Message Object
		ObjectMapper mapper = new ObjectMapper();

		String messageAsJSONstring = mapper.writeValueAsString(message);

		// Send POST request
		Client create = Client.create();
		WebResource service = create.resource("http://localhost:8080/api");
		String response = service.path("harsha").type(MediaType.APPLICATION_JSON).post(String.class, messageAsJSONstring);
		System.out.println(response);
	}




	private static void doPutRequest() throws JsonProcessingException
	{
		Message message = Message.generateExampleMessage();

		// Serialise Message Object
		ObjectMapper mapper = new ObjectMapper();

		String messageAsJSONstring = mapper.writeValueAsString(message);

		// Send PUT request
		Client create = Client.create();
		WebResource service = create.resource("http://localhost:8080/api");
		String response = service.path("message").path(String.valueOf(message.getId())).type(MediaType.APPLICATION_JSON).put(String.class, messageAsJSONstring);
		System.out.println(response);
	}




	private static void doDeleteRequest() throws JsonProcessingException
	{
		Message message = Message.generateExampleMessage();

		// Serialise Message Object
		ObjectMapper mapper = new ObjectMapper();

		String messageAsJSONstring = mapper.writeValueAsString(message);

		// Send DELETE request
		Client create = Client.create();
		WebResource service = create.resource("http://localhost:8080/api");
		String response = service.path("message").path(String.valueOf(message.getId())).type(MediaType.APPLICATION_JSON).delete(String.class, messageAsJSONstring);
		System.out.println(response);
	}
}
