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
			System.out.println("�����");
			//�������̾�Ʈ�� ����� ���� ����� ��Ʈ�� ����
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//Ŭ���̾�Ʈ�� ������ �۽� 
			stin = new BufferedReader(new InputStreamReader(System.in));
			//Ŭ���̾�Ʈ�� ���� ������ ����
			String inputMessage;
			while(true){
				inputMessage= in.readLine();
				if(inputMessage.equalsIgnoreCase("bye"))
					break;
				System.out.println(inputMessage);
				String outputMessage = stin.readLine();
				out.write("����>"+outputMessage+"\n");
				out.flush();
			}
				
		} catch(IOException e){
			System.out.println(e.getMessage());
		}finally{
			try {
				socket.close();
				listener.close();
			} catch(IOException e){
				System.out.println("Ŭ���̾�Ʈ�� ä���� ������ �߻�");
			}
		}
		
	}

}
