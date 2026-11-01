public class Player {
    private String Nickname;
    private int CurrentSquareNumber;
    

    public Player(String Name){
        Nickname = Name;
        CurrentSquareNumber = 0;
    }

    public String getName(){
        return Nickname;
    }

    public int getSquare(){
        return CurrentSquareNumber;
    }

    public void setPosition(int Squarenum){
        CurrentSquareNumber = Squarenum;
    }

    public void Progress(int Squares){
        CurrentSquareNumber += Squares;
    }

    
}
