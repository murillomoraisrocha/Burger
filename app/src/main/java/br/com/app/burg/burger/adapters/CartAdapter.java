package br.com.app.burg.burger.adapters;

//region IMPORTS

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.app.burg.burger.Presenter.PresenterOrders;
import br.com.app.burg.burger.R;
import br.com.app.burg.burger.ServiceCalculate.Calculate;
import br.com.app.burg.burger.interfaces.RecyclerViewOnClickListenerHack;
import br.com.app.burg.burger.model.api.in.InIngredient;
import br.com.app.burg.burger.model.api.in.InOrder;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.pattern.Singleton;
import br.com.app.burg.burger.utils.MyApplication;
import br.com.app.burg.burger.utils.Util;

;
//endregion

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {

    //region GLOBAL VARIABLES
    private List<InOrder> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    private PresenterOrders presenterOrders;
    private double teste = 0d;
    //endregion

    //region OVERRIDES
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;
        v = mLayoutInflater.inflate(R.layout.item_cart, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int position) {

        InOrder item = mList.get(position);
        InSnack lanche = getSnack(Integer.parseInt(item.getId_sandwich()));

        myViewHolder.etTitleSnackC.setText(lanche.getName());
        Picasso.get().load(lanche.getImage()).into(myViewHolder.ivBurgerC);

        Calculate calculate = new Calculate();
        myViewHolder.tvPriceC.setText(Util.formatMoney(calculate.calculateAmount(lanche.getIngredients()), true));

        if (item.getExtras().size() < 1) {
            myViewHolder.tvDesc.setVisibility(View.INVISIBLE);
        } else {
            myViewHolder.tvDesc.setText("“ - do seu jeito");

        }


//        double cartamount = Singleton.getInstance().getCartAmount() + calculate.calculateAmount(lanche.getIngredients());
//        Singleton.getInstance().setCartAmount(cartamount);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    //endregion

    //region PUBLIC METHODS
    public CartAdapter(List<InOrder> l) {
        mList = l;
        mLayoutInflater = (LayoutInflater) MyApplication.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        mRecyclerViewOnClickListenerHack = r;
    }

    public InOrder getItem(int position) {
        InOrder item = mList.get(position);
        return item;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView tvPriceC;
        TextView tvDesc;
        TextView etTitleSnackC;
        ImageView ivBurgerC;

        public MyViewHolder(View itemView) {
            super(itemView);

            tvPriceC = itemView.findViewById(R.id.tvPriceC);
            tvDesc = itemView.findViewById(R.id.tvDesc);
            etTitleSnackC = itemView.findViewById(R.id.etTitleSnackC);
            ivBurgerC = itemView.findViewById(R.id.ivBurgerC);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (mRecyclerViewOnClickListenerHack != null) {
                mRecyclerViewOnClickListenerHack.onClickListener(v, getAdapterPosition(), getItem(getAdapterPosition()));
            }
        }

        @Override
        public boolean onLongClick(View v) {

            if (mRecyclerViewOnClickListenerHack != null) {
                mRecyclerViewOnClickListenerHack.onLongPressClickListener(v, getAdapterPosition(), getItem(getAdapterPosition()));
            }
            return true;
        }


    }

    public InSnack getSnack(int lanche_id) {
        List<InSnack> lanches = Singleton.getInstance().getSnacks();
        InSnack snack = new InSnack();

        if (lanches.size() > 0) {
            for (InSnack i : lanches) {
                if (i.getId() == lanche_id) {
                    snack = i;
                }

            }
        }

        return snack;
    }


    //endregion
}