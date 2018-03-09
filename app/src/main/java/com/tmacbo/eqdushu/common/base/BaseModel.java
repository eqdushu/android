package com.tmacbo.eqdushu.common.base;

import android.text.TextUtils;
import android.util.Log;
import cn.share.jack.cyghttp.BaseRetrofit;
import com.tmacbo.eqdushu.api.APIService;
import com.tmacbo.eqdushu.ui.login.UserDao;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by jack on 2017/6/13
 */

public class BaseModel extends BaseRetrofit {

    private static final String TAG = "BaseModel";

    protected APIService mServletApi;

    protected Map<String, String> mParams = new HashMap<>();

    public BaseModel() {
        super();
        mServletApi = mRetrofit.create(APIService.class);
    }

    @Override
    protected Map<String, String> getCommonMap() {
        Map<String, String> commonMap = new HashMap<>();
        commonMap.put("user_id", String.valueOf(UserDao.getInstance().getUserId()));
        commonMap.put("x-auth-token", String.valueOf(UserDao.getInstance().getToken()));
        return commonMap;
    }


    protected String getToken() {
        return String.valueOf(UserDao.getInstance().getToken());
    }
    protected void addParams(String key, String value) {
        if (TextUtils.isEmpty(key)) {
            Log.e(TAG, "the key is null");
            return;
        }
        mParams.put(key, value);
    }

    protected void addParams(Map<String, String> params) {
        if (null == params) {
            Log.e(TAG, "the map is null");
            return;
        }
        mParams.putAll(params);
    }
}
