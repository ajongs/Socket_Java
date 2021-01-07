package my_thread_socket;
import java.net.*;
import java.io.*;
public class ChatClient{
	public static void main(String[] args){
		Client client = new Client();
		client.get_message();
		client.send_message();
	}
}
class Client {
	Socket socket;
	BufferedReader in;
	BufferedReader stin;
	BufferedWriter out;
	
//	public Client(){
//		
//	}
	
	public void get_message(){
		Thread thread = new Thread(){
			@Override
			public void run() {
				try{
					socket = new Socket("localhost", 9999);
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					String inputMessage;
					while(true){
						inputMessage = in.readLine();
						System.out.println(inputMessage);
						if(inputMessage.equalsIgnoreCase("bye")){
							socket.close(); 	
							break;
						}
					}
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
		};
		thread.start();
	}
	public void send_message(){
		Thread thread = new Thread(){
			@Override
			public void run() {
				try{
					stin = new BufferedReader(new InputStreamReader(System.in));
					String outputMessage;
					while(true){
						outputMessage = stin.readLine();
						if(outputMessage.equalsIgnoreCase("bye")){
							out.write(outputMessage);
							out.flush();
							socket.close();
							break;
						}
						out.write("클라이언트 : " + outputMessage+"\n");
						out.flush();
					}
				}catch(Exception e){
					try{
						System.out.println("[메시지 송신 오류] "+socket.getRemoteSocketAddress() + Thread.currentThread().getName());
						socket.close();
					}catch(Exception e2){
						e2.printStackTrace();;
					}
				}
				
			}
			
		};
		thread.start();
	}

}
