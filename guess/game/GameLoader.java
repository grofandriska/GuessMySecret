package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameLoader {
    private static final int MIN_PLAYERS = 2;
    private static final int MIN_UPPER_BOUND = 10;

    private Scanner scanner;
    private List<Player> playerList;
    private Random randomNumberGenerator;
    private int numberOfPlayers, secretNumber, upperBound;

    public static void main(String[] args) {
        new GameLoader();
    }

    public GameLoader() {
        this.randomNumberGenerator = new Random();
        this.playerList = new ArrayList<>();
        this.scanner = new Scanner(System.in);   // one Scanner for the whole game
        this.initGameAndPlayers();
        this.setMaxNumber();
        System.out.println(startGame());
    }

    /**
     * Keeps asking until the user types a whole number that is at least {@code min}.
     * Letters, empty lines and too-small numbers are all handled here, so nothing crashes.
     */
    private int readInt(String prompt, int min) {
        while (true) {
            System.out.println(prompt);
            if (!scanner.hasNextLine()) {          // input closed (e.g. Ctrl+D)
                System.out.println("No more input, bye!");
                System.exit(0);
            }
            String line = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(line);
                if (value >= min) {
                    return value;
                }
                System.out.println("It must be at least " + min + ".");
            } catch (NumberFormatException e) {
                System.out.println("'" + line + "' is not a whole number. Try again!");
            }
        }
    }

    public String startGame() {
        // 1..upperBound, so the "maximum value" really is the maximum
        this.secretNumber = this.randomNumberGenerator.nextInt(this.upperBound) + 1;

        // the players already exist here, and upperBound is finally known
        int tries = Math.max(1, upperBound / 10);
        for (Player player : playerList) {
            player.setMaxTry(tries);
        }

        System.out.println("\nStarting the game! The secret number is between 1 and " + upperBound
                + ". Everyone has " + tries + (tries == 1 ? " guess." : " guesses."));
        for (Player player : playerList) {
            System.out.println("\nIt's your turn player called: " + player.getName());
            while (player.getGuessedNumbersCount() < player.getMaxTry()) {
                int left = player.getMaxTry() - player.getGuessedNumbersCount();
                int guess = readInt("Please type in the number of your guess! You have " + left
                        + (left == 1 ? " guess left" : " guesses left"), 1);

                if (player.getGuessedNumbers().contains(guess)) {
                    System.out.println("You tried " + guess + " before! This one is on the house, try again.");
                    continue;   // does NOT cost a try
                }
                player.addGuess(guess);

                if (guess == this.secretNumber) {
                    System.out.println("You have Won the game!");
                    return "\nThe winner is : " + player.getName() + "! The Secret number was: " + this.secretNumber;
                } else if (guess < this.secretNumber) {
                    System.out.println("\nSecret number is bigger!");
                } else {
                    System.out.println("\nSecret number is smaller!");
                }
            }
        }
        return "\nNo one won the game! The secret number was: " + secretNumber;
    }

    public void initGameAndPlayers() {
        initPlayerListSize();
        // keep asking until we really have numberOfPlayers valid, unique names
        while (playerList.size() < numberOfPlayers) {
            System.out.println("Please add " + (playerList.size() + 1) + ". player name!");
            if (!scanner.hasNextLine()) {
                System.out.println("No more input, bye!");
                System.exit(0);
            }
            validatePlayerName(scanner.nextLine().trim());
        }
    }

    public void initPlayerListSize() {
        this.numberOfPlayers = readInt("Type in the number of the Players! (min " + MIN_PLAYERS + ")", MIN_PLAYERS);
    }

    public void setMaxNumber() {
        this.upperBound = readInt("Please add the maximum value of Secret Number! (min " + MIN_UPPER_BOUND + ")", MIN_UPPER_BOUND);
    }

    public void validatePlayerName(String name) {
        if (name == null || name.isEmpty()) {
            System.out.println("What is your Name, come on!");
            return;
        }
        // Name Validation
        String newName = "Curious " + name;
        for (Player p : playerList) {
            if (p.getName().equalsIgnoreCase(newName)) {
                System.out.println("There is another " + name + "! Choose another nickname please!");
                return;
            }
        }
        Player newPlayer = new Player(newName);
        playerList.add(newPlayer);
        System.out.println("Hello " + newPlayer.getName() + "!");
    }

    public Scanner getScanner() { return scanner; }
    public void setScanner(Scanner scanner) { this.scanner = scanner; }
    public int getNumberOfPlayers() { return numberOfPlayers; }
    public void setNumberOfPlayers(int numberOfPlayers) { this.numberOfPlayers = numberOfPlayers; }
    public List<Player> getPlayerList() { return playerList; }
    public void setPlayerList(List<Player> playerList) { this.playerList = playerList; }
    public Random getRandomNumberGenerator() { return randomNumberGenerator; }
    public void setRandomNumberGenerator(Random randomNumberGenerator) { this.randomNumberGenerator = randomNumberGenerator; }
    public int getUpperBound() { return upperBound; }
    public void setUpperBound(int upperBound) { this.upperBound = upperBound; }
    public int getSecretNumber() { return secretNumber; }
    public void setSecretNumber(int secretNumber) { this.secretNumber = secretNumber; }

    static class Player {
        private String name;
        private int maxTry = 3;
        private List<Integer> guessedNumbers = new ArrayList<>();

        public Player(String name) { this.name = name; }
        public int getMaxTry() { return maxTry; }
        public void setMaxTry(int maxTry) { this.maxTry = maxTry; }
        public int getGuessedNumbersCount() { return guessedNumbers.size(); }
        public List<Integer> getGuessedNumbers() { return guessedNumbers; }
        public void addGuess(int guess) { guessedNumbers.add(guess); }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}
