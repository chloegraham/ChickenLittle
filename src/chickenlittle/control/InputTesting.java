package chickenlittle.control;

import chickenlittle.control.Action.Actions;

/**
 * User requests sent to game logic, game logic updates game state, sends updates to client
 * For testing Actions & Application Window displays. 
 * @author Thorbukirs
 */
public class InputTesting {
	MoveableTemp obj = new MoveableTemp();
	
	public MoveableTemp getMoveable(){
		return obj;
	}
	
	public void performAction(int ordinal){
		if (Actions.NORTH.ordinal() == ordinal){ obj.moveY(-1); }
		else if (Actions.SOUTH.ordinal() == ordinal){ obj.moveY(1); }
		else if (Actions.WEST.ordinal() == ordinal){ obj.moveX(-1); }
		else if (Actions.EAST.ordinal() == ordinal){ obj.moveX(1); }
	}
	
}
