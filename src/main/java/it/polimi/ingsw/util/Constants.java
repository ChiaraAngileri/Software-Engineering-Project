package it.polimi.ingsw.util;

import it.polimi.ingsw.model.characters.Character;
import it.polimi.ingsw.model.enumerations.GameMode;
import it.polimi.ingsw.model.enumerations.GameState;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Constants {

    private static final String SEPARATOR = "\n============================================================================\n";
    
    // Message formats CLIENT -> SERVER
    private static final String LOGIN_FORMAT = "login [nickname] [number of players (2 / 3)] [game mode (0 = EASY / 1 = EXPERT)]";
    private static final String PREPARE_FORMAT = "wizard [DRUID / KING / WITCH / SENSEI]";
    private static final String PLANNING_FORMAT = "assistant [1..10]";
    private static final String MOVE_STUDENT_FORMAT = "movestudent [color from entrance (GREEN / RED / YELLOW / PINK / BLUE)] [destination (0 = hall / 1..12 = island)]";
    private static final String MOVE_MOTHER_NATURE_FORMAT = "movemothernature [number of steps]";
    private static final String PICK_CLOUD_FORMAT = "pickcloud [cloud id]";
    
    private static final String CHARACTER_INFO_FORMAT = "characterinfo [character id (1..12)]";
    private static final String CHARACTER_ONE_FORMAT = "character 1 [color from character] [island id (1..12)]";
    private static final String CHARACTER_TWO_FORMAT = "character 2";
    private static final String CHARACTER_THREE_FORMAT = "character 3 [island group index]";
    private static final String CHARACTER_FOUR_FORMAT = "character 4";
    private static final String CHARACTER_FIVE_FORMAT = "character 5 [island group index]";
    private static final String CHARACTER_SIX_FORMAT = "character 6";
    private static final String CHARACTER_SEVEN_FORMAT = "character 7 [color from character] [color from entrance]";
    private static final String CHARACTER_EIGHT_FORMAT = "character 8";
    private static final String CHARACTER_NINE_FORMAT = "character 9 [color (GREEN / RED / YELLOW / PINK / BLUE)]";
    private static final String CHARACTER_TEN_FORMAT = "character 10 [color form hall] [color from entrance]";
    private static final String CHARACTER_ELEVEN_FORMAT = "character 11 [color from character]";
    private static final String CHARACTER_TWELVE_FORMAT = "character 12 [color (GREEN / RED / YELLOW / PINK / BLUE)]";
    
    private static final String QUIT_FORMAT = "quit";

    private static String charactersDrawn = "- Drawn characters:";

    private static String playCharacters = "- Play a character";
    
    // Phase instructions
    private static String expertCharacterAction(GameMode gameMode) {
        return gameMode.equals(GameMode.EXPERT) ? "- Ask for characters' info\n\t" + CHARACTER_INFO_FORMAT : "";
    }

    private static final String LOGIN_PHASE_INSTRUCTIONS = SEPARATOR +
            "LOGIN PHASE\n" +
            "The actions you can take are the following:\n" +
            "- Login\n\t" + LOGIN_FORMAT + "\n" +
            "- Quit\n\t" + QUIT_FORMAT;

    private static final String PREPARE_PHASE_INSTRUCTIONS = SEPARATOR +
            "PREPARE PHASE\n" +
            "The actions you can take are the following:\n" +
            "- Choose a wizard\n\t" + PREPARE_FORMAT + "\n" +
            "- Quit\n\t" + QUIT_FORMAT;

    private static String planningPhaseInstructions(GameMode gameMode) {
        return SEPARATOR +
                "PLANNING PHASE\n" +
                "The actions you can take are the following:\n" +
                "- Play an assistant\n\t" + PLANNING_FORMAT + "\n" +
                expertCharacterAction(gameMode) + "\n" +
                charactersDrawn + "\n" +
                "- Quit\n\t" + QUIT_FORMAT;
    }

    private static String moveStudentPhaseInstructions(GameMode gameMode) {
        return SEPARATOR +
                "MOVE STUDENT PHASE\n" +
                "The actions you can take are the following:\n" +
                "- Move a student from your entrance to the hall or one of the islands\n\t" + MOVE_STUDENT_FORMAT + "\n" +
                expertCharacterAction(gameMode) + "\n" +
                playCharacters + "\n" +
                "- Quit\n\t" + QUIT_FORMAT;
    }

    private static String moveMotherNaturePhaseInstructions(GameMode gameMode) {
        return SEPARATOR +
                "MOVE MOTHER NATURE PHASE\n" +
                "The actions you can take are the following:\n" +
                "- Move mother nature a certain amount of steps\n\t" + MOVE_MOTHER_NATURE_FORMAT + "\n" +
                expertCharacterAction(gameMode) + "\n" +
                playCharacters + "\n" +
                "- Quit\n\t" + QUIT_FORMAT;
    }

    private static String pickCloudPhaseInstructions(GameMode gameMode) {
        return SEPARATOR +
                "PICK CLOUD PHASE\n" +
                "The actions you can take are the following:\n" +
                "- Choose a cloud from which to get students\n\t" + PICK_CLOUD_FORMAT + "\n" +
                expertCharacterAction(gameMode) + "\n" +
                playCharacters + "\n" +
                "- Quit\n\t" + QUIT_FORMAT;
    }

    public static String getPhaseInstructions(GameState gameState, GameMode gameMode) {
        return switch (gameState) {
            case LOBBY_PHASE -> LOGIN_PHASE_INSTRUCTIONS;
            case PREPARE_PHASE -> PREPARE_PHASE_INSTRUCTIONS;
            case PLANNING_PHASE -> planningPhaseInstructions(gameMode);
            case MOVE_STUDENT_PHASE -> moveStudentPhaseInstructions(gameMode);
            case MOVE_MOTHER_NATURE_PHASE -> moveMotherNaturePhaseInstructions(gameMode);
            case PICK_CLOUD_PHASE -> pickCloudPhaseInstructions(gameMode);
            default -> "";
        };
    }

    public static void setDrawnCharacters(ArrayList<Character> characters) {
        for(Character character : characters) {
            if(character.getCharacterID() != 0) {
                charactersDrawn = charactersDrawn + " " + character.getCharacterID();
            }
        }
    }

    public static String getDrawnCharacters() {
        return charactersDrawn;
    }

    //set in game
    public static void setPlayCharacters(ArrayList<Character> characters) {
        StringBuilder characterDrawn = new StringBuilder();

        for(Character character : characters) {
            if(character.getCharacterID() != 0) {
                characterDrawn.append("\n\t").append(getCharacterFormat(character.getCharacterID()));
            }
        }

        playCharacters = playCharacters + characterDrawn;
    }

    private static String getCharacterFormat(int characterID) {
        return switch (characterID) {
            case 1 -> CHARACTER_ONE_FORMAT;
            case 2 -> CHARACTER_TWO_FORMAT;
            case 3 -> CHARACTER_THREE_FORMAT;
            case 4 -> CHARACTER_FOUR_FORMAT;
            case 5 -> CHARACTER_FIVE_FORMAT;
            case 6 -> CHARACTER_SIX_FORMAT;
            case 7 -> CHARACTER_SEVEN_FORMAT;
            case 8 -> CHARACTER_EIGHT_FORMAT;
            case 9 -> CHARACTER_NINE_FORMAT;
            case 10 -> CHARACTER_TEN_FORMAT;
            case 11 -> CHARACTER_ELEVEN_FORMAT;
            case 12 -> CHARACTER_TWELVE_FORMAT;
            default -> "";
        };
    }

    // characters descriptions
    private static final String CHARACTER_ONE_DESCRIPTION = """
            Take 1 Student from this card and place it on
            an Island of your choice
            """;

    private static final String CHARACTER_TWO_DESCRIPTION = """
            During this turn, you take control of any
            number of Professors even if you have the same
            number of Students as the player who currently
            controls them.
            """;

    private static final String CHARACTER_THREE_DESCRIPTION = """
            Choose an Island and resolve the Island as if
            Mother Nature had ended her movement there. Mother
            Nature will still move and the Island where she ends
            her movement will also be resolved.
            """;

    private static final String CHARACTER_FOUR_DESCRIPTION = """
            You may move Mother Nature up to 2
            additional Islands than is indicated by the Assistant
            card you've played.
            """;

    private static final String CHARACTER_FIVE_DESCRIPTION = """
            In Setup, put the 4 Ban Cards on this card.
            Place a Ban Card on an Island Group of your choice.
            The first time Mother Nature ends her movement
            there, the Ban Card is removed and the influence is not calculated in that Island Group.
            """;

    private static final String CHARACTER_SIX_DESCRIPTION = """
            When resolving a Conquering on an Island,
            Towers do not count towards influence.
            """;

    private static final String CHARACTER_SEVEN_DESCRIPTION = """
            You may take up to 3 Students from this card
            and replace them with the same number of Students
            from your Entrance.
            """;

    private static final String CHARACTER_EIGHT_DESCRIPTION = """
            During the influence calculation this turn, you
            count as having 2 more influence.
            """;

    private static final String CHARACTER_NINE_DESCRIPTION = """
            Choose a color of Student: during the influence
            calculation this turn, that color adds no influence.
            """;

    private static final String CHARACTER_TEN_DESCRIPTION = """
            You may exchange up to 2 Students between
            your Entrance and your Hall.
            """;

    private static final String CHARACTER_ELEVEN_DESCRIPTION = """
            Take 1 Student from this card and place it in
            your Dining Room. Then, draw a new Student from the
            Bag and place it on this card.
            """;

    private static final String CHARACTER_TWELVE_DESCRIPTION = """
            Choose a type of Student: every player
            (including yourself) must return 3 Students of that type
            from their Dining Room to the bag. If any player has
            fewer than 3 Students of that type, return as many
            Students as they have.
            """;

    public static String getCharacterInformation(int characterID) {
        return switch (characterID) { //todo: add cost
            case 1 -> CHARACTER_ONE_DESCRIPTION;
            case 2 -> CHARACTER_TWO_DESCRIPTION;
            case 3 -> CHARACTER_THREE_DESCRIPTION;
            case 4 -> CHARACTER_FOUR_DESCRIPTION;
            case 5 -> CHARACTER_FIVE_DESCRIPTION;
            case 6 -> CHARACTER_SIX_DESCRIPTION;
            case 7 -> CHARACTER_SEVEN_DESCRIPTION;
            case 8 -> CHARACTER_EIGHT_DESCRIPTION;
            case 9 -> CHARACTER_NINE_DESCRIPTION;
            case 10 -> CHARACTER_TEN_DESCRIPTION;
            case 11 -> CHARACTER_ELEVEN_DESCRIPTION;
            case 12 -> CHARACTER_TWELVE_DESCRIPTION;
            default -> "";
        };
    }

    //Listeners
    public static final String ISLAND_GROUP_LISTENER = "islandGroupListener";
    public static final String BOARD_LISTENER = "boardListener";
    public static final String PHASE_LISTENER = "phaseListener";
    public static final String COIN_LISTENER = "coinListener";
    public static final String ISLAND_LISTENER = "islandListener";
    public static final String ASSISTANT_LISTENER = "assistantListener";
    public static final String WIN_LISTENER = "winListener";
    public static final String PLAYER_LISTENER = "playerListener";
    public static final String CHARACTER_LISTENER = "characterListener";
    public static final String CLOUD_LISTENER = "cloudListener";

}
