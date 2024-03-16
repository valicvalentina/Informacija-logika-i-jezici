package hr.tel.fer.lab1.server;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerM {
public static void main(String[] args) throws IOException {
		
		
		String a = args[2];
		ServerExecutor st = new ServerExecutor(Integer.parseInt(args[1]), args[2]);
		//max broj klijenata
		ExecutorService pool = Executors.newFixedThreadPool(Integer.parseInt(args[0]));
		
		while(true) {
			Socket socket = st.getSsc().accept();
			st.setSocket(socket);
			pool.execute(st);
		   }
		
		
	   }
}
