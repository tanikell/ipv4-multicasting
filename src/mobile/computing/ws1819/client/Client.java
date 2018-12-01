package mobile.computing.ws1819.client;

import java.io.IOException;

import mobile.computing.ws1819.client.StartRestClient;

public class Client {

	static StartRestClient restClient = new StartRestClient();
	
	public static void main(String[] args) {
		// Simple get call 
		try {
			//restClient.doPostRequest();
			//System.out.println("Post Success");
			restClient.doGetRequest();
			System.out.println("Get Success");
		} catch (IOException e) {
			System.out.println("Get Failure");
			e.printStackTrace();
		}
	}

}
