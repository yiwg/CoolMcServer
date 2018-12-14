package cn.nxu.edu.coolmc.utils;

import cn.nxu.edu.coolmc.po.Humidity;
import cn.nxu.edu.coolmc.po.Pressure;
import cn.nxu.edu.coolmc.po.Temp;
import cn.nxu.edu.coolmc.server.HumService;
import cn.nxu.edu.coolmc.server.PsrService;
import cn.nxu.edu.coolmc.server.TempService;

import java.util.ArrayList;

/**
 * Created by zwxq on 2018/12/5.
 */
public class ProtocolUtils {
    static  String startflag="s";
    static  String endflag="e";
    static  char tempflag='t';
    static  char humflag='h';
    static  char psrflag='p';
    HumService humService=new HumService();
    TempService tempService=new TempService();
    PsrService psrService=new PsrService();
    public void	getInfo(String comdata){
        ArrayList<String> data=new ArrayList<String>();
        System.out.println(comdata);
        data=StringUtils.split(comdata,startflag,endflag);
        for (String d : data) {
            char ch=d.charAt(0);
            if(ch == tempflag) {
                Temp temp=tempService.save(d);
            }
            if(ch==humflag) {
                Humidity humidity=humService.save(d);
            }
            if(ch==psrflag) {
                Pressure pressure=psrService.save(d);
            }
        }



    }
}
