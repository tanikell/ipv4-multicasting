package mobile.computing.ws1819.client;
import java.io.IOException;
import java.util.Scanner;

public class Host2 {

	static HostRestClient restClient = new HostRestClient();

	static String status;
	

	public static void main(String[] args) throws IOException {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);

		System.out.println("Would you like to join or leave: ");
	
		status = sc.nextLine();
		
		System.out.println("You have entered: " + status);
		
		Host h2 = Host.generateHost2();

		if(status.equals("join")) {
			try {
				System.out.println("Sending Host: ");
				HostRestClient.postHost(h2);
				System.out.println("Post Success");
			} catch (IOException e) {
				System.out.println("Post Failure");
				e.printStackTrace();
			}
			
		} else if (status.equals("leave")){
			try {
				System.out.println("Deleting Host: "+h2.getId());
				HostRestClient.deleteHost(h2.getId());
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
