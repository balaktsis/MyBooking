package Users.Actions.Graphical;

import javax.swing.*;
import java.awt.*;

public class AdjustSize {
    private static Dimension preferredSize = new Dimension();

    public static void AdjustPanelSize(JPanel mainPanel) {
        preferredSize = new Dimension();
        for (int i = 0; i < mainPanel.getComponentCount(); i++) {
            Rectangle bounds = mainPanel.getComponent(i).getBounds();
            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
        }
        Insets insets = mainPanel.getInsets();
        preferredSize.width += insets.right;
        preferredSize.height += insets.bottom;
        mainPanel.setMinimumSize(preferredSize);
        mainPanel.setPreferredSize(preferredSize);
    }

    public static void AdjustContainerSize(Container container) {
        preferredSize = new Dimension();
        for (int i = 0; i < container.getComponentCount(); i++) {
            Rectangle bounds = container.getComponent(i).getBounds();
            preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
            preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
        }
        Insets insets = container.getInsets();
        preferredSize.width += insets.right;
        preferredSize.height += insets.bottom;
        container.setMinimumSize(preferredSize);
        container.setPreferredSize(preferredSize);
    }

}
