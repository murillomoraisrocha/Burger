package br.com.app.burg.burger.Presenter;

import android.view.View;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.app.burg.burger.IngredientsActivity;
import br.com.app.burg.burger.PersonalizeSnackActivity;
import br.com.app.burg.burger.ServiceCalculate.Calculate;
import br.com.app.burg.burger.ServiceCalculate.Promotions;
import br.com.app.burg.burger.adapters.IngredietsAdapter;
import br.com.app.burg.burger.adapters.IngredietsPersonalizeAdapter;
import br.com.app.burg.burger.callback.Get.GetIngredientsSnacks;
import br.com.app.burg.burger.callback.Put.PutAddSnackToCart;
import br.com.app.burg.burger.callback.Put.PutAddSnackWithExtrasToCart;
import br.com.app.burg.burger.interfaces.RecyclerViewOnClickListenerHack;
import br.com.app.burg.burger.model.api.in.InExtras;
import br.com.app.burg.burger.model.api.in.InIngredient;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.model.api.out.OutExtras;
import br.com.app.burg.burger.pattern.Singleton;
import br.com.app.burg.burger.utils.Util;
import retrofit.RetrofitError;

public class PresenterPersonalizeSnack implements RecyclerViewOnClickListenerHack<InIngredient>, PutAddSnackWithExtrasToCart.PutAddSnackToCartOnListener {

    private PersonalizeSnackActivity personalizeSnackActivity;
    private Calculate calculate = new Calculate();
    private double amountSnack = 0.d;
    private List<Integer> extra = new ArrayList<Integer>();
    private Promotions promotions = new Promotions();
    private Integer teste = 0;

    public PresenterPersonalizeSnack(PersonalizeSnackActivity personalizeSnackActivity) {

        this.personalizeSnackActivity = personalizeSnackActivity;

    }

    public void getIngredients() {
        List<InIngredient> ing = Singleton.getInstance().getInIngredientList();
        IngredietsPersonalizeAdapter ingredietsPersonalizeAdapter = new IngredietsPersonalizeAdapter(ing);
        ingredietsPersonalizeAdapter.setRecyclerViewOnClickListenerHack(this);
        personalizeSnackActivity.setAdpter(ingredietsPersonalizeAdapter);

    }

    public void getISnackSelected() {
        InSnack item = Singleton.getInstance().getInSnack();
        String amount = Util.formatMoney(getAmountSnack(item.getIngredients()), true);
        personalizeSnackActivity.setDescription(item, amount);


    }

    public double getAmountSnack(List<Integer> inIngredients) {

        amountSnack = calculate.calculateAmount(inIngredients);
        return amountSnack;


    }


    @Override
    public void onClickListener(View view, int position, InIngredient item) {
        addExtra(item);

        if (item.getId() == 5) {
            double teste = muchCheese(extra, item);
            amountSnack = calculate.calculateAmountWithExtras(item, amountSnack);
            updateAmount(amountSnack - teste);

        } else {
            amountSnack = calculate.calculateAmountWithExtras(item, amountSnack);
            updateAmount(amountSnack);
        }


    }

    @Override
    public void onLongPressClickListener(View view, int position, InIngredient item) {

    }


    public void addCart(Integer snack_id) {

        PutAddSnackWithExtrasToCart putAddSnackToCart = new PutAddSnackWithExtrasToCart(this);
        InExtras extras = new InExtras();
        extras.setExtras(extra);
        putAddSnackToCart.put("", extras, snack_id);


    }

    public void updateAmount(double amountE) {
        String amount = Util.formatMoney(amountE, true);
        personalizeSnackActivity.updateAmount(amount);
    }

    public void addExtra(InIngredient item) {
        extra.add(item.getId());
    }

    @Override
    public void successGetIngredientsSnacksOnListener(InExtras inExtras) {
        String teste = "";
    }

    @Override
    public void failGetIngredientsSnacksOnListener(RetrofitError error) {

    }

    public double muchCheese(List<Integer> inIngredients, InIngredient inIng) {
        for (Integer idIngrediet : inIngredients) {
            Integer id = idIngrediet;
            if (id == 5) {
                int result = (teste % 3);
                teste++;
                if (teste >= 3 && result == 1) {
                    return inIng.getPrice();
                }

            }

        }

        return 0.0;

    }


}


