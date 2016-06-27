package aiexample.logic.example;

import java.awt.geom.*;

import aiexample.logic.AILogicInterface;
import aiexample.logic.AIOutputInterface;

public class AILogicExample implements AILogicInterface 
{
	public class Player {
		public int id;
		public Point2D.Float position = new Point2D.Float();
		public String currentAction = null;
	}
	
	AIOutputInterface outputInterface;
	
	// In this example, AIs are represented with the same information as players
	Player[] players;
	//Player[] ais;
	
	@Override
	public void SetOutputInterface(AIOutputInterface output) 
	{
		this.outputInterface = output;
	}
	
	@Override
	public void SetPlayerCount(int count) 
	{
		this.players = new Player[count];
		//this.ais = new Player[count];	// Each player has a matching AI in this example
		
		for (int i = 0; i < count; ++i)
		{
			Player p = new Player();
			p.id = i;
			this.players[i] = p;
			
//			Player ai = new Player();
//			ai.id = i;
//			this.ais[i] = ai;
		}
	}
	
	@Override
	public void UpdatePlayerPosition(int id, float x, float y, float z) 
	{
		Player p = GetPlayer(id);
		p.position.setLocation(x, z);
	}

	@Override
	public void UpdatePlayerAction(int id, String action) {
		Player p = GetPlayer(id);
		p.currentAction = action;
	}
	
	
	Player GetPlayer(int id)
	{
		Player p = this.players[id];
		return p;
	}
	
	public void ReasoningTick()
	{
		for (int i = 0; i < this.players.length; ++i) 
		{
			Player p = this.players[i];
			
			// The AI just looks at it's corresponding player and tries to position itself
			// halfway between the player and origin
			float x = p.position.x * 0.5f;
			float y = p.position.y * 0.5f;
			
			outputInterface.MoveTo(i, x, y);
		}
	}

}
