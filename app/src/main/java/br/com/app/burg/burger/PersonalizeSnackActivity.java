package br.com.app.burg.burger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.app.burg.burger.OrderActivity;
import br.com.app.burg.burger.Presenter.PresenterIngredients;
import br.com.app.burg.burger.Presenter.PresenterPersonalizeSnack;
import br.com.app.burg.burger.R;
import br.com.app.burg.burger.adapters.IngredietsAdapter;
import br.com.app.burg.burger.adapters.IngredietsPersonalizeAdapter;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.pattern.Singleton;
import br.com.app.burg.burger.utils.Util;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalizeSnackActivity extends AppCompatActivity {

    private PresenterPersonalizeSnack presenterPersonalizeSnack;


    @BindView(R.id.rvIngredient)
    RecyclerView rvIngredient;

    @BindView(R.id.ivSnaExtras)
    ImageView ivSnaExtras;

    @BindView(R.id.etTitleExtras)
    TextView etTitleExtras;

    @BindView(R.id.tvAmExtras)
    TextView tvAmExtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalize);
        ButterKnife.bind(this);

        rvIngredient.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvIngredient.setLayoutManager(llm);
        rvIngredient.setItemAnimator(new DefaultItemAnimator());
        //rvAddress.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), rvAddress, this));

        float dpHeight = Util.getDisplayMetrics().heightPixels;
        rvIngredient.setMinimumHeight(Math.round(dpHeight - Util.getStatusBarHeight()));


        presenterPersonalizeSnack = new PresenterPersonalizeSnack(this);
        presenterPersonalizeSnack.getISnackSelected();
        presenterPersonalizeSnack.getIngredients();


    }

    public void setAdpter(IngredietsPersonalizeAdapter ingredietsPersonalizeAdapter) {
        rvIngredient.setAdapter(ingredietsPersonalizeAdapter);

    }


    @OnClick(R.id.btAddCartExtras)
    public void addCart() {
        presenterPersonalizeSnack.addCart(Singleton.getInstance().getInSnack().getId());
    }

    public void goToCart() {
        Intent myIntent = new Intent(this, OrderActivity.class);
        this.startActivity(myIntent);
    }

    public void setDescription(InSnack item, String amount) {
        Picasso.get().load(item.getImage()).into(ivSnaExtras);
        etTitleExtras.setText(item.getName());
        tvAmExtras.setText(amount);


    }

    public void updateAmount(String amount){
        tvAmExtras.setText(amount);

    }
}


