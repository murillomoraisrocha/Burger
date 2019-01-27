package br.com.app.burg.burger.Presenter;

import android.view.View;
import android.widget.Toast;

import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import br.com.app.burg.burger.IngredientsActivity;
import br.com.app.burg.burger.adapters.IngredietsAdapter;
import br.com.app.burg.burger.callback.Get.GetIngredientsSnacks;
import br.com.app.burg.burger.callback.Put.PutAddSnackToCart;
import br.com.app.burg.burger.interfaces.RecyclerViewOnClickListenerHack;
import br.com.app.burg.burger.model.api.in.InExtras;
import br.com.app.burg.burger.model.api.in.InIngredient;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.pattern.Singleton;
import br.com.app.burg.burger.utils.Util;
import retrofit.RetrofitError;

public class PresenterIngredients implements RecyclerViewOnClickListenerHack<InIngredient>, GetIngredientsSnacks.GetIngredientsSnacksOnListener, PutAddSnackToCart.PutAddSnackToCartOnListener {

    private IngredientsActivity ingredientsActivity;

    public PresenterIngredients(IngredientsActivity ingredientsActivity) {

        this.ingredientsActivity = ingredientsActivity;

    }

    public void getIngredients(InSnack inSnack) {
        GetIngredientsSnacks getIngredientsSnacks = new GetIngredientsSnacks(this);
        getIngredientsSnacks.get("", inSnack.getId());

    }

    public void getISnackSelected() {
        InSnack item = Singleton.getInstance().getInSnack();
        String amount = Util.formatMoney(getAmountSnack(item.getIngredients()), true);
        ingredientsActivity.setDescription(item, amount);


    }


    @Override
    public void onClickListener(View view, int position, InIngredient item) {

    }

    @Override
    public void onLongPressClickListener(View view, int position, InIngredient item) {

    }

    public double getAmountSnack(List<Integer> inIngredients) {

        double amount = 0d;
        List<InIngredient> ing = Singleton.getInstance().getInIngredientList();
        for (Integer idIngrediet : inIngredients) {
            int id = idIngrediet;

            if (ing.size() > 0) {
                for (InIngredient i : ing) {
                    if (i.getId() == id) {
                        amount += i.getPrice();
                    }

                }
            }

        }

        return amount;

    }

    @Override
    public void successGetIngredientsSnacksOnListener(InSnack data) {

        List<InIngredient> list = getIngredientsList(data.getIngredients());

        IngredietsAdapter ingredietsAdapter = new IngredietsAdapter(list);
        ingredietsAdapter.setRecyclerViewOnClickListenerHack(this);
        ingredientsActivity.setAdpter(ingredietsAdapter);
    }

    @Override
    public void successGetIngredientsSnacksOnListener(InExtras inExtras) {
        Util.showDefaultMessage("Item Adicionado no cariinho com sucesso!", Toast.LENGTH_LONG);
        ingredientsActivity.goToCart();

    }

    @Override
    public void failGetIngredientsSnacksOnListener(RetrofitError error) {

    }


    public ArrayList<InIngredient> getIngredientsList(List<Integer> inIngredients) {

        List<InIngredient> ing = Singleton.getInstance().getInIngredientList();
        ArrayList<InIngredient> list = new ArrayList<InIngredient>();
        for (Integer idIngrediet : inIngredients) {
            int id = idIngrediet;
            if (ing.size() > 0) {
                for (InIngredient i : ing) {
                    if (i.getId() == id) {
                        list.add(i);
                    }

                }
            }

        }

        return list;

    }

    public void addCart(Integer snack_id){

        PutAddSnackToCart putAddSnackToCart = new PutAddSnackToCart(this);
        InExtras extras = new InExtras();
        putAddSnackToCart.put("",extras, 1 );

    }


}
