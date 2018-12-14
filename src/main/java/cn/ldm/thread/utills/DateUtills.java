package cn.ldm.thread.utills;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: thread
 * @description
 * @author: luoyongxiang
 * @create: 2018-12-14 14:40
 **/
public class DateUtills {
    private static SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");

    public static String getNowDate(){
        return sdf.format (new Date ());
    }
}
