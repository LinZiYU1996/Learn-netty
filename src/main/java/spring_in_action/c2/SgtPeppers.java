package spring_in_action.c2;
import org.springframework.stereotype.Component;


/*

 */
@Component
public class SgtPeppers implements CompactDisc {

  private String title = "a";
  private String artist = "b";
  
  public void play() {
    System.out.println(title+artist);
  }
  
}
