public class SingleSoundPlayer implements Runnable {

    private Thread thread;
    private String path;

    private boolean playing = false;

    public SingleSoundPlayer(String path) {
        this.path = path;
    }

    public void playSound() {
        if (!this.playing) {
            this.thread = new Thread(this);
            this.thread.setName("SingleSoundPlayer-" + path);
            thread.start();
        }

    }

    public void stopPlaying() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isPlaying() {
        return this.playing;
    }

    @Override
    public void run() {
        this.playing = true;
        SoundPlayer.queueSound(this.path);
        this.playing = false;
        stopPlaying();
    }
}
