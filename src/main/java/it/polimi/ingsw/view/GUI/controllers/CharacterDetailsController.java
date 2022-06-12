package it.polimi.ingsw.view.GUI.controllers;

import it.polimi.ingsw.model.enumerations.CharacterID;
import it.polimi.ingsw.model.enumerations.CreatureColor;
import it.polimi.ingsw.model.gameBoard.IslandGroup;
import it.polimi.ingsw.model.gameBoard.Student;
import it.polimi.ingsw.util.Constants;
import it.polimi.ingsw.view.CharacterView;
import it.polimi.ingsw.view.GUI.AlertBox;
import it.polimi.ingsw.view.GUI.GUI;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;

public class CharacterDetailsController implements GUIController {

    GUI gui;

    public ImageView ch_image;
    public Text ch_description;

    @Override
    public GUI getGUI() {
        return gui;
    }

    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    @Override
    public void init() {
        int id = gui.getCharacterID();
        Scene scene = gui.getSceneByName(Constants.CHARACTER_DETAILS);

        CharacterView characterView = gui.getModelView().getCharacterViewById(id);

        String info = Constants.getCharacterInformation(characterView.getCharacterID());
        ArrayList<Student> students = characterView.getStudents();
        int banCards = characterView.getBanCards();

        // set ch_image
        String characterPath = gui.getCharacterPathByCharacterID(id);
        URL url = getClass().getResource(characterPath);
        if(url != null) {
            ch_image.setImage(new Image(url.toString()));
        }

        // set description
        ch_description.setText(info);

        // set students
        String studentID = "#ch_student_";
        int studentSize = students.size();

        for(int i = 0; i < studentSize; i++) {
            Button studentButton = (Button) scene.lookup(studentID + (i + 1));
            CreatureColor color = students.get(i).getColor();
            String style = "-fx-background-color: " + gui.getHexByFXColor(gui.getFXColorByCreatureColor(color));
            studentButton.setStyle(style);
            studentButton.setDisable(false);
        }

        for(int i = studentSize; i < 6; i++) {
            Button studentButton = (Button) scene.lookup(studentID + (i + 1));
            studentButton.setDisable(true);
        }

        // set ban cards
        String banCardID = "#ch_banCard_";
        ColorAdjust colorAdjust = new ColorAdjust();

        for(int i = 0; i < banCards; i++) {
            ImageView banCardImage = (ImageView) scene.lookup(banCardID + (i + 1));
            banCardImage.setEffect(null);
        }

        for(int i = banCards; i < 4; i++) {
            ImageView banCardImage = (ImageView) scene.lookup(banCardID + (i + 1));
            colorAdjust.setContrast(-0.2);
            colorAdjust.setSaturation(-1.0);
            banCardImage.setEffect(colorAdjust);
        }

        // set colors
        String colorID = "#ch_color_";

        for(int i = 1; i <= 5; i++) {
            Button colorButton = (Button) scene.lookup(colorID + i);
            colorButton.setDisable(id != 9 && id != 12);
        }

    }

    public void onSelectStudent(ActionEvent event) {
        Button studentButton = (Button) event.getSource();
        CreatureColor color = gui.getButtonColor(studentButton);
        gui.setCharacterStudent(color);
    }

    public void onSelectColor(ActionEvent event) {
        Button colorButton = (Button) event.getSource();
        CreatureColor color = gui.getButtonColor(colorButton);
        gui.setCharacterColor(color);
    }

    public void onPlayCharacter(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        int id = gui.getCharacterID();

        getCharacterMessage(id);

        Stage stage = (Stage)(button.getScene().getWindow());
        stage.close();
    }

    public void onGoBack() {
        gui.changeStage(gui.getSecondaryStage(), Constants.CHARACTERS);
    }

    private void getCharacterMessage(int id) {
        CharacterID enumValue = CharacterID.values()[id];
        CreatureColor hallStudent = gui.getHallColor();
        CreatureColor entranceStudent = gui.getEntranceColor();
        CreatureColor characterStudent = gui.getCharacterStudent();
        CreatureColor characterColor = gui.getCharacterColor();
        int islandID = gui.getDestinationIsland();
        String message = "CHARACTER " + id;
        String result;

        switch (enumValue) {
            case CHARACTER_ONE -> result = createCharacterMessage(characterStudent, islandID);
            case CHARACTER_THREE, CHARACTER_FIVE -> result = createCharacterMessage(islandID);
            case CHARACTER_SEVEN -> result = createCharacterMessage(characterStudent, entranceStudent, "Please select a student from this character.");
            case CHARACTER_NINE, CHARACTER_TWELVE -> result = createCharacterMessage(characterColor, "Please select a color.");
            case CHARACTER_TEN -> result = createCharacterMessage(hallStudent, entranceStudent, "Please select a student from your hall.");
            case CHARACTER_ELEVEN -> result = createCharacterMessage(characterStudent, "Please select a student from this character.");
            default -> result = "";
        }

        // reset
        gui.setEntranceColor(null);
        gui.setHallColor(null);
        gui.setDestinationIsland(0);
        gui.setCharacterStudent(null);
        gui.setCharacterColor(null);

        if(result != null) {
            message = message + result;
            gui.getMessageParser().parseInput(message);
        }


    }

    private String createCharacterMessage(int destination) {
        if(destination == 0) {
            Platform.runLater(() -> AlertBox.display("Error", "Please select an island."));
            return null;
        } else {
            IslandGroup islandGroup = gui.getModelView().getIslandGroupByIslandID(destination);
            int islandGroupIndex = gui.getModelView().getIslandGroups().indexOf(islandGroup);
            return " " + (islandGroupIndex + 1);
        }
    }

    private String createCharacterMessage(CreatureColor color, String error) {
        if(color == null) {
            Platform.runLater(() -> AlertBox.display("Error", error));
            return null;
        } else {
            return " " + color.getColorName();
        }
    }

    private String createCharacterMessage(CreatureColor color, int destination) {
        if(color == null) {
            Platform.runLater(() -> AlertBox.display("Error", "Please select a student from this character."));
            return null;
        } else if(destination == 0) {
            Platform.runLater(() -> AlertBox.display("Error", "Please select an island."));
            return null;
        } else {
            return " " + color.getColorName() + " " + destination;
        }
    }

    private String createCharacterMessage(CreatureColor firstColor, CreatureColor secondColor, String firstError) {
        if(firstColor == null) {
            Platform.runLater(() -> AlertBox.display("Error", firstError));
            return null;
        } else if(secondColor == null) {
            Platform.runLater(() -> AlertBox.display("Error", "Please select a student from your entrance."));
            return null;
        } else {
            return " " + firstColor.getColorName() + " " + secondColor.getColorName();
        }
    }


    @Override
    public void quit() {

    }

}
