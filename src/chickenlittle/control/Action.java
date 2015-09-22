package chickenlittle.control;

import java.awt.event.KeyEvent;

public class Action {
	
	/**
	 * Prototype -- subject to code-breaking changes. Please use with caution.
	 * 
	 * Possible actions by player. Send through network connection using ordinal, holds key event.
	 * Any new action added here will be available to the server immediately. Make sure to add the key to the readme file.
	 * Do not overwrite any existing keys (check in the README file), Do not use CTRL or ALT at all.
	 * 
	 * INSTRUCTIONS FOR USE:
	 * Import the Actions enum to the class that needs to use it:
	 * 		import chickenlittle.control.Action.Actions;
	 * Use the server input to retrieve the action type. Input will be an int.
	 * Do not try to hard code in an int! Always use .ordinal()
	 * Examples:
	 * 	if (Actions.NORTH.ordinal() == input){ System.out.println("Valid input"); }
	 * 
	 *	Switch (input){
	 * 		case Actions.NORTH.ordinal():
	 * 			System.out.println("Valid input");
	 * 			break;
	 *	}
	 * 
	 * @author Thorbukirs
	 */
	public enum Actions {
		// Add extra events here...
		INTERACT (KeyEvent.VK_E),
		QUICKSAVE (KeyEvent.VK_Q),
		/* Adding new events below this line will increase the number of unnecessary checks.
		 * Better to place above instead. */
		NORTH (KeyEvent.VK_W),
		EAST (KeyEvent.VK_D),
		SOUTH (KeyEvent.VK_S),
		WEST (KeyEvent.VK_A);

		private int keyCode;			// KeyEvent.VK_EVENT
		
		Actions(int keyCode){
			this.keyCode = keyCode;
		}
		
		public int getKeyCode(){
			return keyCode;
		}
		
	}

}
