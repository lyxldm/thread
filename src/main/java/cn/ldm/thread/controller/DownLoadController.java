package cn.ldm.thread.controller;

import cn.ldm.thread.utills.DateUtills;
import cn.ldm.thread.utills.DownLoadUtills;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URL;

// TODO: 2018/12/12 1.多线程下载文件为啥效率相对单线程下载文件没有明显提升？ thread

@RestController
public class DownLoadController {
    @RequestMapping("download")
    public String downLoad() throws Exception {
        long start = System.currentTimeMillis ();
        try{
            System.out.println (222222);
            System.out.println (222222);
            String filePath = "C:\\Users\\Administrator\\Desktop\\22111.exe";
            URL url = new URL ("http://39.106.211.119/group1/M00/00/03/rBGZfFwQvKqANPmkAjFzkAeDKn8284.exe");
            //URL url = new URL ("http://39.106.211.119/group1/M00/00/03/rBGZfFwQyDaAFjTYAAAAFPS5WL4556.txt");
            DownLoadUtills.getFileWithThreadPool (url,filePath,10);
        }catch (Exception e){
            e.printStackTrace ();
            return "false";
        }
        long ens = System.currentTimeMillis ();
        return String.valueOf (ens-start);
    }



}
