package br.com.app.burg.burger.api;

import com.google.gson.JsonElement;

import java.util.List;

import br.com.app.burg.burger.model.api.in.InExtras;
import br.com.app.burg.burger.model.api.in.InIngredient;
import br.com.app.burg.burger.model.api.in.InOrder;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.model.api.in.InputDefault;
import br.com.app.burg.burger.model.api.out.OutExtras;
import br.com.app.burg.burger.utils.CancelableCallback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.PUT;
import retrofit.http.Path;


public interface API {

    /* GETs */
    @GET("/lanche")
    public void getSnack(
            CancelableCallback<List<InSnack>> TAG
    );

    @GET("/lanche/{lanche_id}")
    public void getIngredientsSnack(
            @Path("lanche_id") Integer lanche_id,
            CancelableCallback<InSnack> TAG
    );

    @GET("/ingrediente")
    public void getIngredients(
            CancelableCallback<List<InIngredient>> TAG
    );

    @GET("/pedido")
    public void getOrders(
            CancelableCallback<List<InOrder>> TAG
    );



    /* PUTs */
    @PUT("/pedido/{lanche_id}")
    public void putSnack(
            @Body InExtras extras,
            @Path("lanche_id") Integer lanche_id,
            CancelableCallback<InExtras> TAG
    );


}