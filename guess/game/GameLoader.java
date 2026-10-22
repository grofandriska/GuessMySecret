package game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GameLoader {
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
        this.scanner = new Scanner(System.in);
        this.initGameAndPlayers();
        setMaxNumber();
        System.out.println(startGame());
        System.exit(0);
    }

    public String startGame() {
        int guess;
        boolean isWon = false;
        this.secretNumber = this.randomNumberGenerator.nextInt(this.upperBound);

        System.out.println("Starting the game");
        for (int i = 0; i < this.playerList.size(); i++) {
            System.out.println("\nIt's your turn player called: " + this.playerList.get(i).getName());
            for (int k = 0; k < this.playerList.get(i).getMaxTry(); k++) {
                System.out.println("Please type in the number of your guess! You have " + (this.playerList.get(i).getMaxTry() - this.playerList.get(i).getGuessedNumbersCount()) + " guesses left");
                guess = scanner.nextInt();
                this.playerList.get(i).setGuessedNumbersCount(k + 1);
                if (guess == this.secretNumber) {
                    System.out.println("You have Won the game!");
                    isWon = true;
                } else if (guess < this.secretNumber) {
                    System.out.println("\nSecret number is bigger!");
                } else if (guess > this.secretNumber) {
                    System.out.println("\nSecret number is smaller!");
                }
                if (isWon) {
                    return "\nThe winner is : " + this.playerList.get(i).getName() + "! The Secret number was: " + this.secretNumber;
                }
            }
        }
        return "Noone won the game! The secret number was: " + secretNumber;
    }

    public void initGameAndPlayers() {
        initPlayerListSize();
        for (int i = 0; i < this.numberOfPlayers; i++) {
            System.out.println("Please add " + (i + 1) + ". player name!");
            this.scanner = new Scanner(System.in);
            String userName = this.scanner.nextLine();

            validatePlayerName(userName);

        }
    }

    public void initPlayerListSize() {
        if (numberOfPlayers < 2) {
            try {
                scanner = new Scanner(System.in);
                System.out.println("Type in the number of the Players!");
                numberOfPlayers = scanner.nextInt();

                while (numberOfPlayers < 2) {
                    System.out.println("Type in the number of the Players!");
                    numberOfPlayers = scanner.nextInt();
                }
            } catch (Exception e) {
                System.out.println("Min player count is 2");
            } finally {
                initPlayerListSize();
            }
        }
    }

    public void setMaxNumber() {
        if (upperBound < 10) {
            try {
                this.scanner = new Scanner(System.in);
                System.out.println("Please add the maximum value of Secret Number!");
                this.upperBound = this.scanner.nextInt();

                while (upperBound < 10) {
                    System.out.println("Type in the UpperBound!");
                    numberOfPlayers = scanner.nextInt();
                }

            } catch (Exception e) {
                System.out.println("Must be a positive number or a smaller number");
            } finally {
                setMaxNumber();
            }
        }
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
                    System.out.println("There is another " + name +"! Choose another nickname please!");
                    return;
                }
            }
            Player newPlayer = new Player(newName);
            playerList.add(newPlayer);
            System.out.println("Hello " + newPlayer.getName() + "!");
    }
    
                    
                
    


    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Random getRandomNumberGenerator() {
        return randomNumberGenerator;
    }

    public void setRandomNumberGenerator(Random randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public int getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(int upperBound) {
        this.upperBound = upperBound;
    }

    public int getSecretNumber() {
        return secretNumber;
    }

    public void setSecretNumber(int secretNumber) {
        this.secretNumber = secretNumber;
    }

    class Player {
        private String name;
        private int maxTry = 3;
        private int guessedNumbersCount = 0;

        public Player(String name) {
            this.name = name;
        }
        public int getMaxTry() {
            return maxTry;
        }
        public void setMaxTry(int maxTry) {
            this.maxTry = maxTry;
        }
        public int getGuessedNumbersCount() {
            return guessedNumbersCount;
        }
        public void setGuessedNumbersCount(int guessedNumbersCount) {
            this.guessedNumbersCount = guessedNumbersCount;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
