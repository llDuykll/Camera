import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm implements IForm{
    private JPanel mainPanel;
    private JCheckBox voiceAlertsCheckbox;
    private JSlider timeoutSlider;
    private JLabel timeoutSliderLabel;
    private JCheckBox alwaysAlertCheckbox;
    private JButton lockWindowButton;
    private JTextField outputDirField;
    private JLabel outputDirLabel;
    private JLabel passwordFieldLabel;
    private JPasswordField customPasswordField;

    public MainForm(Window window) {

        voiceAlertsCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!voiceAlertsCheckbox.isSelected()){
                    alwaysAlertCheckbox.setSelected(false);
                }
                Settings.setVoiceAlerts(voiceAlertsCheckbox.isSelected());
            }
        });
        timeoutSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                Settings.setAlertTimeout(timeoutSlider.getValue());
                timeoutSliderLabel.setText("Alert Timeout: " + Math.round(Settings.alertTimeout) + "s");
                if (Settings.alertTimeout > 60) {
                    int s = Settings.alertTimeout % 60;
                    timeoutSliderLabel.setText("Alert Timeout: " + Settings.alertTimeout / 60 + "m " + s + "s");
                }
            }
        });
        alwaysAlertCheckbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.setAlwaysAlert(alwaysAlertCheckbox.isSelected());
                voiceAlertsCheckbox.setSelected(true);
            }
        });
        lockWindowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                window.lock();
            }
        });
        outputDirField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.setOutputDirectory(outputDirField.getText());
            }
        });

        customPasswordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Settings.setLoginPassword(customPasswordField.getText());
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
