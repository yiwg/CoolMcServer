package cn.nxu.edu.coolmc.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by zwxq on 2018/11/28.
 */
class logo extends JPanel {
    public void paint (Graphics g) {
        super.paint(g);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.getImage("img/1.jpg");
        g.drawImage(img, 0, 0, this);

    }
}
