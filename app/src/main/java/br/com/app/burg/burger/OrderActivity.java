package br.com.app.burg.burger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.List;

import br.com.app.burg.burger.Presenter.PresenterOrders;

import br.com.app.burg.burger.adapters.CartAdapter;
import br.com.app.burg.burger.adapters.SnackAdapter;
import br.com.app.burg.burger.model.api.in.InOrder;
import br.com.app.burg.burger.pattern.Singleton;
import br.com.app.burg.burger.utils.Util;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends AppCompatActivity {

    private PresenterOrders presenterOrders;


    @BindView(R.id.rvOrder)
    RecyclerView rvOrder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);

        rvOrder.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rvOrder.setLayoutManager(llm);
        rvOrder.setItemAnimator(new DefaultItemAnimator());
        //rvAddress.addOnItemTouchListener(new RecyclerViewTouchListener(getActivity(), rvAddress, this));

        float dpHeight = Util.getDisplayMetrics().heightPixels;
        rvOrder.setMinimumHeight(Math.round(dpHeight - Util.getStatusBarHeight()));

        presenterOrders = new PresenterOrders(this);
        presenterOrders.getOrders();
//        presenterSnack.getSnacks();


    }


    public void setAdpter(CartAdapter cartAdapter) {
        rvOrder.setAdapter(cartAdapter);
    }



}

