package hr.tel.fer.lab1.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	Socket socket;
	String query;
	
	public Client(String IP, int port, String query) {
		try {
			//tu stvaram socket konekciju
			this.socket = new Socket(IP, port);
		} catch(UnknownHostException e) {
			e.printStackTrace(System.out);
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		this.query = query;
	}
	
	public Socket getSocket() {
		return socket;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getQuery() {
		return query;
	}
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		String serverString = "";
		String query=sc.nextLine();
		
		while(!(query).isEmpty()) {
			//kreiram novog klijenta
			Client newClient = new Client(args[0], Integer.parseInt(args[1]), query);
			//otvaram ulazni i izlazni tok
			PrintWriter out = new PrintWriter(newClient.getSocket().getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(newClient.getSocket().getInputStream()));
			
			  out.println(query);
			   serverString = in.readLine();
		     	while(!serverString.equals("")) {
				  System.out.println(serverString);
				    serverString = in.readLine();
			}
			
			       out.close();
			      in.close();
			
			query = sc.nextLine();
		  }
		 System.out.println("END");
	}
}
