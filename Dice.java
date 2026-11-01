import java.util.Random;
public class Dice {
    private Random ran;

    public Dice(){
        ran = new Random();
    }

    public int Roll(){
        return ran.nextInt(6) + 1;
    }

}
