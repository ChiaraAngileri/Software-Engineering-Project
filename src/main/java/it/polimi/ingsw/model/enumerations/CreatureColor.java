package it.polimi.ingsw.model.enumerations;

/**
 * Enumeration of the valid colors.
 */
public enum CreatureColor {

    GREEN(0),
    RED(1),
    YELLOW(2),
    PINK(3),
    BLUE(4);

    private final int index;

    CreatureColor(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
