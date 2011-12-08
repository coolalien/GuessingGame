package uk.co.coolalien.GuessingGame;
import java.util.Random;

import uk.co.coolalien.GuessingGame.R;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.DialogInterface;
import android.view.View;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

public class GuessingGameActivity extends Activity {
	Integer numberToGuess;
	String guessedNumber;
	Integer guessedNumberInt = 0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        reset();
        go();
    }

    public void reset() {
    	numberToGuess = getRandomInteger(10);		//Gets a random number between 1 and number in brackets
    	 Toast.makeText(getApplicationContext(), "Random Number Generated", Toast.LENGTH_SHORT).show();
	}
    
    public void playAgain() {
    	reset();
    	go();
    }
    
    public void go() {
        final Button button = (Button) findViewById(R.id.check);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // Perform action on clicks
                guessingGame();
            }
        });
	}
    
    public void guessingGame(){
			EditText et = (EditText)findViewById(R.id.guessedNumberText);
			guessedNumber = et.getText().toString();
			guessedNumberInt = Integer.parseInt(guessedNumber);
			AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
			final AlertDialog.Builder alertbox2 = new AlertDialog.Builder(this);
			if(guessedNumberInt > 10 || guessedNumberInt < 1){	//if guessed number is out of the range 1-10
				alertbox.setMessage("Number needs to be between 1 and 10");
				alertbox.setNeutralButton("Ok", null); { 
	            }
				alertbox.show();
			}else if(numberToGuess.equals(guessedNumberInt)){	//if guessed number is correct
				alertbox.setMessage("Correct");
				alertbox.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {
	                	alertbox2.setMessage("Would You like to play again?");
	    				alertbox2.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	    	                public void onClick(DialogInterface arg0, int arg1) {	//reset if yes is clicked
	    	                    reset();
	    	                }
	    	            });
	    	            alertbox2.setNegativeButton("No", new DialogInterface.OnClickListener() {
	    	                public void onClick(DialogInterface arg0, int arg1) {	//quits if no is clicked
	    	                	System.exit(0);
	    	                }
	    	            });
	    	            alertbox2.show();
	                }
	            });
				alertbox.show();
			}else if(numberToGuess < guessedNumberInt){	//if guessed number is too high
				alertbox.setMessage("Number is lower");
				alertbox.setNeutralButton("Ok", null); { 
	            }
				alertbox.show();
			}else if(numberToGuess > guessedNumberInt){	//if guessed number is too low
				alertbox.setMessage("Number is higher");
				alertbox.setNeutralButton("Ok", null); { 
	            }
				alertbox.show();
			}
			et.setText("");	//clears the textbox
		}
    




	public Integer getRandomInteger(Integer max){
		
		Random rand = new Random();
		
		int number;
		
		number = rand.nextInt(max)+1;
		
		return new Integer(number);
	
	}
 

}