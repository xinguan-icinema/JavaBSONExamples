package aiexample.logic;

/**
 * A general interface that any AI can implement, with common functions to input information
 * about the world and output commands via an AIOutputInterface
 * @author som
 *
 */
public interface AILogicInterface 
{
	void SetOutputInterface(AIOutputInterface output);
	void SetPlayerCount(int count);
	
	void UpdatePlayerPosition(int id, float x, float y, float z);
	void UpdatePlayerAction(int id, String action);
}
