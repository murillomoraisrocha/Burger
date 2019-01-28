package br.com.app.burg.burger.Presenter;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.app.burg.burger.IngredientsActivity;
import br.com.app.burg.burger.OrderActivity;
import br.com.app.burg.burger.adapters.CartAdapter;
import br.com.app.burg.burger.adapters.SnackAdapter;
import br.com.app.burg.burger.callback.Get.GetOrders;
import br.com.app.burg.burger.interfaces.RecyclerViewOnClickListenerHack;
import br.com.app.burg.burger.model.api.in.InIngredient;
import br.com.app.burg.burger.model.api.in.InOrder;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.pattern.Singleton;
import br.com.app.burg.burger.utils.Util;
import retrofit.RetrofitError;


public class PresenterOrders implements RecyclerViewOnClickListenerHack<InIngredient>, GetOrders.GetOrdersOnListener {

    private OrderActivity orderActivity;
    private double amountTotal = 0.d;

    public PresenterOrders(OrderActivity orderActivity) {

        this.orderActivity = orderActivity;

    }

    public void getOrders() {
        GetOrders getOrders = new GetOrders(this);
        getOrders.get("");
    }


    @Override
    public void onClickListener(View view, int position, InIngredient item) {

    }

    @Override
    public void onLongPressClickListener(View view, int position, InIngredient item) {

    }

    @Override
    public void successGetOrdersOnListener(List<InOrder> data) {
        if (data.size() > 0) {
            CartAdapter cartAdapter = new CartAdapter(data);
            cartAdapter.setRecyclerViewOnClickListenerHack(this);
            orderActivity.setAdpter(cartAdapter);
        }
    }

    @Override
    public void failGetOrdersOnListener(RetrofitError error) {
        String teste = "";

    }


}
