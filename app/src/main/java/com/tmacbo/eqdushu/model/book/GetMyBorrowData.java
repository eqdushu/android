package com.tmacbo.eqdushu.model.book;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * @Author tmacbo
 * @Since on 2017/10/12  14:32
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class GetMyBorrowData {

    /**
     * id : 1
     * createTime : null
     * updateTime : null
     * userId : 12313
     * companyId : 222
     * bookId : 7
     * isbn : 9787108045010
     * bookImg : https://img1.doubanio.com/mpic/s26707088.jpg
     * bookTitle : 陈寅恪的最后20年
     * bookAuthor : ["陆键东"]
     * borrowSts : B
     * borrowBegDt : null
     * borrowBegTm : null
     * borrowEndDt : null
     * borrowEndTm : null
     */
    @SerializedName("id")
    private int id;

    @SerializedName("createTime")
    private String createTime;

    @SerializedName("updateTime")
    private String updateTime;

    @SerializedName("userId")
    private String userId;

    @SerializedName("companyId")
    private String companyId;

    @SerializedName("bookId")
    private String bookId;

    @SerializedName("isbn")
    private String isbn;

    @SerializedName("bookImg")
    private String bookImg;

    @SerializedName("bookTitle")
    private String bookTitle;

    @SerializedName("bookAuthor")
    private String bookAuthor;

    @SerializedName("borrowSts")
    private String borrowSts;

    @SerializedName("borrowBegDt")
    private String borrowBegDt;

    @SerializedName("borrowBegTm")
    private String borrowBegTm;

    @SerializedName("borrowEndDt")
    private String borrowEndDt;

    @SerializedName("borrowEndTm")
    private String borrowEndTm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookImg() {
        return bookImg;
    }

    public void setBookImg(String bookImg) {
        this.bookImg = bookImg;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBorrowSts() {
        return borrowSts;
    }

    public void setBorrowSts(String borrowSts) {
        this.borrowSts = borrowSts;
    }

    public String getBorrowBegDt() {
        return borrowBegDt;
    }

    public void setBorrowBegDt(String borrowBegDt) {
        this.borrowBegDt = borrowBegDt;
    }

    public String getBorrowBegTm() {
        return borrowBegTm;
    }

    public void setBorrowBegTm(String borrowBegTm) {
        this.borrowBegTm = borrowBegTm;
    }

    public String getBorrowEndDt() {
        return borrowEndDt;
    }

    public void setBorrowEndDt(String borrowEndDt) {
        this.borrowEndDt = borrowEndDt;
    }

    public String getBorrowEndTm() {
        return borrowEndTm;
    }

    public void setBorrowEndTm(String borrowEndTm) {
        this.borrowEndTm = borrowEndTm;
    }
}
