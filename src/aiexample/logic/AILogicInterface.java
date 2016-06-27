package aiexample.logic;

/**
 * A general interface that any AI can implement
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
