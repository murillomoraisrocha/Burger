package br.com.app.burg.burger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import br.com.app.burg.burger.Presenter.PresenterOrders;

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





}


