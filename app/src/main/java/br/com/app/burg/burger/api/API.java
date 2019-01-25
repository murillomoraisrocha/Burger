package br.com.app.burg.burger.api;

import java.util.List;

import br.com.app.burg.burger.model.api.in.InIngredient;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.model.api.in.InputDefault;
import br.com.app.burg.burger.utils.CancelableCallback;
import retrofit.http.GET;


public interface API {

    /* GETs */
    @GET("/lanche")
    public void getSnack(
            CancelableCallback<List<InSnack>> TAG
    );

    @GET("/ingrediente")
    public void getIngredients(
            CancelableCallback<List<InIngredient>> TAG
    );


//
//    /* PUTs */
//    @PUT("/bankdata")
//    public void putBankData(
//            @Body OutBankData outBankData,
//            CancelableCallback<InputDefault> TAG
//    );

}