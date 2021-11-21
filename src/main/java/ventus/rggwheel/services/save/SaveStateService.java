package ventus.rggwheel.services.save;

import ventus.rggwheel.model.SaveState;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveStateService {
    private SaveState currentState;
    private List<String> playedList;

    public SaveStateService() {
        currentState = new SaveState();
        playedList = new ArrayList<>();
        loadState();
    }

    public SaveState getCurrentState() {
        return currentState;
    }
    public List<String> getPlayedList() {
        return playedList;
    }


    public void loadState() {
        try {
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + "/saves/" +System.getProperty("user.name") + ".save");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.currentState = (SaveState) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.err.println("Save state not found, creating new one");
            saveState();
        } catch (ClassNotFoundException c) {
            System.err.println("Save state class not found");
            c.printStackTrace();
        }
        try {
            FileInputStream fileIn = new FileInputStream(System.getProperty("user.dir") + "/saves/" +System.getProperty("user.name") + "_playedSongs" + ".save");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            this.playedList = (List<String>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            System.err.println("Save state not found, creating new one");
            saveState();
        } catch (ClassNotFoundException c) {
            System.err.println("Save state class not found");
            c.printStackTrace();
        }
    }

    public void saveState() {
        try {
            File saveFile = new File(System.getProperty("user.dir") + "/saves/" + System.getProperty("user.name") + ".save");
            FileOutputStream fileOut = new FileOutputStream(saveFile);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(currentState);
            out.close();
            fileOut.close();
            System.out.println("Progress saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveSongs(){
        try {
            File playedSongs = new File(System.getProperty("user.dir") + "/saves/" + System.getProperty("user.name") + "_playedSongs" + ".save");
            FileOutputStream playedSongsOut = new FileOutputStream(playedSongs);
            ObjectOutputStream outSongs = new ObjectOutputStream(playedSongsOut);
            outSongs.writeObject(playedList);
            outSongs.close();
            playedSongsOut.close();
            System.out.println("Songs saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
