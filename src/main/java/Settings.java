public class Settings {

    /**
     * Whether audio voice alerts are enabled when motion is detected.
     */
    public static boolean voiceAlerts = false;

    public static int alertTimeout = 20;

    public static boolean alwaysAlert = false;

    public static String outputDirectory = "\\\\26.3.204.20\\Server\\Pictures\\";

    public static String loginPassword = "23058";



    public static void setVoiceAlerts(boolean voiceAlerts) {
        Settings.voiceAlerts = voiceAlerts;
    }

    public static void setAlertTimeout(int value) {
        alertTimeout = value * 10;
    }

    public static void setAlwaysAlert(boolean alwaysAlert) {
        Settings.alwaysAlert = alwaysAlert;
    }

    public static void setOutputDirectory(String outputDirectory) {
        Settings.outputDirectory = outputDirectory;
    }

    public static void setLoginPassword(String loginPassword) {
        Settings.loginPassword = loginPassword;
    }
}
