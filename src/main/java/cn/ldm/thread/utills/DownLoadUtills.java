package cn.ldm.thread.utills;

import cn.ldm.thread.th.DownLoadThread;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;

public class DownLoadUtills {

    private static void downLoad(HttpURLConnection con, String filePath , long startIndex, long endIndex) throws IOException {
        System.out.println("线程:"+Thread.currentThread ().getId ()+"下载索引："+startIndex+"~"+endIndex);
        con.setRequestProperty ("range", "bytes="+startIndex+"-"+endIndex);
        con.setReadTimeout(5000);
        con.setRequestMethod ("GET");
        InputStream in = new BufferedInputStream (con.getInputStream ( ));
        File file = new File (filePath);
        RandomAccessFile out = new RandomAccessFile (file,"rw");
        byte[] buffer = new byte[10240];
        int len =0;
        while((len = in.read (buffer)) > 0){
            out.write (buffer,0,len);
        }
        in.close ();
        out.close ();
    }


    public static Boolean getFileWithThreadPool(URL url, String filePath, int time) throws Exception {
        ExecutorService executorService = ThreadPoolUtills.getCachedThreadPool ();
        final HttpURLConnection con = UrlUtills.getUrlConnection (url);
        int contentLength = con.getContentLength ( );
        long startIndex;
        long endIndex;
        try{
            for(int i=0;i<time;i++){
                startIndex = i*(contentLength/time);
                endIndex = (i+1)*(contentLength/time)-1;
                if(i == time-1){
                    endIndex = contentLength-1;
                }
                DownLoadThread downLoadThread = new DownLoadThread (url,filePath,startIndex,endIndex);
                executorService.execute (downLoadThread);
            }
        }catch (Exception e){
            return false;
        }finally {
            executorService.shutdown ();
        }
        return true;
    }
}
