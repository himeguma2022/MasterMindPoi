/* Class to help run a game for PuzzleCode.java. Assigns the right answers and vaildates player input.
*
*@Hubert Huang
*@12/15/2020
*
*/
import java.util.Random;//Needed for computer to give a correct number
import java.util.Scanner;//Allows program to take in user input
public class AttemptRecord {//List variables that will be used
    public static int Attempt;//Attempt number
    private static int [] UserGuess = new int [4];
    private static int [] RandomGuess = new int [4];//User's guess
    public static int CorrectPosition;//Number of digits in user's guess that are in the correct space
    public static int CorrectNumbers;//Number of digits the user's guess share with the correct number
    private static int [] Correct = new int [4];//Correct number
    public static void main(String [] args){
        for(int i = 0; i < 100; ++i){
            Correct = getCorrectSet();
            for(int index = 0; index < Correct.length; ++index){
                System.out.print(Correct[index]+"\t");
            }
            System.out.println(" ");
        }
        for(int i = 0; i < 100; ++i){
            UserGuess = RandomGuess();
            for(int index = 0; index < UserGuess.length; ++index){
                System.out.print(UserGuess[index]+"\t");
            }
            System.out.println(" ");
        }
    }
    public static void setFirstAttmpt(){//Only used to for the first attempt only
        Correct = getCorrectSet();//Find correct number
        UserGuess = getGuess();//Find user guess through user input
        //UserGuess = RandomGuess();
        CorrectPosition = getCorrectPositions();//Evaluated within the class
        CorrectNumbers = getCorrectNumbers();//Evaluated within the class
    }
    public static void setAttmpt(){//Used for later attempts after the first
        UserGuess = getGuess();
        //UserGuess = RandomGuess();
        CorrectPosition = getCorrectPositions();
        CorrectNumbers = getCorrectNumbers();
    }
    
    public static int getCorrectNumbers() {//Determine the digits in the guess that are in the correct answer
        int CorrectNumbers = 0;
        for(int index=0;index<Correct.length;++index){
        for(int jndex=0;jndex<Correct.length;++jndex){
        if(UserGuess[index]==Correct[jndex]){
            ++CorrectNumbers;
        }
    }
        }
        return CorrectNumbers;
    }

    public static int[] getCorrectSet() {//Determine a set of numbers for the correct answer
       for(int index = 0; index<Correct.length; ++index){
        boolean Match;
           do{
               Random ChooseNumber = new Random();//Choose a random number
               Correct[index] = ChooseNumber.nextInt(10);//Assign it into an array
               Match = false;//Preset to say there is no matching digit
               for(int jndex = 0; jndex<Correct.length; ++jndex){//Check that the randomize number in that index isn't equal to the other numbers in the array.
                   if(jndex!=index&&Correct[index]==Correct[jndex]){//jndex is used to compare values in the array with index
                       Match = true;
                    }
                }          
             
            }
       while(Match);//Reassigns value if there's a matching digit
       
        }
   for(int index = 0; index<Correct.length;++index){
   switch(RandomGuess[index]){//Convert every 10 in the array into a zero
    case 10:
    Correct[index] = 0;
    break;
   }
}

   return Correct; 
}
           public static int[] RandomGuess() {//Determine a set of numbers for the correct answer
            for(int index = 0; index<RandomGuess.length; ++index){
             boolean Match;
                do{
                    Random ChooseNumber = new Random();//Choose a random number
                    RandomGuess[index] = ChooseNumber.nextInt(10);//Assign it into an array
                    Match = false;//Preset to say there is no matching digit
                    for(int jndex = 0; jndex<RandomGuess.length; ++jndex){//Check that the randomize number in that index isn't equal to the other numbers in the array.
                        if(jndex!=index&&RandomGuess[index]==Correct[jndex]){//jndex is used to compare values in the array with index
                            Match = true;
                        }
                    }          
                 
                }
           while(Match);//Reassigns value if there's a matching digit
            }
       
       for(int index = 0; index<RandomGuess.length;++index){
       switch(RandomGuess[index]){//Convert every 10 in the array into a zero
        case 10:
        RandomGuess[index] = 0;
        break;
       }
    }
    for(int index =0;index<RandomGuess.length;index++){
        System.out.print(RandomGuess[index]);
    }
    System.out.print("\n");
       return RandomGuess; 
}
    public static int[] getGuess(){//Obtain guess from user input
        Scanner Input = new Scanner(System.in);
        String Guess;
        int [] UserGuess = new int[4];
        boolean valid;
        do{
        Guess = Input.next();
        valid = false;//Assume guess isn't valid first
        switch(Guess.length()){//Check that guess length is exactly four characters
            case 4:
            char check;
            int index;
            for(index =0; index<4;++index){
                boolean validChar = false;//Check for any illegal charcters in the guess
                check = Guess.charAt(index);
                switch(check){
                    case '0':
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                    case '5':
                    case '6':
                    case '7':
                    case '8':
                    case '9':
                    validChar = true;
                    break;
                    default:
                    validChar=false;
                }
                if(validChar){//Check for repeats in the guess if all four charcters are legal                    
                boolean [] Repeat = {false, false, false, false};//Assign a boolean array of all falses
                    for (int jndex = 0; jndex < index; jndex++){//Compare values within the array with each other
                    if(check==Guess.charAt(jndex)&&jndex!=index){
                        Repeat[index] = true;
                    } 
                }
            if(Repeat[index]){
                tryAgain();
                index = 5;//Allows users to do another guess when a repeating digit is encountered
            } 
        } else{
            tryAgain();
            index = 5;//Allows users to do another guess when an illegal character is encountered
        }
            }
            switch(index){
                case 4:
                valid = true;//Final index should only be four after the check is completed
                break;
                default:
                tryAgain();
            }
            break;
            default:
            tryAgain();
        }
    } while(!valid); 
        for(int index =0; index<4;++index){//Turn user guess into an array of integers 
            UserGuess[index] = Integer.parseInt(String.valueOf(Guess.charAt(index)));
        }
        return UserGuess;      
        
    }
    public static void tryAgain(){
        System.out.println("Please enter a valid guess");//Asks user to input a valid guess
    }
    public static int getCorrectPositions(){//Determine the correct digits guessed
        int correctPosition = 0;
        for(int index = 0;index<Correct.length;++index){
            if(Correct[index] == UserGuess[index]){
                ++correctPosition;
            }
        }
        return correctPosition;
    }


}
