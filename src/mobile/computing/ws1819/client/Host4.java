package mobile.computing.ws1819.client;
import java.io.IOException;
import java.util.Scanner;

public class Host4 {

	static HostRestClient restClient = new HostRestClient();

	static String status;
	

	public static void main(String[] args) throws IOException {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.println("Would you like to join or leave: ");
	
		status = sc.nextLine();
		
		System.out.println("You have entered: " + status);
		
		Host h4 = Host.generateHost4();

		if(status.equals("join")) {
			try {
				System.out.println("Sending Host: ");
				HostRestClient.postHost(h4);
				System.out.println("Post Success");
			} catch (IOException e) {
				System.out.println("Post Failure");
				e.printStackTrace();
			}
			
		} else if (status.equals("leave")){
			try {
				System.out.println("Deleting Host: "+h4.getId());
				HostRestClient.deleteHost(h4.getId());
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
