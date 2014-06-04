package cse110team4.drawpic.drawpic_desktop.event.lobby;

import cse110team4.drawpic.drawpic_core.player.Lobby;
import cse110team4.drawpic.drawpic_core.player.LobbySettings;
import cse110team4.drawpic.drawpic_desktop.event.listener.LobbyListener;

public class LobbySettingsChangedEvent extends LobbyEvent {

	public LobbySettingsChangedEvent(Lobby lobby) {
		super(lobby);
	}
	
	/**
	 * Gets the currently set lobby settings object
	 * @return The LobbySettings object currently set
	 */
	public LobbySettings getSettings() {
		return getLobby().getSettings();
	}

	@Override
	public void notify(LobbyListener listener) {
		listener.settingsChanged(this);
	}

}
