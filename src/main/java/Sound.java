import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public class Sound {


    public static void startMenuSound() {
        try {
            String soundFilePath = "caves-of-dawn-10376.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Adventure.class.getResource(soundFilePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15.0f); // Reduce volume by 15 decibels.
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playRoomEntrySound() {
        try {
            String soundFilePath = "footsteps-4.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Adventure.class.getResource(soundFilePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 2000);

            // Only close the clip and audioInputStream if it played
            clip.close();
            audioInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playVictorySound() {
        try {
            String soundFilePath = "success-fanfare-trumpets-6185.wav";
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Adventure.class.getResource(soundFilePath));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl =
                    (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-20.0f); // Reduce volume by 20 decibels.
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 2000);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
