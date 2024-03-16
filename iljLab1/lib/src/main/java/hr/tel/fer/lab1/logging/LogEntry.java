package hr.tel.fer.lab1.logging;

public class LogEntry {
  private String ip;
  private String datetime;
  private String method;
  private String path;
 private String version;
  private int status;
  private String client;

  public LogEntry() {
  }

  public LogEntry(String ip, String datetime, String method, String path,String version, int status, String client) {
    this.ip = ip;
    this.datetime = datetime;
    this.method = method;
    this.path=path;
    this.version = version;
    this.status = status;
    this.client=client;
  }

  public String getClient() {
	return client;
}

public void setClient(String client) {
	this.client = client;
}

public String getDateTimeAsString() {
    return datetime;
  }

  public String getIp() {
    return ip;
  }

  public String getMethod() {
    return method;
  }

  public int getStatus() {
    return status;
  }

  public String getVersion() {
    return version;
  }

  public void setDateTime(String datetime) {
    this.datetime = datetime;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public void setStatus(int status) {
    this.status = status;
  }
  public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
