package spring_in_action.c4;

@SuppressWarnings("serial")
public class PerformanceException extends RuntimeException {
  public PerformanceException() {}
  
  public PerformanceException(String message) {
    super(message);
  }
}
