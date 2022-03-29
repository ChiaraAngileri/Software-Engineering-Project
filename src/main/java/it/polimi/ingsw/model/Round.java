package it.polimi.ingsw.model;

import it.polimi.ingsw.model.gameBoard.Cloud;

import java.util.*;

public class Round {

    private Player currentPlayer;
    private ArrayList<Player> playingOrder;
    private HashMap<Player, Assistant> playerPriority;

    /**
     * Default constructor
     */
    public Round() {

    }

    public HashMap<Player, Assistant> getPlayerPriority() {
        return new HashMap<>(playerPriority);
    }

    /**
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * @return the player who played the Assistant card with the lowest priority number
     */
    public Player getFirstPlayer() {
        // TODO
        return null;
    }

    /**
     * Manages the round's execution
     */
    public void play() {

    }

    /**
     * Manages the planning phase of the round
     */
    private void planning() {

    }

    /**
     * Manages the action phase of the round
     */
    private void action() {

    }

    /**
     * Refills the inputted cloud card with students
     * @param cloud to be filled
     */
    private void fillCloud(Cloud cloud) {

    }

    /**
     * Manages the first part of the action phase
     * (moving a certain number of students)
     */
    private void phaseOne() {

    }

    /**
     * Manages the second part of the action phase
     * (move Mother Nature and check the island groups' state)
     */
    private void phaseTwo() {

    }

    /**
     * Manages the third part of the action phase
     * (choice of the cloud card to empty)
     */
    private void phaseThree() {

    }

    public void updateProfessors() {
    }
}