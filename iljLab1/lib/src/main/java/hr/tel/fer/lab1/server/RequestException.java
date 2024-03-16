package hr.tel.fer.lab1.server;

public class RequestException extends RuntimeException {

  public RequestException(String error) {
    super(error);
  }

}
