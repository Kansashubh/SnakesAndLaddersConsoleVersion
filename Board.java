import java.util.HashMap;
public class Board {
    private int NumberOfSquares;
    private HashMap<Integer, Integer> Snakes;
    private HashMap<Integer, Integer> Ladders;

    public Board(){
        this.NumberOfSquares = 100;
        this.Snakes = new HashMap<>();
        this.Ladders = new HashMap<>();
        setupLadders();
        setupSnakes();
    }

    public void setupSnakes(){
        // The position for the Snakes
        Snakes.put(42,1 );
        Snakes.put(25, 3);
        Snakes.put(94, 12);
        Snakes.put(61,43);
        Snakes.put(56,48);
        Snakes.put(98,80);
        Snakes.put(92,67);

    }
    public void setupLadders(){
        Ladders.put(7,30);
        Ladders.put(16,33);
        Ladders.put(20,38);
        Ladders.put(36,83);
        Ladders.put(50,68);
        Ladders.put(63,81);
        Ladders.put(71,89);
        Ladders.put(86,97);
    }

    public int getNumSquares(){
        return NumberOfSquares;
    }

    public int getNextPositions(int CurrentSquare){
        if (Ladders.containsKey(CurrentSquare)) {
            return Ladders.get(CurrentSquare);
        } else if (Snakes.containsKey(CurrentSquare)) {
            return Snakes.get(CurrentSquare);
        }
        else{
            return CurrentSquare;
        }
    }


}
