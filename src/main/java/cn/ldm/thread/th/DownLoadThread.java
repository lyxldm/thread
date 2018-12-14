package cn.ldm.thread.th;

import cn.ldm.thread.utills.UrlUtills;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoadThread implements Runnable{
    private URL url;
    private String filePath;
    private long startIndex;
    private long endIndex;

    public DownLoadThread() {
    }

    public DownLoadThread(URL url, String filePath, long startIndex, long endIndex) {
        this.url = url;
        this.filePath = filePath;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        try {
            //一个线程一个连接
            HttpURLConnection con = UrlUtills.getUrlConnection (url);
            //断点续传设置，设置开始和结束的范围，每次暂停后，从上一次的进度开始下载  控制流的顺序
            con.setRequestProperty ("range", "bytes="+startIndex+"-"+endIndex);
            System.out.println("线程:"+Thread.currentThread ().getId ()+"下载索引："+startIndex+"~"+endIndex);
            File file = new File (filePath);
            RandomAccessFile out = new RandomAccessFile (file,"rw");
            //读写文件都从startIndex开始，控制文件顺序
            out.seek(startIndex);
            InputStream in = con.getInputStream ();
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = in.read(b)) >= 0) {
                out.write(b, 0, len);
            }
            System.out.println ("线程:"+Thread.currentThread ().getId ()+"完成下载" );
            in.close ();
            out.close ();
        } catch (Exception e) {
            e.printStackTrace ( );
        }
    }

}
