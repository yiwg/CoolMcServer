package cn.nxu.edu.coolmc.server;

import cn.nxu.edu.coolmc.dao.JdbcUtils;
import cn.nxu.edu.coolmc.po.Humidity;
import cn.nxu.edu.coolmc.po.Pressure;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zwxq on 2018/11/29.
 */
public class PsrService {
    static  JdbcUtils jdbcUtils;
    static  {
        jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
    }
    public Pressure save(String a ){
        String num= a.substring(2, a.length());
        int ch=Integer.parseInt(a.charAt(1)+"");
        System.out.println(num);
        float d = Float.parseFloat(num);
        Date date = new Date();
        Pressure mp = new Pressure(d,date, ch,(byte) 1," ");
        String sql = "insert into pressure (value, date,channel,avail, remark) value (?, ?, ?, ?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(mp.getValue());
        params.add(mp.getDate());
        params.add(mp.getChannel());
        params.add(mp.getAvail());
        params.add(mp.getRemark());
        if(ch==1){
            move(Pressure.psrListC1,Humidity.ARRAY_SIZE);
            Pressure.psrListC1.add(mp);
        }
        else  if(ch==2){
            move(Pressure.psrListC2,Humidity.ARRAY_SIZE);
            Pressure.psrListC2.add(mp);
        }
        else if(ch==3){
            move(Pressure.psrListC3,Humidity.ARRAY_SIZE);
            Pressure.psrListC3.add(mp);
        }
        //params.add("lisi000");
        try {
            boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
            System.out.println(flag);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return  mp;
    }
    void move(ArrayList<Pressure> arr,int size){
        if(arr.size()>=size){
            arr.remove(0);
        }
    }
}
