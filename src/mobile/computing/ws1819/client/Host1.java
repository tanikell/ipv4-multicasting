package mobile.computing.ws1819.client;

import java.io.IOException;
import java.util.Scanner;

public class Host1 {

	static HostRestClient restClient = new HostRestClient();

	static String status;

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException {

		System.out.println("Would you like to join/leave/broadcast (j/l/b) ? : ");

		status = sc.nextLine();

		if (status.equals("j")) {
			Host h1 = Host.generateHost1();

			try {
				System.out.println("Sending Host: ");
				HostRestClient.postHost(h1);
				System.out.println("Post Success");
			} catch (IOException e) {
				System.out.println("Post Failure");
				e.printStackTrace();
			}

		} else if (status.equals("l")) {
			Host h1 = Host.generateHost1();

			try {
				System.out.println("Deleting Host: " + h1.getId());
				HostRestClient.deleteHost(h1.getId());
				System.out.println("Delete Success");
			} catch (IOException e) {
				System.out.println("Delete Failure");
				e.printStackTrace();
			}
		} else if (status.equals("b")) {

			System.out.println("Enter your message to group : ");
			status = sc.nextLine();
			HostRestClient.broadcastMessage(status);

		} else {
			System.out.println("Something wrong with your input");
		}

	}

}
