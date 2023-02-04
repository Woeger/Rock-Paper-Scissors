import java.util.*;

import javax.lang.model.util.ElementScanner6;

public class RPS
{
    public static void main(String args[])
    {
        //Initalise score for a new game
        int playerScore = 0;
        int computerScore = 0;

        //While loop, condition being that either the player or computer has not won (reached a score of 2)
        while (playerScore != 2 && computerScore != 2)
        {
            //Inform player of the current score
            System.out.println("The score is currently... Player:" + playerScore + " Computer:" + computerScore);

            //Generate new options for player and computer
            String playerOption = playerOption();
            String computerOption = computerOption();

            //Flag to check if the player has won or not
            Boolean playerWin = false;

            //If else statment to check what option a player has picked, if that option wins and updating the win flag accordingly. .equals used to compare strings.
            if (playerOption.equals("rock"))
            {
              if (computerOption.equals("scissors"))
              {
                playerWin = true;
              }
            }

            else if (playerOption.equals("paper"))
            {
              if (computerOption.equals("rock"))
              {
                playerWin = true;
              }

            }

            else if (playerOption.equals("scissors"))
            {
              if (computerOption.equals("paper"))
              {
                playerWin = true;
              }
            }

            //Else, we know the input isn't valid so can display an error message and return to the start of the while loop
            else
            {
              System.out.println("Error: Please enter a valid option.");
              continue;
            }

            //Informing the player on what option was picked by the computer
            System.out.println("The computer picked... " + computerOption);
            
            //Check if the player won
            if (playerWin == true)
            {
                playerScore++;
                System.out.println("The player wins this round!");
            }

            //Equal options means a tie, no win is updated
            else if (playerOption.equals(computerOption))
            {
            System.out.println("Its a tie!");
            }

            //Otherwise we know the computer won
            else
            {
                computerScore++;
                System.out.println("The computer wins this round!");
            }
            
        }

        //If the players score is higher, display the players win message
        if (playerScore > computerScore)
        {
            System.out.println("The player wins this game! The final score was... Player:" + playerScore + " Computer:" + computerScore);
        }

        //Otherwise the computer has won, display the correct win message
        else
        {
            System.out.println("The computer wins this game! The final score was... Player:" + playerScore + " Computer:" + computerScore); 
        }
    }

    //Method for taking in the players choice of option
    public static String playerOption()
    {
        //Inform player that program is waiting for input
        System.out.println("Please select an option from: rock, paper or scissors.");
        //Create scanner object for player input
        Scanner scannerIn = new Scanner(System.in);
        //Take input and format to lower case to allow for comparisons
        String playerInput = scannerIn.next();
        String playerOption = playerInput.toLowerCase();
        return playerOption;
    }

    //Method for randomly generating an option for the computer
    public static String computerOption()
    {
        //Generate random interger between 1 and 3 to decide on option
        Random r = new Random();
        int option =  r.nextInt((3 - 1) + 1) + 1;

        //Switch case, returning rock, paper or scissor as a string depending on the number generated
        switch(option)
        {
            case 1:
              return "rock";
            case 2:
              return "paper";
            case 3:
              return "scissors";
            default:
              //Null return to avoid compile errors with returning a string, however this block should never be executed
              return null;
        }
    }

    public static int computerWeightedOption(int[] $playerChoices)
    {

      //Create and initalise an array of type double for the weights of all three options, double used to be the most accurate
      double[] weights = {0.0, 0.0, 0.0};
      int[] options = {1, 2, 3};

      //Check to see if weighting needs to be taken into effect, if not we pick a random number without weights
      if ($playerChoices.length >= 10)
      {

        //Initalise count of each option in the array
        int[] optionCount = {0, 0, 0};

        //For loop to count how many of each option was picked in the last ten throws (assuming 1 = rock, 2 = paper, 3 = scissors)
        for (int i = 0; i < 10; i++)
        {

          int currentElement = $playerChoices[i];

          switch(currentElement)
          {
              case 1:
                optionCount[0]++;
                break;
              case 2:
                optionCount[1]++;
                break;
              case 3:
                optionCount[2]++;
                break;
          }

        }

        //Calculate base weights by dividing count by 10 (size of array), then work out the bounded weight (25 > 50) by multiplying that probability by 25, and adding 25 on top.
        for (int i = 0; i < 3; i++)
        {
          weights[i] = 25.0 + (25.0 * (optionCount[i] / 10.0));
        }

        //For loop to calculate random selection from our options array based on weights calculated. Total weights add up to 100, so multiply our random value by that.
        int i = 0;
        for (double r = Math.random() * 100; i < 3; ++i)
        {
          r -= weights[options[i] - 1];
          if (r <= 0.0) break;
        }

        return options[i];
        
      }

      //Otherwise use standard random number generation + return
      else
      {

        Random r = new Random();
        int option =  r.nextInt((3 - 1) + 1) + 1;
        return option;

      }

    }
}