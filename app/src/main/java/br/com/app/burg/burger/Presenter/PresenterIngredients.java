package br.com.app.burg.burger.Presenter;

import android.view.View;

import br.com.app.burg.burger.IngredientsActivity;
import br.com.app.burg.burger.MainActivity;
import br.com.app.burg.burger.adapters.IngredietsAdapter;
import br.com.app.burg.burger.adapters.SnackAdapter;
import br.com.app.burg.burger.interfaces.RecyclerViewOnClickListenerHack;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.pattern.Singleton;

public class PresenterIngredients  implements RecyclerViewOnClickListenerHack<InSnack> {

    private IngredientsActivity ingredientsActivity;

    public PresenterIngredients(IngredientsActivity ingredientsActivity) {

        this.ingredientsActivity = ingredientsActivity;

    }

    public void getIngredients(){
        IngredietsAdapter ingredietsAdapter = new IngredietsAdapter(Singleton.getInstance().getInIngredientList());
        ingredietsAdapter.setRecyclerViewOnClickListenerHack(this);
        ingredientsActivity.setAdpter(ingredietsAdapter);

    }


    @Override
    public void onClickListener(View view, int position, InSnack item) {
        String teste = "";
    }

    @Override
    public void onLongPressClickListener(View view, int position, InSnack item) {

    }
}
