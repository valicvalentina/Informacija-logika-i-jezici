package hr.tel.fer.lab1.server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import hr.tel.fer.lab1.antlr.*;
import hr.tel.fer.lab1.logging.*;
import hr.tel.fer.lab1.query.RequestLexer;
import hr.tel.fer.lab1.query.RequestParser;

public class ServerExecutor implements Runnable {
	String cnt;
	int port;
	Socket socket;
	List<LogEntry> logs;
	LogLoader loader = new LogLoader();
	ServerSocket ssc;

	public ServerExecutor(int port, String file) {
		this.port = port;
		try {
			ssc = new ServerSocket(port);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			this.logs = loader.load(new FileReader(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		};
	}
	
	public int getPort() {
		return port;
	}
	
	public ServerSocket getSsc() {
		return this.ssc;
	}
	
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try(PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
			
			String request = in.readLine();
			//izdvajanje broja iz requesta
			String[] req = request.split("RETURN");
			cnt = req[1].replaceAll("\"", "");
			
			try {
				List<LogEntry> logs = processInput(request); //vraca filtrirane logove
				while(request != null) {
					if(logs.isEmpty()) {
						out.println("No match");
						out.println();
					} else {
						processOutput(logs, out);
					}
					
					request = in.readLine();
					if(request != null) {
						req = request.split("RETURN");
						cnt = req[1].replaceAll("\"", "");
						logs = processInput(request);
					}
				}
			} catch(RequestException e) {
				out.println(e.getMessage());
			}
		} catch(IOException ioe) {
			// ako pukne veza nemamo što napraviti
		} finally {
			try {
				socket.close();
			} catch(Exception e) {
				// ako ne možemo zatvoriti socket ne možemo ništa napraviti
			}
		}
	}
	
	private void processOutput(List<LogEntry> logs, PrintWriter out) {
		if(cnt.equals("*")) {
			logs.forEach(log -> {
				out.print(log.getIp());
				out.print(" [");
				out.print(log.getDateTimeAsString());
				out.print("] ");
				out.print(log.getMethod());
				out.print(" ");
				out.print(log.getPath());
				out.print(" ");
				out.print(log.getVersion());
				out.print(" ");
				out.print(log.getStatus());
				out.print(" ");
				out.println(log.getClient());
			});
			out.println();
			
		     } else {
		    	 
			int cnt1 = Integer.parseInt(cnt);
			for( int i=0;i<logs.size();i++) {
				if(cnt1>0) {
				out.print(logs.get(i).getIp());
				out.print(" [");
				out.print(logs.get(i).getDateTimeAsString());
				out.print("] ");
				out.print(logs.get(i).getMethod());
				out.print(" ");
				out.print(logs.get(i).getPath());
				out.print(" ");
				out.print(logs.get(i).getVersion());
				out.print(" ");
				out.print(logs.get(i).getStatus());
				out.print(" ");
				out.println(logs.get(i).getClient());
				cnt1 --;
				}
				if(cnt1 == 0) {
					break;
				}
			}
			out.println();
		}
	}
	
	private List<LogEntry> processInput(String request) throws IOException {
		List<Expression> expressions = parseExpressions(request);
		List<LogEntry> filteredLogs = filterLogs(expressions);

		return filteredLogs;
	}

	private List<LogEntry> filterLogs(List<Expression> expressions) {
		LogEntryFilter filter = new LogEntryFilter();
		expressions.forEach(e -> filter.add(e));

		List<LogEntry> filteredLogs = filter.filter(logs);
		
		return filteredLogs;
	}

	private List<Expression> parseExpressions(String request) throws IOException {
		CharStream input = CharStreams.fromString(request);

		RequestLexer lexer = new RequestLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);

		RequestParser parser = new RequestParser(tokens);
		ParseTree tree = parser.request();

		ParseTreeWalker walker = new ParseTreeWalker();
		ExpressionExtractor listener = new ExpressionExtractor();
		walker.walk(listener, tree);

		if(listener.getError() != null) {
			throw new RequestException(listener.getError());
		}

		List<Expression> expressions = listener.getExpressions();
		
		return expressions;
	}
}
