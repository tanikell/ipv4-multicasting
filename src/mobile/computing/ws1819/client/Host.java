package mobile.computing.ws1819.client;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import mobile.computing.ws1819.client.StartRestClient;
import mobile.computing.ws1819.Message;


public class Host {

	public String port;
	public String ip;
	public String status;
	
	static StartRestClient restClient = new StartRestClient();
	static Message message = new Message();

    public void setHost(String ip, String port, String status) {
        this.ip = ip;
        this.port = port;
        this.status = status;
    }

    public static Host createHost() {
		Host host1 = new Host();
		host1.setHost("10.10.10.0", "8000", "join");
		return host1;
    }
	

	public static void main(String[] args) {

		try {
			System.out.println("Sending Host: ");
			restClient.doPostRequest();
			System.out.println("Post Success");
		} catch (IOException e) {
			System.out.println("Post Failure");
			e.printStackTrace();
		}
		
	}

}