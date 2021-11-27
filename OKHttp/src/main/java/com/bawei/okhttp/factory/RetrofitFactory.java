package com.bawei.okhttp.factory;

import com.bawei.okhttp.entity.Concurrent;
import com.bawei.okhttp.api.TokenApi;
import com.bawei.okhttp.entity.TokenApiEntity;
import com.bawei.okhttp.factory.calladapter.LiveDataAdapterFactory;
import com.bawei.okhttp.factory.convertfactory.CustomGsonFactory;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RetrofitFactory {

    private static RetrofitFactory factory = null;
    private Retrofit retrofit = null;

    public static RetrofitFactory getInstance() {
        return Holder.INSTANCE;
    }

    public static class Holder{
        private static RetrofitFactory INSTANCE = new RetrofitFactory();
    }

    private RetrofitFactory() {
        retrofit = createRetrofit();
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://82.156.178.182:8082/")
                .client(createClient())
                .addConverterFactory(CustomGsonFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataAdapterFactory.create())
                .build();
    }

    private OkHttpClient createClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(createLogInterceptor())
                .addInterceptor(createTokenInterceptor())
                .writeTimeout(Concurrent.TimeOut, TimeUnit.SECONDS)
                .readTimeout(Concurrent.TimeOut,TimeUnit.SECONDS)
                .connectTimeout(Concurrent.TimeOut,TimeUnit.SECONDS)
                .build();
    }

    private Interceptor createTokenInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response proceed = chain.proceed(request);
                if(proceed.code() == 401){
                    String token = getToken();
                    Request authorization = request.newBuilder().addHeader("Authorization", "bearer " + token).build();
                    return chain.proceed(authorization);
                }
                return proceed;
            }
        };
    }

    private String getToken() {
        TokenApi tokenApi = create(TokenApi.class);
        Call<TokenApiEntity> call = tokenApi.getToken("password","c71ad1c11b81621771d912e1f21ca1931c41131191491a61","");
        try {
            retrofit2.Response<TokenApiEntity> response = call.execute();
            if(response != null && response.body() != null){
                String access_token = response.body().getAccess_token();
                return access_token;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public <T> T create(Class<?> tClass) {
        return (T) retrofit.create(tClass);
    }

    private Interceptor createLogInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }


}
