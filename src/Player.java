import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/*
 * POINT DATA DECLARED AT BOTTOM OF FILE!!! In Token Class
 */
@SuppressWarnings("serial")
class Player extends JPanel {

	private final int NUM_TOKENS = 4;
    	private int pid;// Player id
    	private Point p;// Player info,Starting point for pieces for each player
    	private Color color;
	private Color red = new Color(255, 0, 0);
	private Color blue = new Color(0, 0, 255);
	private Color green = new Color(0, 255, 0);
	private Color yellow = new Color(255, 255, 0);
	private Color[] col = {red,blue,green,yellow};
	private int offset;
	public Token[] t;
	public final ArrayList<Point> specialPointData;// Point Data for Special

    	public Player(int id) {

        	specialPointData = new ArrayList<Point>();

        	// Initialize Player Details
        	pid = id + 1;
        	switch (id) {
            	case 0:// player1
			p = new Point(60, 30);
			offset = 25;// Perfect
			color = Color.RED;
			break;
		    case 1:// player2
			p = new Point(514, 30);
			offset = 31;
			color = Color.BLUE;
			break;
		    case 2:// player3
			p = new Point(60, 485);
			offset = -1;
			color = Color.YELLOW;               
			break;
		    case 3:// player4
			p = new Point(514, 485);
			offset = 47;
			color = Color.GREEN;
			break;
		    default: // This should never happen
			p = new Point(60, 60);
			color = Color.RED;
		}
	    
		t = new Token[NUM_TOKENS];
		for (int i = 0; i < NUM_TOKENS; i++) {
			t[i] = new Token(i, p.x, p.y, getColor());
		}
	    
    	}

	public ArrayList<String> getAvailableTokens() {
		ArrayList<String> available = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			if (!t[i].isSafe()) {
				available.add(Integer.toString(i));
			}
		}
		return available;
	}

	public int getPID() {
		return pid;
	}

	public int getOffset() {
		return offset;
	}

	/*
	* Choose Player's Token to move with Error Bounds Checking
	*/
	public int chooseToken() {
		int tok = -1;
		ArrayList<String> available = getAvailableTokens();
		if (available.size() > 0) {
			while (tok > 3 || tok < 0) {
				String[] options = new String[available.size()];
				options = available.toArray(options);
				String x = (String) JOptionPane.showInputDialog(null,
						"Which Token Would You Like To Move? (WTWYLTM?)",
						"Parcheesi", JOptionPane.QUESTION_MESSAGE, null,
						options, autoSelectFreeToken());

				if (x == null) {
					tok = autoSelectFreeToken();
				} else {
					tok = Integer.parseInt(x);
				}
				// tok = Integer.parseInt(x);
			}
		}
		
		return tok;
	}

	public int autoSelectFreeToken() {
		int tok = -1;
		for (int k = 0; k < NUM_TOKENS; k++) {
			if (!t[k].isSafe()) {
				// Grab the first not safe token!
				tok = k;
			}
		}
		return tok;
	}

	public void setPoint(int x, int y) {
		p = new Point(x, y);
	}

	/*
	* Updates the Player info and tokens on board
	*/
	public void update(Graphics g) {
		g.setColor(color);
		g.fillRect(p.x, p.y, 310, 310);
		g.setColor(Color.BLACK);
		g.drawRect(p.x, p.y, 310, 310);
		g.drawString("Player " + pid, p.x + 9, p.y + 16);
		// Update Tokens
		for (int i = 0; i < NUM_TOKENS; i++) {
			t[i].draw(g);
		}

	}

	/*
	* Checks if Player has won. Returns a boolean representation of the condition
	*/
	public boolean hasWon() {
		for (int i = 0; i < 4; i++) {// Return False if any aren't safe
			if (!t[i].isSafe()) {
				return false;
			} else {
				System.out.println("Player:hasWon(): Player " + pid + " token "
						+ i + " is safe");
			}
		}
		System.out.println("Player: Player " + pid + " Has Won");
		return true;
	}

	/*
	* gets Player Color
	*/
	public Color getColor() {
		return color;
	}

	/*
	* Nested Token Object Class, each player has 4 tokens
	*/
	class Token {
		// Index Represents where a piece is on the board
		// Do not change SAFE or else
		private final int SAFE = 63;// Index for all Safe Tokens!
		private boolean lastEight;
		private int tokenSize;
		private int id;
		private int index;
		private Point pos;
		private Color c;
		
		// This constructor takes the players individual Corner Parameters so it
		// knows where each players nest is
		public Token(int i, int x, int y, Color col) {
			lastEight = false;
			tokenSize = 20;
			id = i;
			index = 0;
			pos = new Point(x, y);
			c = col;
		}

		// Draws a token, anywhere on the board we want to
		public void drawToken(Graphics g, int x, int y) {

		}

		public void draw(Graphics g) {

		}

		/*
		 * If the player has traversed the entire board, then we want to go down the
		 * players safe zone for a win! Returns -1 if false Returns any other positive
		 * value to represent how many spaces the user can traverse following the turn
		 */
		public int checkTraversal() {

		}

		/*
		 * gets the safe zone condition
		 */
		public boolean getSafeZone() {
			return lastEight;
		}

		/*
		 * sets the safe zone condition
		 */
		public void setSafeZone(boolean b) {
			lastEight = b;
		}

		/*
		 * Checks to see if the token is Safe, or not
		 */
		public boolean isSafe() {

		}


		// Getter and Setter
		public void setX(int xPos) {
			pos.x = xPos;
		}

		public int getX() {
			return pos.x;
		}

		public void setY(int yPos) {
			pos.y = yPos;
		}

		public int getY() {
			return pos.y;
		}

		public int getWidth() {
			return tokenSize;
		}

		public int getHeight() {
			return tokenSize;
		}
		
	}

}
