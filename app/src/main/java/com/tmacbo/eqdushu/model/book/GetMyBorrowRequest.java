package com.tmacbo.eqdushu.model.book;

import com.tmacbo.eqdushu.common.base.BaseRequest;

/**
 * @Author tmacbo
 * @Since on 2017/10/12  01:01
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class GetMyBorrowRequest extends BaseRequest {
    private String pageNo;
    private String pageNum;

    public String getPageNo() {
        return pageNo;
    }

    public void setPageNo(String pageNo) {
        this.pageNo = pageNo;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }
}
