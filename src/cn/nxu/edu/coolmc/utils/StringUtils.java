package cn.nxu.edu.coolmc.utils;

/**
 * Created by zwxq on 2018/12/5.
 */
import java.util.ArrayList;

/**
 * 问题描述:给定一个字符串，在给定起始符和结束符，找出被起始符和结束符之间的字符串，
 *         该字符串不能包含给定起始符或结束符
 * */
public class StringUtils {

    /**
     * @param args
     */
    public static void main(String[] args) {
        String string = "7sdfsdf12dd}dfds211fe212hjhj21";
        System.out.println(split(string, "12", "21"));
    }

    /**
     * @return 返回被包含的字符串List
     * 该方法实现没有考虑正则表达式
     * */
    public static ArrayList<String> split(String str,String s1,String s2){
        String[] strings = str.split(s1);//将字符串用给定的起始符分割成数组
        ArrayList<String> strs = new ArrayList<String>();
        for(String str0:strings){
            if(str0.contains(s2)){//寻找包含结束符的数组元素
                strs.add(str0.substring(0, str0.indexOf(s2)));//取得被包含的元素
            }
        }
        return strs;
    }
}