package cn.nxu.edu.coolmc.server;

import cn.nxu.edu.coolmc.dao.JdbcUtils;
import cn.nxu.edu.coolmc.po.Temp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by zwxq on 2018/11/29.
 */
public class TempService {
    static  JdbcUtils jdbcUtils;
    static  {
        jdbcUtils = new JdbcUtils();
        jdbcUtils.getConnection();
    }
    public Temp save( String a ){
        String num= a.substring(2, a.length());
        int ch=Integer.parseInt(a.charAt(1)+"");
        System.out.println(num);
        float d = Float.parseFloat(num);
        Date date = new Date();
        Temp mp = new Temp(d,date, ch,(byte) 1," ");
        String sql = "insert into temp (value, date,channel,avail, remark) value (?, ?, ?, ?,?)";
        List<Object> params = new ArrayList<Object>();
        params.add(mp.getValue());
        params.add(mp.getDate());
        params.add(mp.getChannel());
        params.add(mp.getAvail());
        params.add(mp.getRemark());
        if(ch==1){
            move(Temp.tempListC1,Temp.ARRAY_SIZE);
            Temp.tempListC1.add(mp);
        }
        else  if(ch==2){
            move(Temp.tempListC2,Temp.ARRAY_SIZE);
            Temp.tempListC2.add(mp);
        }
        else if(ch==3){
            move(Temp.tempListC3,Temp.ARRAY_SIZE);
            Temp.tempListC3.add(mp);
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
    void move(ArrayList<Temp> arr,int size){
        if(arr.size()>=size){
            arr.remove(0);
        }
    }
}
