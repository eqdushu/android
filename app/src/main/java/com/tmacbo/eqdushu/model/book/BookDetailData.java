package com.tmacbo.eqdushu.model.book;

/**
 * @Author tmacbo
 * @Since on 2017/10/11  15:29
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class BookDetailData {

    /**
     * bookTitle : xxx
     * bookImg : xx
     * bookAuthor : xx
     * bookPublish : xx
     * bookSummary : xx
     */

    private String bookTitle;
    private String bookImg;
    private String bookAuthor;
    private String bookPublish;
    private String bookSummary;

    public String getBookTitle() { return bookTitle;}

    public void setBookTitle(String bookTitle) { this.bookTitle = bookTitle;}

    public String getBookImg() { return bookImg;}

    public void setBookImg(String bookImg) { this.bookImg = bookImg;}

    public String getBookAuthor() { return bookAuthor;}

    public void setBookAuthor(String bookAuthor) { this.bookAuthor = bookAuthor;}

    public String getBookPublish() { return bookPublish;}

    public void setBookPublish(String bookPublish) { this.bookPublish = bookPublish;}

    public String getBookSummary() { return bookSummary;}

    public void setBookSummary(String bookSummary) { this.bookSummary = bookSummary;}
}
