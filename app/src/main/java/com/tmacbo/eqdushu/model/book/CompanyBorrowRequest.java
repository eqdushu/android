package com.tmacbo.eqdushu.model.book;

import com.tmacbo.eqdushu.common.base.BaseRequest;

/**
 * @Author tmacbo
 * @Since on 2017/10/26  14:38
 * @mail tang_bo@hotmail.com
 * @Description TODO
 */

public class CompanyBorrowRequest extends BaseRequest {
    /**
     * companyName : sk
     * companyCode : sdjda
     * type : admin
     */
    private transient String companyId;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
