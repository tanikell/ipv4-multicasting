package mobile.computing.ws1819.client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import mobile.computing.ws1819.Message;

public class Host1 {

	static HostRestClient restClient = new HostRestClient();

	static String status;
	

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);

		System.out.println("Would you like to join or leave: ");
	
		status = sc.nextLine();
		
		System.out.println("You have entered: " + status);
		Host host1 = new Host("10.10.10.10:8000", 0, Message.generateMR());

		if(status.equals("join")) {
			try {
				System.out.println("Sending Host: ");
				restClient.postHost(host1);
				System.out.println("Post Success");
			} catch (IOException e) {
				System.out.println("Post Failure");
				e.printStackTrace();
			}
			
		} else if (status.equals("leave")){
			try {
				System.out.println("Deleting Host: "+host1.getId());
				restClient.deleteHost(host1.getId());
				System.out.println("Delete Success");
			} catch (IOException e) {
				System.out.println("Delete Failure");
				e.printStackTrace();
			}		
		} else {
			System.out.println("Something wrong with your input");
		}
		

	}

}
