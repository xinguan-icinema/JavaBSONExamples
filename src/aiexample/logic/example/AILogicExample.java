package aiexample.logic.example;

import java.awt.geom.*;

import aiexample.logic.AILogicInterface;
import aiexample.logic.AIOutputInterface;

/**
 * An example implementation of AILogicInterface. This just stores the latest state of
 * each player and performs some simple 'reasoning' in ReasoningTick()
 * @author som
 *
 */
public class AILogicExample implements AILogicInterface 
{
	/** A simple class to store the current state of Players in the game **/
	public class Player {
		public int id;
		public Point2D.Float position = new Point2D.Float();
		public String currentAction = null;
	}
	
	AIOutputInterface outputInterface;
	
	Player[] players;
	
	@Override
	public void SetOutputInterface(AIOutputInterface output) 
	{
		this.outputInterface = output;
	}
	
	@Override
	public void SetPlayerCount(int count) 
	{
		String s = String.format("Player count has been reset to %d. This probably means you just started the Unity scene", count);
		System.out.println(s);
		
		this.players = new Player[count];
		
		for (int i = 0; i < count; ++i)
		{
			Player p = new Player();
			p.id = i;
			this.players[i] = p;
		}
	}
	
	@Override
	public void UpdatePlayerPosition(int id, float x, float y, float z) 
	{
		Player p = this.players[id];
		p.position.setLocation(x, z);
	}

	@Override
	public void UpdatePlayerAction(int id, String action) {
		Player p = this.players[id];
		p.currentAction = action;
	}
	
	
	/**
	 * Example of how the AI can look at the state of the world,
	 * perform some reasoning, and send commands back to Unity
	 */
	public void ReasoningTick()
	{
		for (int i = 0; i < this.players.length; ++i) 
		{
			Player p = this.players[i];
			
			// The AI just looks at it's corresponding player and tries to position itself
			// halfway between the player and origin
			float x = p.position.x * 0.5f;
			float y = p.position.y * 0.5f;
			
			// Tell the AI character in Unity to move to this position
			outputInterface.MoveTo(i, x, y);
		}
	}

}
