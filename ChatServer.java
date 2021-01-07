package my_thread_socket;
import java.net.*;
import java.io.*;
public class ChatServer{
	public static void main(String[] args)
	{
		Server s = new Server();
		s.get();
		s.send();
	}
}

class Server{
	ServerSocket serverSocket;
	Socket socket;
	BufferedReader in;
	BufferedReader stin;
	BufferedWriter out;
	
	public Server(){
		try{
			serverSocket = new ServerSocket(9999);
			socket = serverSocket.accept();
			System.out.println("클라이언트 연결됨 (정보 : " + socket.getRemoteSocketAddress());
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	public void get(){
		Thread get_thread = new Thread(){
			@Override
			public void run() {
				try{
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					String message;
					while(true){
						message = in.readLine();
						System.out.println(message);
						if(message.equalsIgnoreCase("bye")){
							serverSocket.close();
							socket.close();
							return;
						}
							
					}
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
		};
		get_thread.start();
	}
	public void send(){
		Thread send_thread = new Thread(){
			@Override
			public void run() {
				try{
					out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					stin = new BufferedReader(new InputStreamReader(System.in));
					String outputMessage;
					while(true){
						outputMessage = stin.readLine();
						if(outputMessage.equalsIgnoreCase("bye")){
							out.write(outputMessage);
							out.flush();
							serverSocket.close();
							socket.close();
							break;
						}
						out.write("서버 : " + outputMessage+"\n");
						out.flush();
					}
				}catch(Exception e){
					try{
						System.out.println("[메시지 송신 오류] "+socket.getRemoteSocketAddress() + Thread.currentThread().getName());
						socket.close();
						serverSocket.close();
					}catch(Exception e2){
						e2.printStackTrace();;
					}
				}
			}
		};
		send_thread.start();
	}

}