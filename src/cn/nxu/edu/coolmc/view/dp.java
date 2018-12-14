package cn.nxu.edu.coolmc.view;
import cn.nxu.edu.coolmc.po.Temp;
import cn.nxu.edu.coolmc.server.TempService;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by zwxq on 2018/11/28.
 */
class  dp extends JPanel {
    //TempService tempService=new TempService();
    String TargetTemperature = "600";
    String lowTarget = "500";
    String llowTarget = "400";
    int Max=600;

    Date startData = null;
    boolean op = false;
    String i = new String ("");
    public void paint (Graphics g) {
        super.paint(g);
        this.pxy(g);
        g.setColor(Color.blue);
        this.pw(g,Temp.tempListC1);
        g.setColor(Color.BLACK);
        this.pw(g,Temp.tempListC2);
        g.setColor(Color.red);
        this.pw(g,Temp.tempListC3);
    }
    public void pw(Graphics g,ArrayList<Temp> arrayList) {
        for(int i = 0;i<arrayList.size()-1;i++){
            int x1=i*6+33;
            int y1=300-((int)(arrayList.get(i).getValue()-Temp.LOW_TEMPERATURE))*4;
            int x2=i*6+39;
            int y2=300-((int)(arrayList.get(i+1).getValue()-Temp.LOW_TEMPERATURE))*4;
            g.drawLine(x1,y1,x2,y2);
        }
    }

    public void pxy(Graphics g) {
        Graphics2D g2=(Graphics2D)g;
        Stroke stroke=new BasicStroke(3.0f);//设置线宽为3.0
        g2.setStroke(stroke);
        g.drawLine(30, 320, 400, 320);
        g.drawLine(30, 40, 30, 320);
        g.drawLine(30, 60, 35, 60);
        g.drawLine(30, 170, 35, 170);
        g.drawLine(30, 300, 35, 300);
        int tempYY=(int) Temp.HIGHT_TEMPERATURE;
        for (int y=60;y<=300;y=y+20){
            g.drawString(Integer.toString(tempYY), 4,y);
            tempYY=tempYY-5;
        }
        /*g.drawString(lowTarget.substring(0, 2)+"."+lowTarget.charAt(2), 4,170);
        g.drawString(Temp.LOW_TEMPERATURE.toString(), 4,280);*/
        //stroke=new BasicStroke(1.0f);//设置线宽为3.0
        //g2.setStroke(stroke);
        //g.drawString("目标设置温度:"+TargetTemperature.substring(0, 2)+"."+TargetTemperature.charAt(2), 130,20);
    }
}
