import java.awt.*;

public class CommonConstants {
    // File paths
    public static final String LOGIN_IMAGE_PATH = "Resources/profile.png";

    // Frame Config
    public static final Dimension FRAME_SIZE = new Dimension(540, 760);
    public static final Dimension TEXT_FIELD_SIZE = new Dimension((int)(FRAME_SIZE.width * 0.8), 50);
    public static final Dimension BUTTON_SIZE = TEXT_FIELD_SIZE;

    // Login Config
    public static final Dimension LOGIN_IMAGE_SIZE = new Dimension(255, 262);

    // result dialog config
    public static final Dimension RESULT_DIALOG_SIZE = new Dimension((int)(FRAME_SIZE.width / 3), (int)(FRAME_SIZE.height / 6));

    // Register Config
    public static final Dimension REGISTER_LABEL_SIZE = new Dimension(FRAME_SIZE.width, 150);
}
