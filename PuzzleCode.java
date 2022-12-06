/* Needs AttemptRecord.java to run properly. Allows user to play Bulls and Cows. Includes a 
* menu for the user to navigate around to learn some tips to play the game more smoothly. 
* Doesn't allow giving up.
*
*@Hubert Huang
*@12/15/2020
*
*/
import java.util.Scanner;//Imports scanner needed for recieving user inputs
public class PuzzleCode {//Main driver class
    public static void main(String [] args){
        Menu();//Takes user to menus
        }
    public static void Menu(){//Menu and its functionality
        System.out.println("Press 's' to start");
        System.out.println("Press 't' to view tips and how to play");
        System.out.println("Press 'q' to quit");
        Scanner input = new Scanner(System.in);
        boolean MenuMode = true;//Is user in menu or not
        while(MenuMode){
        String UserChoice = input.next();
        switch (UserChoice){//Have user choose where to go using keyboard
            case "s":
            MenuMode = false;
            GameStart();//Starts game
            break;
            case "t":
            help();
            break;
            case "q":
            System.exit(0);
            }
        }
    }
    public static void GameStart(){
        System.out.println("Guess the four digit number that has no repeating digits the computer is thinking of.");
        System.out.println("This is attempt #1");//Start with the first attempt
        AttemptRecord.setFirstAttmpt();//Uses a method outside this class
        AttemptRecord.Attempt = 1;//Set number of attempt to one
        AttemptEvaluation();//Check guess with correct answer using this method
    }
    public static void AttemptEvaluation(){
        switch(AttemptRecord.CorrectPosition){//Only allows win condition when user guesses the number correctly
            case 4:
            System.out.println("It took you " + AttemptRecord.Attempt + " tries to guess the secret number!");//Tells how many guesses it took the user to find the correct answer
            EndGame();//Takes user to end of game choices
            break;
            default:
            System.out.print("Your guess contains "+AttemptRecord.CorrectNumbers);//Display guess results
            System.out.println(" numbers that are in the correct answer");
            System.out.print("Out of the numbers correct numbers in your guess, ");
            System.out.println(AttemptRecord.CorrectPosition+" were in the correct positions.");
            ++AttemptRecord.Attempt;//Retrieves values from other class and adds one to the total number of attempts taken
            Continue(AttemptRecord.Attempt);//User continues the game here
        }
    }
    public static void Continue(int guess){//User continues guessing with this function
        System.out.println("Attempt #: "+guess);
        AttemptRecord.setAttmpt();
        AttemptEvaluation();
    }
    public static void EndGame(){//Asks whether or not the user wants to play again
        System.out.println("Play again? Y/N");
        Scanner input = new Scanner(System.in);
        String UserChoice = input.next();
        boolean ExitEnd = false;
        while(!ExitEnd){
        switch(UserChoice){
            case "Y":
            ExitEnd = true;
            GameStart();//User plays again
            break;
            case "N":
            ExitEnd = true;//User returns to menu
            Menu();
            default:
            UserChoice = input.next();
        }
    }
    }
    public static void help(){//Help for User for playing this game, gives some advice to beat the game more quickly.
        System.out.println("The game will come up with a four digit number with no repeating digits");
        System.out.println("Your goal is to guess the correct four digit number with no repating digits");
        System.out.println("Remeber that a valid guess has to have four digits, no characters that aren't 0 through 9, or contain duplicate digits.");
        System.out.println("Invalid guesses include: ");
        System.out.println("1323, 9544, or 123");
        System.out.println("The goal is to guess the number in the fewest number of guesses.");
        System.out.println("It may be helpful to use a pencil and paper to keep track of your guesses.");
        System.out.println("Lastly, don't expect to get a guess very quickly, it takes AI about 7 or 8 guesses to find the correct number.");
        System.out.println("But guessing every possible combination might take you a long time since theres about 5040 different valid combinations");
        System.out.println("Fortuneatly, the game tells you if you're getting closer to the correct answer or not.");
        System.out.println("Type something and press enter to return to menu");
        Scanner input = new Scanner(System.in);//Waits when user is done reading to return to menu.
        String UserChoice = input.next();
        Menu();//Returns to menu
    }
    
       
}
