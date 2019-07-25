package cn.ldm.thread.controller;

import cn.ldm.thread.utills.FastDFSClient;
import cn.ldm.thread.utills.ThreadPoolUtills;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @program: thread
 * @description 多线程下载文件
 * @author: luoyongxiang
 * @create: 2018-12-12 11:26
 **/

@RestController
public class UpLoadController {

    @Value("${IMAGE_SERVER}")
    private String photoBase;


    @RequestMapping("upload")
    public String upload() throws ExecutionException, InterruptedException {
        System.out.println (1 );
        System.out.println (312312 );
        String path = "C:\\Users\\Administrator\\Desktop\\1.txt";
        Future<String> future = ThreadPoolUtills.getFixedThreadPool ().submit (new Callable<String> ( ) {
            @Override
            public String call(){
                try {
                    FastDFSClient fastDFSClient = new FastDFSClient ("classpath:fdfs.conf");
                    String result = fastDFSClient.uploadFile (path);
                    return photoBase+result;
                } catch (Exception e) {
                    e.printStackTrace ( );
                    return "上传失败";
                }
            }
        });
        String result = future.get ( );
        return result;
    }
}
