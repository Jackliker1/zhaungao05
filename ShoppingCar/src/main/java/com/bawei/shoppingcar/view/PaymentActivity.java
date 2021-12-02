package com.bawei.shoppingcar.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.databinding.ViewDataBinding;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.bawei.mvvm.view.MVVMBaseActivity;
import com.bawei.mvvm.viewmodel.BaseViewModel;
import com.bawei.okhttp.database.MySql;
import com.bawei.shoppingcar.BR;
import com.bawei.shoppingcar.R;
import com.bawei.shoppingcar.database.MySqlTable;
import com.bawei.shoppingcar.databinding.ActivityPaymentBinding;
import com.bawei.shoppingcar.entity.PayEntity;
import com.bawei.shoppingcar.pay.AuthResult;
import com.bawei.shoppingcar.pay.PayResult;
import com.bawei.shoppingcar.pay.util.OrderInfoUtil2_0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentActivity extends MVVMBaseActivity<BaseViewModel, ActivityPaymentBinding> {

    public static float price = 0;
    private int index = 0;
    public String str;
    public String sumPrice;
    private Button pay;
    private List<CheckBox> list = new ArrayList<>();

    @Override
    protected void prepareSetValues(HashMap<Integer, Object> mMap) {
        mMap.put(BR.ownerPayment,this);
    }

    @Override
    protected BaseViewModel createViewModel() {
        return null;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    protected void initEvn() {

        initView();

        Intent intent = getIntent();
        float price = intent.getFloatExtra("sumPrice", 0);
        sumPrice = String.valueOf(price);
        str = "银行卡支付￥" + sumPrice;
        pay.setText(str);

    }

    private void initView(){

        CheckBox bankCar,apple,wx,ali;

        bankCar = findViewById(R.id.payment_bankCar);
        apple = findViewById(R.id.payment_applePay);
        wx = findViewById(R.id.payment_wxPay);
        ali = findViewById(R.id.payment_aliPay);
        pay = findViewById(R.id.payment_commit);

        list.add(bankCar);
        list.add(apple);
        list.add(wx);
        list.add(ali);

    }

    public void setBtnText(View view){

        for (int i = 0; i < list.size(); i++) {
            if(view.getId() == list.get(i).getId()){
                list.get(i).setChecked(true);
                str = getStr(i) + "支付￥" + sumPrice;
                pay.setText(str);
                index = i;
            }else{
                list.get(i).setChecked(false);
            }

        }

    }

    public void toPay(View view){

        if(index == 3){
            price = Float.parseFloat(sumPrice);
            EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
            payV2(view);
        }else{
            showToast("余额不足");
        }

    }

    public String getStr(int position){
        switch (position){
            case 0:
                return "银行卡";
            case 1:
                return "Apple";
            case 2:
                return "微信";
            case 3:
                return "支付宝";
        }
        return null;
    }

    public void payed(){

        List<PayEntity> payEntities = new ArrayList<>();

        MySqlTable mySqlTable = new MySqlTable(this,"SqlTable.db",null,1);
        SQLiteDatabase db = mySqlTable.getReadableDatabase();

        Cursor orderTable2 = db.query("orderTable2", null, null, null, null, null, null);

        while (orderTable2.moveToNext()){
            String goodsImg = orderTable2.getString(orderTable2.getColumnIndex("goodsimgurl"));
            int goodsCount = orderTable2.getInt(orderTable2.getColumnIndex("goodscount"));
            float goodsPrice = orderTable2.getFloat(orderTable2.getColumnIndex("goodsprice"));
            float goodsTotalValue = orderTable2.getFloat(orderTable2.getColumnIndex("goodstotalvalue"));
            int id = orderTable2.getInt(orderTable2.getColumnIndex("id"));
            String orderNumber = orderTable2.getString(orderTable2.getColumnIndex("ordernumber"));
            int goodsId = orderTable2.getInt(orderTable2.getColumnIndex("goodsid"));
            PayEntity payEntity = new PayEntity(id,orderNumber,goodsId,goodsImg,goodsPrice,goodsCount,goodsTotalValue);
            payEntities.add(payEntity);
        }

        MySql mySql = new MySql(this, "ShopCar.db", null, 1);
        SQLiteDatabase database = mySql.getReadableDatabase();

        for (int i = 0; i < payEntities.size(); i++) {
            db.execSQL("delete from orderTable2 where id = " + payEntities.get(i).getId());
            database.delete("goods","pic = ?",new String[]{payEntities.get(i).getGoodsImgUrl()});
            payEntities.remove(i);
        }

    }

    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2021000118623257";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "";

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "";

    public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC4LN4op3ysh9l0bESBksukEWNJnRNtD1fFPWL7r+FJKp6se+HBdeAcrF6NTKIlBNXg1gt0gpyL5a6gE8j39YwPI3yKJ44636oriDeCJjYBRMMM1hW9h7iNUNVDV0TxjOFxmTQlYOUDxGPgjF5Qq7OTC9jbaNCbzBwrw2UCYnxmL084UKtk59csNQ8ySgWTyVn1Jqf5H+Acp69Zi4ld0538U3SQOyiRn6hbVAdzLeQSoU+92HzzR2w6r77XKxUwLmVTk7hx7yUc7VQsuZw+2GnsiROyOGtkmVW5a+LLnfN/Niu/ZU6Z2FydVn1SuGuX9pDToHfv0KIG16WDP30zYeGpAgMBAAECggEAH+vu6X72FfVJ1Gr7iLXHw/0bh2PJPSrenJoiMNtwIb1YWS7zlxN2L6IUlUsGA4KOC3Ut8Ri+R2Uc2jPzrj6J8wnEiTGH7l5b4UWv0UgMLjYb1Hq6s+n1K9Ep1Y0nrvIrFQRnzF658ug0I3RQ2IyaCE+SW50UIxzCeTdZp4soYpFe6GFu9H2oWYuNltr0s6MUliZBTpt+yuK4N8+H5d2BK/mtDnmxZUD7V02CZUkLWYi5UvfRGC2eiu86LMNzVQw2Wmke3Buzs76rjuF90k6li93yCs3yOjBv5YvIkvLyNS+35tQHIgV2oXnst7DtMdnepznKin63R2BL/wnt9TjVfQKBgQDxf5slWyUIcPd2RNvXJXkjGRUrONLsZyfhpmEIbsbMjA/oxXfVmtc0N4jrSwHW6ullSoUzcu3uwREDFgXamg48YLUFQT04MiN6iB7tdBomUsP03fGAxRJUdShAby0EycFrVLyco6vBjfyFheezgDk782mMAIYicBMvm06sNbMYWwKBgQDDPBIZTEEh0kxg9VnoasHjCePW1d+GX1m8YR0fhOQMKN6UJmHgS8Mk3r4oa/oBJv7duVKcDiM0To6BEV0ecuNBYzUIqywJSAn79GhGFrSfFxDTLW82WFcFdX4dk/YImd9IcFcFcDMgdYABoosmPK00whhGuwtDnYnD7BbWMZFtSwKBgAouchEKHa6UbKpszsPsTYYgSZcv5xyWYMUPqamcWf9HrGgI4zt7MrV93eTlnac88igWEzWPE97r/tadeBO2SQFcpmUZUgZrfrlswbkLGwsglChfrw3ZwSMUaWUGCuTXyLLIuMb104jufoX2lkUoE8GmSn3fi8gp53UrXA7AmNNfAoGBAK2HmCKqN34jWMYZbdd6BGmTodKTeMELwUwCGYupZ7UzIlsWHorpbEM6RFTTXHxnt8NF9JpGqp3UcS9hSp5EyZ+V2U6iLlr1kAJFg+VASzzYmJjLWn04Wei9POJ3YAjXE7JkP8TSrG7eYW+SlRHgfqdVGZTMrb9kqE3fa5Dor/UvAoGBAMx1LEqVFJJ1KIyA9CrKDa8VI69O845uy33BttNYrsAVGiJyEeC7opPfa4W5Hj6PwkmsGGoWGg0Apm+fqdmVqKUUAOQJ8JoAOoXDbrehcG8DOMokurhvNa2tAEUbzpiX1M2brcGo4J72VnoBtmhdB4ZzTVy9l8sfXZYK1k7BcStt";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        payed();
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。

                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        //showAlert(PayDemoActivity.this, getString(R.string.auth_success) + authResult);
                    } else {
                        // 其他状态值则为授权失败
                        //showAlert(PayDemoActivity.this, getString(R.string.auth_failed) + authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    /**
     * 支付宝支付业务示例
     */
    public void payV2(View v) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {

            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PaymentActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

}
