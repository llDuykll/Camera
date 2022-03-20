import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Window extends JFrame {

    public Window(){
        lock();
     }

     public void lock() {
        if (this.isVisible()) smoothSize(300, 200, this);
         this.setSize(300,200);
         this.setTitle("");
         URL resource = this.getClass().getResource("/icon.png");
         try {
             BufferedImage image = ImageIO.read(resource);
             setIconImage(image);
         } catch (IOException e) {
             e.printStackTrace();
         }
         setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
         setContentPane(new LoginForm(this).getMainContentPain());
         setLocationRelativeTo(null);
         setResizable(false);
         setVisible(true);
     }

     public void login() {

         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setTitle("Security System");
         this.smoothSize(700, 500, this);
         this.setContentPane(new MainForm(this).getMainContentPain());
         this.setLocationRelativeTo(null);
         this.setResizable(true);
     }



    @Override
    public void dispose() {
        Start.main.shutdown();
        super.dispose();
    }

    public void smoothSize(int w, int h, JFrame window) {
        int dw = w - window.getWidth();
        int dh = h - window.getHeight();
        float cw = window.getWidth();
        float ch = window.getHeight();
        while (window.getWidth() != w || window.getHeight() != h) {
            if (window.getWidth() != w) cw += dw / 4D;
            if (window.getHeight() != h) ch += dh / 4D;
            int rw = Math.round(cw);
            int rh = Math.round(ch);
            if (window.getWidth() != rw || window.getHeight() != rh) {
                window.setSize(new Dimension(rw, rh));
                window.setLocationRelativeTo(null);
            }
            window.update(window.getGraphics());
        }
    }
}
