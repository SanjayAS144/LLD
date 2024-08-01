public class Player {
    private final String Name;
    private final char Marker;
    private int NumberOfWins;
    private final int State;

    private Player(String name, char marker,int State) {
        Name = name;
        Marker = marker;
        NumberOfWins = 0;
        this.State = State;
    }

    public String getName() {
        return Name;
    }

    public char getMarker() {
        return Marker;
    }

    public int getState(){
        return this.State;
    }

    public int getNumberOfWins() {
        return NumberOfWins;
    }

    public void onWin(){
        NumberOfWins++;
    }

    public static class PlayerBuilder{
        private String Name;
        private char Marker;
        private int State;

        public PlayerBuilder() {
        }

        public PlayerBuilder setName(String name) {
            Name = name;
            return this;
        }

        public PlayerBuilder setMarker(char marker) {
            Marker = marker;
            return this;
        }

        public PlayerBuilder setState(int state) {
            State = state;
            return this;
        }

        public Player build(){
            return new Player(this.Name,this.Marker,this.State);
        }
    }
}
