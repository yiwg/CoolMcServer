package cn.nxu.edu.coolmc.view;

import cn.nxu.edu.coolmc.utils.ByteUtils;
import cn.nxu.edu.coolmc.utils.ProtocolUtils;
import cn.nxu.edu.coolmc.utils.SerialPortManager;
import cn.nxu.edu.coolmc.utils.ShowUtils;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by zwxq on 2018/11/28.
 */
class CMDDp extends JPanel {
    public CMDDp(){
        this.setBorder(BorderFactory.createTitledBorder("电气控制"));
        this.setLayout(new FlowLayout());
    }
}
