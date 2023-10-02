// Controller klasse
import javax.sound.sampled.*;
public class Adventure {

    private final Player player;

    public Adventure() {
        Map map = new Map();
        player = new Player(map.getStartingRoom());
    }

    // afslutter spillet
    public void exit() {
        System.exit(0);
    }

    // move metode, flytter 'player' til nyt rum
    public void move(String command) {
        player.move(command);
        playRoomEntrySound();
    }

    // tp metode
    public void xyzzy() {
        player.xyzzy();
    }

    // viser hjælpetektst
    public void help() {
        player.help();

    }

    // viser beskrivelse af rummet
    public void look() {
        player.look();
    }

    // check to see if you won
    public void wincheck() {
        player.wincheck();
    }

    public void startMenuSound() {
        try {
            String soundFilePath = "caves-of-dawn-10376.mp3";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Adventure.class.getResource(soundFilePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();

            clip.close();
            audioInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void playRoomEntrySound() {
        try {
            String soundFilePath = "footsteps-4.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Adventure.class.getResource(soundFilePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

                clip.start();
                Thread.sleep(clip.getMicrosecondLength() / 1000);

                // Only close the clip and audioInputStream if it played
                clip.close();
                audioInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}