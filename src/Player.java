import java.util.Timer;

public class Player {
    String name;
    boolean color;
    Timer time;

    public Player(String name, boolean color){
        this.name = name;
        this.color = color;
        time = null;
    }
}
