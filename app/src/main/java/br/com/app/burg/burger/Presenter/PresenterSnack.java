package br.com.app.burg.burger.Presenter;

import android.content.Intent;
import android.view.View;

import java.util.List;

import br.com.app.burg.burger.IngredientsActivity;
import br.com.app.burg.burger.MainActivity;
import br.com.app.burg.burger.adapters.SnackAdapter;
import br.com.app.burg.burger.callback.Get.GetIngredients;
import br.com.app.burg.burger.callback.Get.GetSnacks;
import br.com.app.burg.burger.interfaces.RecyclerViewOnClickListenerHack;
import br.com.app.burg.burger.model.api.in.InIngredient;
import br.com.app.burg.burger.model.api.in.InIngredients;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.pattern.Singleton;
import retrofit.RetrofitError;

public class PresenterSnack implements GetSnacks.GetSnacksOnListener, GetIngredients.GetIngredientsOnListener, RecyclerViewOnClickListenerHack<InSnack> {

    private MainActivity mainActivity;

    public PresenterSnack(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void getSnacks() {
        GetSnacks getSnacks = new GetSnacks(this);
        getSnacks.get("");

    }

    public void getIngredients() {
        GetIngredients getIngredients = new GetIngredients(this);
        getIngredients.get("");

    }


    @Override
    public void successGetSnacksOnListener(List<InSnack> data) {
        SnackAdapter snackAdapter = new SnackAdapter(data);
        snackAdapter.setRecyclerViewOnClickListenerHack(this);
        mainActivity.setAdpter(snackAdapter);
    }

    @Override
    public void failGetSnacksOnListener(RetrofitError error) {

    }

    @Override
    public void onClickListener(View view, int position, InSnack item) {
        goToIngredients(item);
    }

    @Override
    public void onLongPressClickListener(View view, int position, InSnack item) {

    }

    @Override
    public void successGetIngredientsOnListener(List<InIngredient> data) {
        if (data.size() > 0) {
            Singleton.getInstance().setInIngredientList(data);

        }

    }

    @Override
    public void failGetIngredientsOnListener(RetrofitError error) {

    }

    public void goToIngredients(InSnack inSnack) {
        mainActivity.goToIngredients(inSnack);
    }
}
