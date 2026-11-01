import java.util.ArrayList;
import java.util.Scanner;


public class App {
    public static void main(String[] args){
        boolean checked = false;
        int numberOfPlayers = 0;

        System.out.println("Welcome to Snakes and Ladders!");
        Scanner scanner = new Scanner(System.in);
        

        while (checked == false) {
            System.out.println("Mention Number of players (it has to be between 2 and 4):");
            numberOfPlayers = scanner.nextInt();
            
            if (numberOfPlayers >= 2 && numberOfPlayers <= 4) {
                checked = true;
                System.out.println("Great! Setting up the game for " + numberOfPlayers + " players.");
            } else {
                checked = false;
                System.out.println("Invalid Input. Please enter a number between 2 and 4.");
            }
        }

        scanner.nextLine();

        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++){
            Player newPlayer = new Player("Player " + (i + 1));
            players.add(newPlayer);
            System.out.println("Created player name: " + newPlayer.getName());
        }

        Board game = new Board();
        Dice cube = new Dice();
        
        Boolean GameFinished = false;
        Player winner = null;


        while (GameFinished == false){
            for (int i = 0; i < players.size(); i++){
                try {
                    // Pause for 3 seconds (3000 milliseconds)
                    Thread.sleep(3000); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                clearScreen();
                Player incumbentPlayer = players.get(i); // Get the current player
                boolean pressed = false;
                System.out.println("-----------------------------------------------");
                System.out.println(incumbentPlayer.getName() + "'s turn Current Square: " + incumbentPlayer.getSquare());

                while (pressed == false) {
                    System.out.print("Type 'r' and press Enter to roll: ");
                    String input = scanner.nextLine();
                    
                    if (input.equals("r")) {
                        pressed = true;
                    } else {
                        System.out.println("Invalid input! You must type 'r' to roll.");
                    }
                }

                int NumberFromRollingtheDice = cube.Roll();

                System.out.println(incumbentPlayer.getName() + " rolled a " + NumberFromRollingtheDice + "!");

                int CurrentPosition = incumbentPlayer.getSquare();

                int PotentialPosition = CurrentPosition + NumberFromRollingtheDice;

                if (PotentialPosition > game.getNumSquares()){
                    int Excess = PotentialPosition - game.getNumSquares();
                    int BouncedPosition = game.getNumSquares() - Excess;

                    System.out.println("There was an overflow and you are bounced back to " + BouncedPosition);
                    incumbentPlayer.setPosition(BouncedPosition);

                    int UpdatedPosition = game.getNextPositions(BouncedPosition);

                    if (UpdatedPosition < BouncedPosition){
                        System.out.println("Oops "+ incumbentPlayer.getName() + " you bumped into a Snake and now you will slide down from " + BouncedPosition +  " to " + UpdatedPosition);
                        incumbentPlayer.setPosition(UpdatedPosition);
                    }
                    else{
                        System.out.println("You landed safely on " + BouncedPosition);
                    }

                }

                else{
                    incumbentPlayer.setPosition(PotentialPosition);

                    if (incumbentPlayer.getSquare() == game.getNumSquares()) {
                        GameFinished = true;
                        winner = incumbentPlayer;
                        break;
                    }
                    

                    int incumbentPosition = game.getNextPositions(incumbentPlayer.getSquare());

                    if (incumbentPosition > incumbentPlayer.getSquare()){
                        System.out.println(incumbentPlayer.getName() + "You hit a ladder and climbed from " + incumbentPlayer.getSquare() + " to " + incumbentPosition);
                        incumbentPlayer.setPosition(incumbentPosition);
                    }
                    else if (incumbentPosition < incumbentPlayer.getSquare()){
                        System.out.println("You hit a snake and you will slide from " + incumbentPlayer.getSquare() + " to " + incumbentPosition);
                        incumbentPlayer.setPosition(incumbentPosition);

                    }
                    else {
                        System.out.println(incumbentPlayer.getName() + " landed on the square of " + incumbentPlayer.getSquare());
                    }
                }


            }
        }

        System.out.println("\n=========================================");
        System.out.println("CONGRATULATIONS! " + winner.getName() + " wins the game!");
        System.out.println("=========================================");
        
        scanner.close();


    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}