package cn.nxu.edu.coolmc.view;
import cn.nxu.edu.coolmc.po.Humidity;
import cn.nxu.edu.coolmc.po.Temp;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by zwxq on 2018/11/28.
 */
class PsrDp extends JPanel {
    //TempService tempService=new TempService();
    public void paint (Graphics g) {
        super.paint(g);
        this.pxy(g);
        g.setColor(Color.blue);
        this.pw(g, Humidity.humListC1);
        g.setColor(Color.BLACK);
        this.pw(g,Humidity.humListC2);
        g.setColor(Color.red);
        this.pw(g,Humidity.humListC3);
    }
    public void pw(Graphics g,ArrayList<Humidity> arrayList) {
        for(int i = 0;i<arrayList.size()-1;i++){
            int x1=i*6+33;
            int y1=300-((int)(arrayList.get(i).getValue()-Temp.LOW_TEMPERATURE))*2;
            int x2=i*6+39;
            int y2=300-((int)(arrayList.get(i+1).getValue()-Temp.LOW_TEMPERATURE))*2;
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
        int tempYY=(int) Humidity.HIGHT_TEMPERATURE;
        for (int y=60;y<=300;y=y+20){
            g.drawString(Integer.toString(tempYY), 4,y);
            tempYY=tempYY-10;
        }
    }
}
