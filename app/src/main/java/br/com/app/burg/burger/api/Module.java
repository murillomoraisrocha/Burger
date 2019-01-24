package br.com.app.burg.burger.api;

import android.os.Debug;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import br.com.app.burg.burger.BuildConfig;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

public class Module {

    public Gson providesGson() {

        return new GsonBuilder()
                .create();
    }

    private OkHttpClient myOkHttpClient() {

        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(30, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(15, TimeUnit.SECONDS);

        return okHttpClient;
    }

    public RestAdapter providesRestAdapter(Gson gson, String token, boolean isLogin) {

        RestAdapter restAdapter;

        if (isLogin) {

            restAdapter = new RestAdapter.Builder()
                    .setClient(new OkClient(myOkHttpClient()))
                    .setLog(new AndroidLog("Retrofit"))
                    .setLogLevel(Debug.isDebuggerConnected() ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                    .setEndpoint(BuildConfig.API_URL)
                    .setConverter(new GsonConverter(gson))
                    .build();

        } else {

            restAdapter = new RestAdapter.Builder()
                    .setClient(new OkClient(myOkHttpClient()))
                    .setLog(new AndroidLog("Retrofit"))
                    .setLogLevel(Debug.isDebuggerConnected() ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                    .setEndpoint(BuildConfig.API_URL)
                    .setConverter(new GsonConverter(gson))
                    .setRequestInterceptor(new HeaderAPI())
                    .build();

        }

        return restAdapter;

    }

    public API provideService(RestAdapter adapter) {
        return adapter.create(API.class);
    }

    public static API service(boolean isLogin) {
        Module module = new Module();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        RestAdapter adapter = module.providesRestAdapter(gson, "", isLogin);
        return module.provideService(adapter);
    }

}
