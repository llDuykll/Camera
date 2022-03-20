import javax.swing.*;

public class Start {
    public static Main main;
    public static void main(String[] args){

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
        }

        main = new Main();
    }

}
