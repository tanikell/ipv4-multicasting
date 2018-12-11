package mobile.computing.ws1819.client;
import java.util.Scanner;

import mobile.computing.ws1819.Message;


public class Host {

	public String socket;
	public int id;
	public Message message;
	 
	public void setId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	public void setSocket(String socket) {
		this.socket = socket;
	}
	
	public String getSocket() {
		return socket;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	
	public Message getMessage() {
		return message;
	}
	
    public static Host createHost1(int groupToJoin) {

		Host host1 = new Host();
		//Message message = new Message();
		host1.setSocket("10.10.10.1:8001");
		host1.setId(1);
		host1.setMessage(Message.generateMR(groupToJoin));
		
		return host1;
    }
    
    public static Host createHost2(int groupToJoin) {
		Host host2 = new Host();
		//Message message = new Message();
		host2.setSocket("10.10.10.1:8002");
		host2.setId(2);
		host2.setMessage(Message.generateMR(groupToJoin));
		
		return host2;
    }
    
    public static Host createHost3(int groupToJoin) {
		Host host3 = new Host();
		//Message message = new Message();
		host3.setSocket("10.10.10.1:8003");
		host3.setId(3);
		host3.setMessage(Message.generateMR(groupToJoin));
		
		return host3;
    }
    
    public static Host createHost4(int groupToJoin) {
		Host host4 = new Host();
		//Message message = new Message();
		host4.setSocket("10.10.10.1:8004");
		host4.setId(4);
		host4.setMessage(Message.generateMR(groupToJoin));
		
		return host4;
    }
    
}