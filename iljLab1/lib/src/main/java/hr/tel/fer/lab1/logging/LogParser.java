package hr.tel.fer.lab1.logging;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogParser {
	static String IP = "(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}) ";
	static String dateTime = "\\[(\\d{1,2}/\\w{3}/\\d{4}:\\d{2}:\\d{2}:\\d{2})\\] ";
	static String method = "([A-Z]{3,7}) ";
	static String path = "(\\*|/[^\"]*) ";
	static String version = "(HTTP/\\d{1}\\.\\d{1}) ";
	static String status = "(\\d{3}) ";
	static String client = "(\"[^\"]*\")";
	
	private static final String regex = "^" + IP + dateTime + method + path + version + status + client + "$";
	private static final Pattern pattern = Pattern.compile(regex);

	
	
	public Optional<LogEntry> parse(String line) {
		Matcher matcher = pattern.matcher(line);
		if(matcher.find()) {
			LogEntry entry = new LogEntry();
			
			System.out.println(matcher.group(0));
			entry.setIp(matcher.group(1));
			entry.setDateTime(matcher.group(2));
			entry.setMethod(matcher.group(3));
			entry.setPath(matcher.group(4));
			entry.setVersion(matcher.group(5));
			entry.setStatus(Integer.parseInt(matcher.group(6)));
			entry.setClient(matcher.group(7));
				
			return Optional.of(entry);
		}
		
		return Optional.empty();
	}
}
