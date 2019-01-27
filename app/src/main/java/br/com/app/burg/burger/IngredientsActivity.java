package br.com.app.burg.burger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.app.burg.burger.Presenter.PresenterIngredients;
import br.com.app.burg.burger.Presenter.PresenterSnack;
import br.com.app.burg.burger.adapters.IngredietsAdapter;
import br.com.app.burg.burger.adapters.SnackAdapter;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.pattern.Singleton;
import br.com.app.burg.burger.utils.Util;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IngredientsActivity extends AppCompatActivity {

    private PresenterIngredients presenterIngredient;


   @BindView(R.id.rvIngredients)
   RecyclerView rvIngredients;

    @BindView(R.id.ivSna)
    ImageView ivSna;

    @BindView(R.id.etTitleS)
    TextView etTitleS;

    @BindView(R.id.tvAm)
    TextView tvAm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredients);
        ButterKnife.bind(this);

        rvIngredients.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvIngredients.setLayoutManager(llm);
        rvIngredients.setItemAnimator(new DefaultItemAnimator());
        //rvAddress.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), rvAddress, this));

        float dpHeight = Util.getDisplayMetrics().heightPixels;
        rvIngredients.setMinimumHeight(Math.round(dpHeight - Util.getStatusBarHeight()));

        presenterIngredient = new PresenterIngredients (this);
        presenterIngredient.getISnackSelected();
        presenterIngredient.getIngredients(Singleton.getInstance().getInSnack());

    }

    public void setAdpter(IngredietsAdapter ingredietsAdapter){
        rvIngredients.setAdapter(ingredietsAdapter);

    }

    public void setDescription(InSnack item, String amount){
        Picasso.get().load(item.getImage()).into(ivSna);
        etTitleS.setText(item.getName());
        tvAm.setText(amount);

    }

    @OnClick(R.id.btAddCart)
    public void addCart() {
        presenterIngredient.addCart(Singleton.getInstance().getInSnack().getId());
    }




}


