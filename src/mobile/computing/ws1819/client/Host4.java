package mobile.computing.ws1819.client;

import java.io.IOException;
import java.util.Scanner;

public class Host4 {

	static HostRestClient restClient = new HostRestClient();

	static String status;
	
	static int group;

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		if(args.length >= 1) {
			for(int i = 0; i < args.length; i++) {
				System.out.println("At Host4 - Server Says:  "+args[i]);
			}
		}

		else {
			System.out.println("Would you like to join/leave/multicast (j/l/m) ? : ");

			status = sc.nextLine();

			if (status.equals("j")) {
				//Host h1 = Host.generateHost1();
				System.out.println("Which MCG to join (7/9) ? : ");

				group = Integer.parseInt(sc.nextLine());
				try {
					System.out.println("Sending Host: ");
					HostRestClient.postHost(Host.createHost4(group));
					System.out.println("Post Success");
				} catch (IOException e) {
					System.out.println("Post Failure");
					e.printStackTrace();
				}
			} else if (status.equals("l")) {
				System.out.println("Which MCG to leave from (7/9) ? : ");

				group = Integer.parseInt(sc.nextLine());
				try {
					HostRestClient.deleteHost(group , Host.createHost4(group).getId());
					System.out.println("Delete Success");
				} catch (IOException e) {
					System.out.println("Delete Failure");
					e.printStackTrace();
				}
			} else if (status.equals("ml")) {
				System.out.println("Which Multicast group (7/9) ? : ");
				group = Integer.parseInt(sc.nextLine());

				System.out.println("Enter your message to group : ");
				status = sc.nextLine();
				
				HostRestClient.sendMessage(group, status);

			} else {
				System.out.println("Something wrong with your input");
			}
		}

	}

}
