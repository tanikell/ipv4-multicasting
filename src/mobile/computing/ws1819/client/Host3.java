package mobile.computing.ws1819.client;
import java.io.IOException;
import java.util.Scanner;

import mobile.computing.ws1819.Message;

public class Host3 {

	static HostRestClient restClient = new HostRestClient();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);

		System.out.println("Would you like to join or leave: ");
	
		String status = sc.nextLine();
		
		System.out.println("You have entered: " + status);
		Host host3 = new Host("10.10.10.5:8002", 2, Message.generateMR());

		if(status.equals("join")) {
			try {
				System.out.println("Sending Host: ");
				restClient.postHost(host3);
				System.out.println("Post Success");
			} catch (IOException e) {
				System.out.println("Post Failure");
				e.printStackTrace();
			}
			
		} else if (status.equals("leave")){
			try {
				System.out.println("Deleting Host: "+host3.getId());
				restClient.deleteHost(host3.getId());
				System.out.println("Delte Success");
			} catch (IOException e) {
				System.out.println("Delete Failure");
				e.printStackTrace();
			}		
		} else {
			System.out.println("Something wrong with your input");
		}
	}

}
