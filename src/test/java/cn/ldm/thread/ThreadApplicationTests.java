package cn.ldm.thread;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ThreadApplicationTests {

	@Test
	public void contextLoads() throws Exception {
			long s =System.currentTimeMillis ();
			URL url = new URL ("http://39.106.211.119/group1/M00/00/03/rBGZfFwQvKqANPmkAjFzkAeDKn8284.exe");
			HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection ( );
			InputStream inputStream = httpURLConnection.getInputStream ( );
			RandomAccessFile out = new RandomAccessFile ("C:\\Users\\Administrator\\Desktop\\2221.exe","rw");
			byte[] butes = new byte[1024];
			int len =0;
			while((len = inputStream.read (butes)) >= 0){
				out.write (butes,0,len);
			}
			long e = System.currentTimeMillis ();
			System.out.println (e-s);
	}

}
