package cn.nxu.edu.coolmc.view;
import cn.nxu.edu.coolmc.po.Temp;
import cn.nxu.edu.coolmc.utils.ByteUtils;
import cn.nxu.edu.coolmc.utils.ProtocolUtils;
import cn.nxu.edu.coolmc.utils.SerialPortManager;
import cn.nxu.edu.coolmc.utils.ShowUtils;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * Created by zwxq on 2018/11/28.
 */
class SerialDp extends JPanel {
    //TempService tempService=new TempService();

    public JLabel mSerialPortLabel = new JLabel("串口");
    public JLabel mBaudrateLabel = new JLabel("波特率");
    public JLabel	set = new JLabel("请需要输入加热温度");
    public JComboBox mCommChoice = new JComboBox();
    public JComboBox mBaudrateChoice = new JComboBox();
    public ButtonGroup mDataChoice = new ButtonGroup();
    public JRadioButton mDataASCIIChoice = new JRadioButton("ASCII", true);
    public JRadioButton mDataHexChoice = new JRadioButton("Hex");
    public java.util.List<String> mCommList = null;
    public JTextArea mDataInput = new JTextArea();
    public JButton mSerialPortOperate = new JButton("打开串口");
    //private JButton start = new JButton("开始加热");
    public JButton stop = new JButton("停止加热");
    public JButton report = new JButton("生成报告");
    public JButton mSendData = new JButton("发送数据");

    public JTextArea mDataView = new JTextArea();
    public JTextArea mData   = new JTextArea();
    public JScrollPane mScrollDataView = new JScrollPane(mDataView);
    // 串口对象
    public SerialPort mSerialport;

    ProtocolUtils protocolUtils=new ProtocolUtils();
    public SerialDp() {
        this.setBorder(BorderFactory.createTitledBorder("串口设置"));
        this.setLayout(new FlowLayout());
        mSerialPortLabel.setForeground(Color.gray);
        mSerialPortLabel.setBounds(10, 25, 40, 20);
        this.add(mSerialPortLabel);

        mCommChoice.setFocusable(false);
        mCommChoice.setBounds(60, 25, 100, 20);
        this.add(mCommChoice);

        mBaudrateLabel.setForeground(Color.gray);
        mBaudrateLabel.setBounds(10, 60, 40, 20);
        this.add(mBaudrateLabel);

        mBaudrateChoice.setFocusable(false);
        mBaudrateChoice.setBounds(60, 60, 100, 20);
        this.add(mBaudrateChoice);

        mDataASCIIChoice.setBounds(20, 95, 55, 20);
        mDataHexChoice.setBounds(95, 95, 55, 20);
        mDataChoice.add(mDataASCIIChoice);
        mDataChoice.add(mDataHexChoice);
        this.add(mDataASCIIChoice);
        this.add(mDataHexChoice);

        mCommList = SerialPortManager.findPorts();
        // 检查是否有可用串口，有则加入选项中
        if (mCommList == null || mCommList.size() < 1) {
            ShowUtils.warningMessage("没有搜索到有效串口！");
        } else {
            for (String s : mCommList) {
                mCommChoice.addItem(s);
            }
        }

        mBaudrateChoice.addItem("9600");
        mBaudrateChoice.addItem("19200");
        mBaudrateChoice.addItem("38400");
        mBaudrateChoice.addItem("57600");
        mBaudrateChoice.addItem("115200");

        mSerialPortOperate.setBounds(40, 130, 90, 20);
        this.add(mSerialPortOperate);
        mData.setBounds(10, 160, 150, 20);
        this.add(mData);
        mSendData.setBounds(40, 190, 90, 20);
        this.add(mSendData);
        mDataView.setBounds(40, 210, 90, 40);
        this.add(mDataView);
        actionListener();
    }
    private void actionListener() {
        // 串口
        mCommChoice.addPopupMenuListener(new PopupMenuListener() {

            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
                mCommList = SerialPortManager.findPorts();
                // 检查是否有可用串口，有则加入选项中
                if (mCommList == null || mCommList.size() < 1) {
                    ShowUtils.warningMessage("没有搜索到有效串口！");
                } else {
                    int index = mCommChoice.getSelectedIndex();
                    mCommChoice.removeAllItems();
                    for (String s : mCommList) {
                        mCommChoice.addItem(s);
                    }
                    mCommChoice.setSelectedIndex(index);
                }
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
                // NO OP
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
                // NO OP
            }
        });
        mSerialPortOperate.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.out.println("打开串口");
                if ("打开串口".equals(mSerialPortOperate.getText()) && mSerialport == null) {
                    openSerialPort(e);
                } else {
                    closeSerialPort(e);
                }
            }
        });

        // 发送数据
        mSendData.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sendData(e);
            }
        });

        mSendData.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                sendData(e);
            }
        });
    }
    private void openSerialPort(java.awt.event.ActionEvent evt) {
        // 获取串口名称
        String commName = (String) mCommChoice.getSelectedItem();
        // 获取波特率，默认为9600
        int baudrate = 9600;
        String bps = (String) mBaudrateChoice.getSelectedItem();
        baudrate = Integer.parseInt(bps);

        // 检查串口名称是否获取正确
        if (commName == null || commName.equals("")) {
            ShowUtils.warningMessage("没有搜索到有效串口！");
        } else {
            try {
                mSerialport = SerialPortManager.openPort(commName, baudrate);
                if (mSerialport != null) {
                    mDataView.setText("串口已打开" + "\r\n");
                    mSerialPortOperate.setText("关闭串口");
                }
            } catch (PortInUseException e) {
                ShowUtils.warningMessage("串口已被占用！");
            }
        }

        // 添加串口监听
        SerialPortManager.addListener(mSerialport, new SerialPortManager.DataAvailableListener() {

            @Override
            public void dataAvailable() {
                byte[] data = null;
                try {
                    if (mSerialport == null) {
                        ShowUtils.errorMessage("串口对象为空，监听失败！");
                    } else {
                        // 读取串口数据
                        data = SerialPortManager.readFromPort(mSerialport);

                        // 以字符串的形式接收数据
                        if (mDataASCIIChoice.isSelected()) {
                            mDataView.append(new String(data) + "\r\n");
                            protocolUtils.getInfo(new String(data));
                            MainFrame.lo.repaint();
                            MainFrame.humDp.repaint();
                        }

                    }
                } catch (Exception e) {
                    ShowUtils.errorMessage(e.toString());
                    // 发生读取错误时显示错误信息后退出系统
                    System.exit(0);
                }
            }
        });
    }

    /**
     * 关闭串口
     *
     * @param evt
     *            点击事件
     */
    private void closeSerialPort(java.awt.event.ActionEvent evt) {
        SerialPortManager.closePort(mSerialport);
        mDataView.setText("串口已关闭" + "\r\n");
        mSerialPortOperate.setText("打开串口");
        mSerialport = null;
    }

    /**
     * 发送数据
     *
     * @param evt
     *            点击事件
     */
    private void sendData(java.awt.event.ActionEvent evt) {
        // 待发送数据
        String data = mDataInput.getText().toString();

        if (mSerialport == null) {
            ShowUtils.warningMessage("请先打开串口！");
            return;
        }

        if ("".equals(data) || data == null) {
            ShowUtils.warningMessage("请输入要发送的数据！");
            return;
        }

        // 以字符串的形式发送数据
        if (mDataASCIIChoice.isSelected()) {
            SerialPortManager.sendToPort(mSerialport, data.getBytes());

        }

        // 以十六进制的形式发送数据
        if (mDataHexChoice.isSelected()) {
            SerialPortManager.sendToPort(mSerialport, ByteUtils.hexStr2Byte(data));
        }
    }


}
