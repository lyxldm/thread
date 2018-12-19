package cn.ldm.thread.entity;

import io.searchbox.annotations.JestId;

import java.io.Serializable;

/**
 * @program: thread
 * @description
 * @author: luoyongxiang
 * @create: 2018-12-15 17:25
 **/
public class Book1 implements Serializable {
    @JestId
    private Integer id;
    private String bookName;

    public Book1() {
    }

    public Book1(Integer id, String bookName) {
        this.id = id;
        this.bookName = bookName;
    }
}
