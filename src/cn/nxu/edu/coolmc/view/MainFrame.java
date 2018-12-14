package cn.nxu.edu.coolmc.view;
import javax.swing.*;
import java.awt.*;
import cn.nxu.edu.coolmc.utils.*;
/**
 * Created by zwxq on 2018/11/28.
 */
public class MainFrame extends JFrame {

    // 程序界面宽度
    public final int WIDTH = 1400;
    // 程序界面高度
    public final int HEIGHT = 800;
    static  dp lo = new dp();
    static HumDp humDp=new HumDp();
    static PsrDp psrDp=new PsrDp();
    SerialDp mSerialPortPanel=new SerialDp();

    public MainFrame() {
        initView();
        initComponents();
    }

    /**
     * 初始化窗口
     */
    private void initView() {
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        // 设置程序窗口居中显示
        Point p = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        setBounds(p.x - WIDTH / 2, p.y - HEIGHT / 2, WIDTH, HEIGHT);
        this.setLayout(null);
        setTitle("预冷机控制系统 By 宁夏大学农学院");
    }

    /**
     * 初始化控件
     */
    private void initComponents() {

        lo.setBorder(BorderFactory.createTitledBorder("温度监控"));
        lo.setBounds(20, 390, 400, 400);
        humDp.setBorder(BorderFactory.createTitledBorder("湿度监控"));
        humDp.setBounds(20, 20, 400, 400);
        psrDp.setBorder(BorderFactory.createTitledBorder("气压监控"));
        psrDp.setBounds(460, 20, 400, 400);
        mSerialPortPanel.setBorder(BorderFactory.createTitledBorder("串口设置"));
        mSerialPortPanel.setBounds(460, 390, 400, 400);
        mSerialPortPanel.setLayout(null);
        mSerialPortPanel.setBorder(BorderFactory.createTitledBorder("电气控制"));
        mSerialPortPanel.setBounds(460, 390, 400, 400);
        mSerialPortPanel.setLayout(null);
        add(mSerialPortPanel);
        add(lo);
        add(humDp);
        add(psrDp);

    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
}