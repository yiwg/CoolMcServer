package cn.nxu.edu.coolmc.po;

import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

/**
 * Created by zwxq on 2018/11/28.
 */
public class Temp implements Serializable{
    public static  final  int ARRAY_SIZE=60;
    public static   ArrayList<Temp> tempListC1 = new ArrayList<Temp>();
    public static   ArrayList<Temp> tempListC2 = new ArrayList<Temp>();
    public static   ArrayList<Temp> tempListC3 = new ArrayList<Temp>();
    public  static  final float HIGHT_TEMPERATURE =40.00f;
    public  static  final float LOW_TEMPERATURE =-20.00f;
    private static final long serialVersionUID = 1L;

    Long id;

    Date date;

    float value;

    byte avail;

    String remark;

    int channel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public byte getAvail() {
        return avail;
    }

    public void setAvail(byte avail) {
        this.avail = avail;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Temp() {
    }

    public Temp(Long id, float value, byte avail, String remark, int channel) {
        id = id;
        this.value = value;
        this.avail = avail;
        this.remark = remark;
        this.channel = channel;
    }

    public Temp(float value,Date date,int channel,byte avail, String remark) {
        this.date = date;
        this.channel = channel;
        this.value = value;
        this.remark=remark;
        this.avail=avail;
    }

    @Override
    public String toString() {
        return "Temp{" +
                "Id=" + id +
                ", date=" + date +
                ", value=" + value +
                ", avail=" + avail +
                ", remark='" + remark + '\'' +
                ", channel=" + channel +
                '}';
    }
}
