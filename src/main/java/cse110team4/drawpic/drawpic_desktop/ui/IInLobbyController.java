package cse110team4.drawpic.drawpic_desktop.ui;

import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_core.player.NormalLobbySettings.JudgeSetting;


public interface IInLobbyController {

	//Lobby is the lobby 
	public void leaveLobby();
	
	//returns true if game can be started (4 player requirement)
	//else returns false
	public void startGame();
	
	//JudgeSetting can only be from the JudgeSetting enum
	//(RANDOM, HOST_ONLY, IN_ORDER)
	public void changeSettingJudgeMode(JudgeSetting setting);
	
	//num is number of rounds per game
	//returns true if valid number of rounds (not less than number of wins)
	public void changeSettingNumRounds(int num);
	
	//num is the number of wins for a player to win the game
	//returns true if valid (not more than number of rounds)
	public void changeSettingNumWins(int num);
	
	//time is the time per draw phase (in seconds)
	public void changeSettingDrawTime(int time);
	
}
