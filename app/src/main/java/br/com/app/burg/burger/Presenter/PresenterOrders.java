package br.com.app.burg.burger.Presenter;

import android.view.View;

import java.util.List;

import br.com.app.burg.burger.IngredientsActivity;
import br.com.app.burg.burger.OrderActivity;
import br.com.app.burg.burger.callback.Get.GetOrders;
import br.com.app.burg.burger.interfaces.RecyclerViewOnClickListenerHack;
import br.com.app.burg.burger.model.api.in.InIngredient;
import br.com.app.burg.burger.model.api.in.InOrder;
import retrofit.RetrofitError;


public class PresenterOrders implements RecyclerViewOnClickListenerHack<InIngredient>, GetOrders.GetOrdersOnListener {

    private OrderActivity orderActivity;

    public PresenterOrders(OrderActivity orderActivity) {

        this.orderActivity = orderActivity;

    }

    public  void getOrders(){
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
        String teste = "";
    }

    @Override
    public void failGetOrdersOnListener(RetrofitError error) {
        String teste = "";

    }

//    public double getAmountSnack(List<Integer> inIngredients) {
//
//        double amount = 0d;
//        List<InIngredient> ing = Singleton.getInstance().getInIngredientList();
//        for (Integer idIngrediet : inIngredients) {
//            int id = idIngrediet;
//
//            if (ing.size() > 0) {
//                for (InIngredient i : ing) {
//                    if (i.getId() == id) {
//                        amount += i.getPrice();
//                    }
//
//                }
//            }
//
//        }
//
//        return amount;
//
//    }



}
