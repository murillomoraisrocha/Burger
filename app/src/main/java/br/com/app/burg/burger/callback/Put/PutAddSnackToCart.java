package br.com.app.burg.burger.callback.Put;

import android.util.Log;

import com.google.gson.JsonElement;

import java.util.List;

import br.com.app.burg.burger.api.Module;
import br.com.app.burg.burger.model.api.in.InExtras;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.utils.CancelableCallback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public class PutAddSnackToCart {

    PutAddSnackToCartOnListener putAddSnackToCartOnListener;

    public interface PutAddSnackToCartOnListener {
        void successGetIngredientsSnacksOnListener(InExtras inExtras);

        void failGetIngredientsSnacksOnListener(RetrofitError error);
    }

    public PutAddSnackToCart(PutAddSnackToCartOnListener putAddSnackToCartOnListener) {
        this.putAddSnackToCartOnListener = putAddSnackToCartOnListener;
    }

    public void put(String TAG, InExtras s, int lanche_id) {
        Module.service(false).putSnack(
                s,
                lanche_id,
                new CancelableCallback<InExtras>(TAG) {
                    @Override
                    public void onSuccess(InExtras inExtras, Response response) {
                        putAddSnackToCartOnListener.successGetIngredientsSnacksOnListener(inExtras);
                    }

                    @Override
                    public void onFailure(RetrofitError error) {
                        try {
                            String json = new String(((TypedByteArray) error.getResponse().getBody()).getBytes());
                            Log.v("failure", json.toString());
                        } catch (Exception ex) {
                        }
                        putAddSnackToCartOnListener.failGetIngredientsSnacksOnListener(error);
                    }
                });
    }

}
