import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main implements WebcamMotionListener {
    public static BufferedImage lastImage;
    public long lastPictureTime;
    public String currentFolder;
    public Webcam webcam;
    public Window window;
    public SingleSoundPlayer voiceAlertSound;
    public DiscordBot discordBot;

    public void shutdown(){
        webcam.close();
    }
    public Main() {
        URL resource = this.getClass().getResource("/Voice.mp3");
        voiceAlertSound = new SingleSoundPlayer(resource.getPath());
        window = new Window();
        webcam = Webcam.getDefault();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                discordBot = new DiscordBot();
                discordBot.loginBot();
            }
        });
        thread.start();
        System.out.println(webcam.getName());
        webcam.open();

        StartDetectingMotion(webcam);

    }

    @Override
    public void motionDetected(WebcamMotionEvent webcamMotionEvent) {
        long delta = System.currentTimeMillis() - lastPictureTime;
        System.out.println("Motion picture taken. Delta: " + delta + " Time: " + getCurrentTime());
        lastPictureTime = System.currentTimeMillis();
        if(delta > Settings.alertTimeout * 1000L){
            currentFolder = getCurrentTime();
            alert();
        }

        try {
            BufferedImage image = webcam.getImage();
             if (Settings.alwaysAlert) alert();

            File directory = new File(Settings.outputDirectory + "\\"+ currentFolder +"\\");
            if (!directory.exists()) directory.mkdirs();

            setCurrentImage(image);
            ImageIO.write(image, "PNG", new File( directory.getAbsolutePath() +"\\" + getCurrentTime() + ".png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void alert() {
        if (Settings.voiceAlerts)
        voiceAlertSound.playSound();
    }











    public void StartDetectingMotion(Webcam camera) {
        WebcamMotionDetector detector = new WebcamMotionDetector(camera);
        detector.setInterval(500); // one check per 500 ms
        detector.addMotionListener((WebcamMotionListener) this);
        detector.start();

    }


    public String getCurrentTime() {
        long milliSec = System.currentTimeMillis();
        DateFormat simple = new SimpleDateFormat("MMM dd yyyy HH;mm;ss;SSS");
        Date result = new Date(milliSec);
        return simple.format(result);

    }

    public static void setCurrentImage(BufferedImage image) {
        lastImage = image;
    }
}

