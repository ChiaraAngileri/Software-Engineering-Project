package it.polimi.ingsw.listeners;

import it.polimi.ingsw.model.enumerations.GameMode;
import it.polimi.ingsw.model.enumerations.GameState;
import it.polimi.ingsw.model.phases.Phase;
import it.polimi.ingsw.network.message.serverToclient.CurrentPhaseMessage;
import it.polimi.ingsw.network.message.serverToclient.GenericMessage;
import it.polimi.ingsw.util.Constants;
import it.polimi.ingsw.view.VirtualView;

import java.beans.PropertyChangeEvent;
import java.util.Map;

public class PhaseListener extends Listener {

    public PhaseListener(VirtualView virtualView) {
        super(virtualView);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Map.Entry<GameState, GameMode> preferences = (Map.Entry<GameState, GameMode>) evt.getNewValue();
        GameState gameState = preferences.getKey();
        GameMode gameMode = preferences.getValue();
        // TODO check gameState to determine whether to send to all players or just the current player
        virtualView.sendAll(new CurrentPhaseMessage(Constants.getPhaseInstructions(gameState, gameMode)));
    }
}