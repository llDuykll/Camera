import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm implements IForm  {


    private JPanel mainPanel;
    public JPasswordField pinField;
    private JPanel pinPanel1;
    private JLabel goodByeLabel;

    public LoginForm(Window window) {
        if (window.isVisible()) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    goodByeLabel.setVisible(true);
                    try {
                        Thread.sleep(3000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    goodByeLabel.setVisible(false);
                }
            });
            thread.start();
        }
        pinField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(pinField.getText());
                String correct = Settings.loginPassword;
                if(pinField.getText().equals(correct)){
                    window.login();
                }
            }
        });
    }

    @Override
    public JPanel getMainContentPain() {
        return mainPanel;
    }

    private void createUIComponents() {
    }
}
