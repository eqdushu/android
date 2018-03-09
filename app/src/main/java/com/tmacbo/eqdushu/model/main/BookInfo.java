package com.tmacbo.eqdushu.model.main;

import java.io.Serializable;

/**
 * Created by jack on 2017/6/14
 */

public class BookInfo implements Serializable{

    /**
     * id : 1
     * createTime : 1506199818000
     * updateTime : 1506199818000
     * isbn : 9787308083256
     * bookImg : https://img3.doubanio.com/mpic/s4644461.jpg
     * bookTitle : 马云的颠覆智慧
     * bookAuthor : 快刀洪七
     * bookPublisher : 浙江大学出版社
     * bookSummary : 他是“教主”！是极具煽动力的“布道者”！是不走寻常路的企业家！
     */

    private int id;
    private String createTime;
    private String updateTime;
    private String isbn;
    private String bookImg;
    private String bookTitle;
    private String bookAuthor;
    private String bookPublisher;
    private String bookSummary;

    private String isBorrow;

    public int getId() { return id;}

    public void setId(int id) { this.id = id;}

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsbn() { return isbn;}

    public void setIsbn(String isbn) { this.isbn = isbn;}

    public String getBookImg() { return bookImg;}

    public void setBookImg(String bookImg) { this.bookImg = bookImg;}

    public String getBookTitle() { return bookTitle;}

    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle;}

    public String getBookAuthor() { return bookAuthor;}

    public void setBookAuthor(String bookAuthor) { this.bookAuthor = bookAuthor;}

    public String getBookPublisher() { return bookPublisher;}

    public void setBookPublisher(String bookPublisher) { this.bookPublisher = bookPublisher;}

    public String getBookSummary() { return bookSummary;}

    public void setBookSummary(String bookSummary) { this.bookSummary = bookSummary;}

    public String getIsBorrow() {
        return isBorrow;
    }

    public void setIsBorrow(String isBorrow) {
        this.isBorrow = isBorrow;
    }
}
