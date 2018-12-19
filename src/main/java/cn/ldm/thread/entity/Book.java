package cn.ldm.thread.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: thread
 * @description
 * @author: luoyongxiang
 * @create: 2018-12-15 17:11
 **/
@Document (indexName = "book8",type = "动画8")
public class Book implements Serializable {
    @Id
    private long id;
    private String bookName;
    private Date date;

    public Book() {
    }

    public Book(long id, String bookName, Date date) {
        this.id = id;
        this.bookName = bookName;
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
