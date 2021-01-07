package socket;
import java.net.*;
import java.io.*;
public class Server {

	public static void main(String[] args) {
		Socket socket = null;
		ServerSocket listener = null;
		BufferedReader in = null;
		BufferedReader stin = null;
		BufferedWriter out = null;
		try{
			listener = new ServerSocket(9999);
			socket = listener.accept();
			System.out.println("연결됨");
			//ㅋ를라이언트와 통신을 위한 입출력 스트림 생성
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//클라이언트로 데이터 송신 
			stin = new BufferedReader(new InputStreamReader(System.in));
			//클라이언트로 부터 데이터 수신
			String inputMessage;
			while(true){
				inputMessage= in.readLine();
				if(inputMessage.equalsIgnoreCase("bye"))
					break;
				System.out.println(inputMessage);
				String outputMessage = stin.readLine();
				out.write("서버>"+outputMessage+"\n");
				out.flush();
			}
				
		} catch(IOException e){
			System.out.println(e.getMessage());
		}finally{
			try {
				socket.close();
				listener.close();
			} catch(IOException e){
				System.out.println("클라이언트와 채팅중 오류가 발생");
			}
		}
		
	}

}
