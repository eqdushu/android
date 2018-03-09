package com.tmacbo.eqdushu.model.book;

import com.tmacbo.eqdushu.common.base.BaseRequest;

/**
 * @Author tmacbo
 * @Since on 2017/10/11  15:31
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class GetBookDetailRequest extends BaseRequest {
    private String isbn;

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
