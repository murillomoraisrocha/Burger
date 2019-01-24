package br.com.app.burg.burger.utils;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public abstract class CancelableCallback<T> implements Callback<T> {

    private static List<CancelableCallback> mList = new ArrayList<>();

    private boolean isCanceled = false;
    private Object mTag = null;

    public static void cancelAll() {
        for (CancelableCallback callback : mList) {
            callback.cancel();
        }
    }

    public static void cancel(Object tag) {
        if (tag != null)
            for (CancelableCallback callback : mList) {
                if (tag.equals(callback.mTag))
                    callback.cancel();
            }
    }

    public CancelableCallback() {
        mList.add(this);
//        Singleton.getInstance().showWait();
    }

    public CancelableCallback(Object tag) {
        mTag = tag;
        mList.add(this);
//        Singleton.getInstance().showWait();
    }

    public void cancel() {
        isCanceled = true;
        mList.remove(this);
//        Singleton.getInstance().hideWait();
    }

    @Override
    public void success(T t, Response response) {
        if (!isCanceled)
            onSuccess(t, response);
        mList.remove(this);
//        Singleton.getInstance().hideWait();
    }

    @Override
    public final void failure(RetrofitError error) {
        if (!isCanceled)
            onFailure(error);
        mList.remove(this);
//        Singleton.getInstance().hideWait();
    }

    public abstract void onSuccess(T t, Response response);

    public abstract void onFailure(RetrofitError error);

}