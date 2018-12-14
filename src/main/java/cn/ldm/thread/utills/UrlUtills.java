package cn.ldm.thread.utills;

import java.net.HttpURLConnection;
import java.net.URL;

public class UrlUtills {
    public static HttpURLConnection getUrlConnection(URL url) throws Exception {
        if(url == null && url.equals (" ")){
            throw new Exception ("创建url连接异常");
        }
        HttpURLConnection conn = (HttpURLConnection) url.openConnection ( );
        conn.setReadTimeout(5000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Charset", "UTF-8");
        return conn;
    }

}
