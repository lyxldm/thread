package cn.ldm.thread.utills;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtills {
    private static ExecutorService executorService;
    private static ExecutorService executorService1;

    private ThreadPoolUtills() {

    }
    public static ExecutorService getFixedThreadPool(){
        if(executorService == null){
            executorService = Executors.newFixedThreadPool (4);
        }
        return executorService;
    }

    public static ExecutorService getCachedThreadPool(){
        if(executorService1 == null){
            executorService1 = Executors.newCachedThreadPool ();
        }
        return executorService1;
    }
}
