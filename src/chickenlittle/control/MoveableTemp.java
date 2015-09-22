package chickenlittle.control;

/**
 * Class for testing input & app window display.
 */
public class MoveableTemp {
	private int x = 0;
	private int y = 0;
	
	public void moveX(int x){ this.x += x; }
	public void moveY(int y){ this.y += y; }
	public int getX(){ return x; }
	public int getY(){ return y; }
}
