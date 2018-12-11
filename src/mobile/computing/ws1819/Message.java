package mobile.computing.ws1819;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Random;
import java.util.Scanner;


import mobile.computing.ws1819.client.HostRestClient;


public class Message{

	private int max_response_time;
	private int check_sum;
	private String Group_address;
	private String Type;
	
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}
	public int getMax_response_time() {
		return max_response_time;
	}
	public void setMax_response_time(int max_response_time) {
		this.max_response_time = max_response_time;
	}
	public int getCheck_sum() {
		return check_sum;
	}
	public void setCheck_sum(int check_sum) {
		this.check_sum = check_sum;
	}
	public String getGroup_address() {
		return Group_address;
	}
	public void setGroup_address(String group_address) {
		Group_address = group_address;
	}
	
	public static Message generateExampleMessage()
	{
		Random random = new Random();
		
		System.out.println("Send join:");
		Scanner scan = new Scanner(System.in);

		Message message = new Message();
		message.setType("jdfhjg");
		message.setMax_response_time(random.nextInt());
		message.setCheck_sum(6456);
		message.setGroup_address("0.0.0.0");
		
		scan.close();
		
		return message;
	}
	
	public static Message generateMR(int id)
	{
		Random random = new Random();
		
		Scanner scan = new Scanner(System.in);

		Message message = new Message();
		message.setType("0X16");
		message.setMax_response_time(random.nextInt());
		message.setCheck_sum(1);
		
		if(id == 7) {
			message.setGroup_address("224.7.7.7");
		} else {
			message.setGroup_address("224.9.9.9");
		}
		
		scan.close();
		
		return message;
	}
	
	public static Message generateLG()
	{
		Random random = new Random();
		
		Scanner scan = new Scanner(System.in);

		Message message = new Message();
		message.setType("0X17");
		message.setMax_response_time(random.nextInt());
		message.setCheck_sum(1);
		message.setGroup_address("224.7.7.7");
		
		scan.close();
		
		return message;
	}

	@Override
	public String toString()
	{
		String outputMessage = String.format("Message: [max_response_time: %s, check_sum: %s, group_address: %s, Type: %s]", max_response_time, check_sum, Group_address, Type );
		return outputMessage;
	}

}


