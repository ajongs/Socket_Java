package socket;

import java.net.*;
import java.io.*;
public class client {

	public static void main(String[] args) {
		BufferedReader in = null;
		BufferedWriter out = null;
		BufferedReader stin = null;
		Socket socket = null;
		try{
			socket = new Socket("localhost", 9999);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			stin = new BufferedReader(new InputStreamReader(System.in));
			String outputMessage;
			while(true){
				outputMessage = stin.readLine();
				if(outputMessage.equalsIgnoreCase("bye") ){
					out.write(outputMessage);
					out.flush();
					break;
				}
				out.write("클라이언트>"+outputMessage+"\n");
				out.flush();
				String inputMessage = in.readLine();
				System.out.println(inputMessage);
			}
		} catch(IOException e){
			System.out.println(e.getMessage());
		} finally{
			try{
				socket.close(); // 클라이언트 소켓 닫기
			}catch(IOException e){
				System.out.println("서버와 채팅 중 오류가 발생했습니다.");
			}
		}
		
		
	}

}
