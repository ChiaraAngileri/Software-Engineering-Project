package it.polimi.ingsw.network.message;

public enum MessageType {

    //client to server
    LOGIN_REQUEST_MESSAGE,
    WIZARD_REQUEST_MESSAGE,
    ASSISTANT_REQUEST_MESSAGE,
    MOVE_STUDENT_MESSAGE,
    MOVE_MOTHER_NATURE_MESSAGE,
    PICK_CLOUD_MESSAGE,
    CHARACTER_INFO_REQUEST_MESSAGE,
    CHARACTER_MESSAGE, CHARACTER_COLOR_MESSAGE, CHARACTER_DOUBLE_COLOR_MESSAGE,
    CHARACTER_DESTINATION_MESSAGE, CHARACTER_COLOR_DESTINATION_MESSAGE,

    //server to client
    LOGIN_REPLY_MESSAGE,
    CHARACTER_INFO_MESSAGE, CHARACTER_PLAYED_MESSAGE,
    WINNER_MESSAGE,
    GENERIC_MESSAGE,
    MATCH_INFO_MESSAGE,
    BOARD_MESSAGE,
    ISLAND_GROUPS_MESSAGE, ISLAND_MESSAGE,
    CURRENT_PLAYER_MESSAGE,
    CURRENT_PHASE_MESSAGE,
    COLORS_AVAILABLE_MESSAGE,
    ASSISTANTS_MESSAGE,
    CLOUDS_MESSAGE,
    WIZARDS_AVAILABLE_MESSAGE,
    CHARACTERS_DRAWN_MESSAGE,
    COIN_MESSAGE,

    //utility
    PING_MESSAGE, PONG_MESSAGE,
    ERROR_MESSAGE, DISCONNECTION_MESSAGE
}
