package com.tmacbo.eqdushu.api;

import cn.share.jack.cyghttp.BaseResponse;
import com.tmacbo.eqdushu.common.base.BaseRequest;
import com.tmacbo.eqdushu.model.GetSmsCodeRequest;
import com.tmacbo.eqdushu.model.book.BookDetailData;
import com.tmacbo.eqdushu.model.book.BorrowInfoData;
import com.tmacbo.eqdushu.model.book.GetBookDetailRequest;
import com.tmacbo.eqdushu.model.book.GetMyBorrowData;
import com.tmacbo.eqdushu.model.book.GetMyBorrowRequest;
import com.tmacbo.eqdushu.model.login.CompanyDataRequest;
import com.tmacbo.eqdushu.model.login.LoginData;
import com.tmacbo.eqdushu.model.login.LoginRequest;
import com.tmacbo.eqdushu.model.main.BookInfo;
import com.tmacbo.eqdushu.model.personalInfo.RewardInfoData;
import com.tmacbo.eqdushu.model.register.RegisterRequest;
import com.tmacbo.eqdushu.model.setting.ModifyPswRequest;
import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by tmacbo on 2017/6/13
 */

public interface APIService {

    @POST("user/auth/register")
    Observable<BaseResponse<LoginData>> register(@Body RegisterRequest request);

    @POST("user/auth/login")
    Observable<BaseResponse<LoginData>> login(@Body LoginRequest request);

    @POST("book/getLibrary")
    @Headers({
        "X-Requested-With:XMLHttpRequest", "Content-type:application/json;charset=UTF-8"
    })
    Observable<BaseResponse<List<BookInfo>>> getLibrary(@Body BaseRequest request);

    @POST("book/getBookDetails")
    Observable<BaseResponse<BookDetailData>> getBookDetails(@Body GetBookDetailRequest request);

    @POST("book/borrowBook")
    Observable<BaseResponse> borrowBook(@Body GetBookDetailRequest request);

    @POST("book/getCompanyBrowDetail")
    Observable<BaseResponse<List<BorrowInfoData>>> getCompanyBrowDetail(
        @Body CompanyDataRequest request);

    @POST("book/backBook")
    Observable<BaseResponse> backBook(@Body GetBookDetailRequest request);

    @POST("book/bookInLibrary")
    Observable<BaseResponse> bookInLibrary(@Body GetBookDetailRequest request);

    @POST("book/getReaderBrowDetail")
    Observable<BaseResponse<List<GetMyBorrowData>>> getReaderBrowDetail(
        @Body GetMyBorrowRequest request);

    @POST("urm/sendSms")
    Observable<BaseResponse<String>> getSmsCode(@Body GetSmsCodeRequest request);

    @POST("user/extra/set-type")
    Observable<BaseResponse<String>> setUserType(@Body CompanyDataRequest request);

    @POST("user/auth/resetpass")
    Observable<BaseResponse<String>> modifyPsw(@Body ModifyPswRequest request);

    @POST("user/auth/resetpass")
    Observable<BaseResponse<String>> resetPsw(@Body ModifyPswRequest request);

    @POST("user/reward/get")
    Observable<BaseResponse<RewardInfoData>> getAccountGas(@Body BaseRequest request);

    @POST("user/reward/send-random")
    Observable<BaseResponse<RewardInfoData>> getRewardGas(@Body BaseRequest request);
}