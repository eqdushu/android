package com.tmacbo.eqdushu.model.book;

/**
 * @Author tmacbo
 * @Since on 2017/11/7  18:33
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class BorrowInfoData {

    /**
     * id : 12
     * createTime : 2017-11-08 18:04:59
     * updateTime : 2017-11-08 18:04:59
     * userId : 3
     * companyId : 1
     * bookId : 22
     * isbn : 9787121022982
     * bookImg : null
     * bookTitle : null
     * bookAuthor : null
     * borrowSts : B
     * borrowBegDt : 2017-11-08
     * borrowBegTm : 18:04:59
     * borrowEndDt : 2017-12-08
     * borrowEndTm : null
     */

    private String id;
    private String createTime;
    private String updateTime;
    private String userId;
    private String companyId;
    private String bookId;
    private String isbn;
    private String bookImg;
    private String bookTitle;
    private String bookAuthor;
    private String borrowSts;
    private String borrowBegDt;
    private String borrowBegTm;
    private String borrowEndDt;
    private String borrowEndTm;

    /**
     * ibsn : xxx
     * bookTitle : xxx
     * bookImg : xx
     * browBegDt : yyyyMMdd
     * browEndDt : yyyyMMdd
     * browUsrId : xx
     * browUsrNm : xx
     */

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
