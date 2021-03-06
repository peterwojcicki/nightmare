package com.freedom.sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.SourceDataLine;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.concurrent.Callable;

public class Audio {

    private SourceDataLine sourceLine;
    private String filename;

    public Audio(String file) {
        this.filename = file;
    }

    public void playIndefinitely(Callable<Boolean> stopCondition) {
        try {
            while (!stopCondition.call()) {
                playOnce();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void stop() {
        if (sourceLine != null) {
            sourceLine.stop();
        }
    }

    public void playOnce() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getResourceAsStream("/" + filename)));

            int BUFFER_SIZE = 128000;
            AudioFormat audioFormat = null;
            sourceLine = null;

            audioFormat = audioStream.getFormat();

            sourceLine = AudioSystem.getSourceDataLine(audioFormat);
            sourceLine.open(audioFormat);
            sourceLine.start();

            int nBytesRead = 0;
            byte[] abData = new byte[BUFFER_SIZE];
            while (nBytesRead != -1) {
                try {
                    nBytesRead =
                            audioStream.read(abData, 0, abData.length);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (nBytesRead >= 0) {
                    int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
                }
            }

            sourceLine.drain();
            sourceLine.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
