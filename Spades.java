import java.util.HashMap;
import java.util.Scanner;

public class Spades {
    
    public static void printScores(HashMap<String, Integer> scores) {
        for (String key : scores.keySet()) {
            System.out.println(key + ": " + scores.get(key));
        }
    }

    public static boolean checkWin(HashMap<String, Integer> scores, int winningScore) {
        for (String key : scores.keySet()) {
            if (scores.get(key) >= winningScore) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // use scanner to scan 4 names from 4 players
        
        System.out.println("Enter the name of the first player:");
        String player1 = input.nextLine();
        System.out.println("Enter the name of the second player:");
        String player2 = input.nextLine();
        System.out.println("Enter the name of the third player:");
        String player3 = input.nextLine();
        System.out.println("Enter the name of the fourth player:");
        String player4 = input.nextLine();

        System.out.println("What score would you like to play to?\n");
        int winningScore = input.nextInt();

        HashMap<String, Integer> scores = new HashMap<String, Integer>();
        String names[] = {player1, player2, player3, player4};
        // add players to hashmap with score 0
        scores.put(player1, 0);
        scores.put(player2, 0);
        scores.put(player3, 0);
        scores.put(player4, 0);

        int wagers[] = new int[4];
        
        int roundCount = 0;

        while (!checkWin(scores, winningScore)) { //beginning of game loop
            int totalWager = 0;
            System.out.println("Start of Round " + ++roundCount + "!");
            
            
            for (int i = 0; i < 4; i++) {
                System.out.println("Enter the wager for " + names[i] + ":");
                wagers[i] = input.nextInt();
                totalWager += wagers[i];
            }
            if (totalWager < 10) {
                System.out.println("The total wager is under 10 so we will add everyone's wagers and move to the next round.\n");
                for (int i = 0; i < 4; i++) {
                    scores.put(names[i], scores.get(names[i]) + wagers[i]);
                }
            } else {
                System.out.println("You may play the round.\n");
                for (int i = 0; i < 4; i++) {
                    System.out.println("Enter the number of points " + names[i] + " got:\n");
                    int tricks = input.nextInt();

                    if (tricks >= wagers[i]) { // if they took enough tricks than they wagered (end of play of round)
                        scores.put(names[i], scores.get(names[i]) + wagers[i]);
                        System.out.println(names[i] + " won their wager of " + wagers[i] + "!\n"); 
                    } else {
                        scores.put(names[i], scores.get(names[i]) - wagers[i]);
                        System.out.println(names[i] + " lost their wager of " + wagers[i] + "!\n"); 
                    }
                }
            }
            System.out.println("End of Round " + roundCount + "!\n");
            System.out.println("Scores:");
            System.out.println("----------------------------------------------");
            printScores(scores);  
            System.out.println("----------------------------------------------");          
        }
        int winnerIndex = 0;
        for (int i = 1; i < 4; i++) {
            if (scores.get(names[i]) > scores.get(names[winnerIndex])) {
                winnerIndex = i;
            }
        }
        System.out.println("Game Over!");
        System.out.println("Congratulations to " + names[winnerIndex] + " for winning the game!");
        
    }
}
