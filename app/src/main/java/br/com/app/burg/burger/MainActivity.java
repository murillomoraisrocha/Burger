package br.com.app.burg.burger;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mobsandgeeks.saripaar.Validator;

import br.com.app.burg.burger.Presenter.PresenterSnack;
import br.com.app.burg.burger.adapters.SnackAdapter;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.utils.Util;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private PresenterSnack presenterSnack;


   @BindView(R.id.rvSnack)
   RecyclerView rvSnack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        rvSnack.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvSnack.setLayoutManager(llm);
        rvSnack.setItemAnimator(new DefaultItemAnimator());
        //rvAddress.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), rvAddress, this));

        float dpHeight = Util.getDisplayMetrics().heightPixels;
        rvSnack.setMinimumHeight(Math.round(dpHeight - Util.getStatusBarHeight()));

        presenterSnack = new PresenterSnack(this);
        presenterSnack.getIngredients();
        presenterSnack.getSnacks();
    }



    public void setAdpter(SnackAdapter snackAdapter){
        rvSnack.setAdapter(snackAdapter);

    }

    public void goToIngredients(InSnack inSnack) {
        Intent myIntent = new Intent(this, IngredientsActivity.class);
        this.startActivity(myIntent);
    }

}


