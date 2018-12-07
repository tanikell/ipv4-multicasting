package mobile.computing.ws1819.client;
import mobile.computing.ws1819.Message;


public class Host {

	public String socket;
	public int id;
	public Message message;
	 
	//static Message message = new Message();

    public Host(String socket, int id, Message message) {
        this.socket = socket;
        this.id = id;
        this.message = message;
    }

	public int getId() {
		return id;
	}
}