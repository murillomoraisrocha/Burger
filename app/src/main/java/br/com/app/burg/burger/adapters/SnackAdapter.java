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

import java.util.ArrayList;
import java.util.List;

;import br.com.app.burg.burger.R;
import br.com.app.burg.burger.interfaces.RecyclerViewOnClickListenerHack;
import br.com.app.burg.burger.model.api.in.InIngredient;
import br.com.app.burg.burger.model.api.in.InIngredients;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.pattern.Singleton;
import br.com.app.burg.burger.utils.MyApplication;
import br.com.app.burg.burger.utils.Util;
//endregion


public class SnackAdapter extends RecyclerView.Adapter<SnackAdapter.MyViewHolder> {

    //region GLOBAL VARIABLES
    private List<InSnack> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    //endregion

    //region OVERRIDES
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;
        v = mLayoutInflater.inflate(R.layout.item_snack, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int position) {

        InSnack item = mList.get(position);
        myViewHolder.etTitleSnack.setText(item.getName());

        Picasso.get().load(item.getImage()).into(myViewHolder.ivBurger);
        myViewHolder.tvPrice.setText(Util.formatMoney(getAmountSnack(item.getIngredients()), true));

        if (getitemIngredient(item.getIngredients()) != null ){
            String teste = getitemIngredient(item.getIngredients());
            myViewHolder.tvItem.setText(teste);
        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    //endregion

    //region PUBLIC METHODS
    public SnackAdapter(List<InSnack> l) {
        mList = l;
        mLayoutInflater = (LayoutInflater) MyApplication.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        mRecyclerViewOnClickListenerHack = r;
    }

    public InSnack getItem(int position) {
        InSnack item = mList.get(position);
        return item;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView etTitleSnack;
        TextView tvPrice;
        TextView tvItem;
        ImageView ivBurger;

        public MyViewHolder(View itemView) {
            super(itemView);

            etTitleSnack = itemView.findViewById(R.id.etTitleSnack);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvItem = itemView.findViewById(R.id.tvItem);
            ivBurger = itemView.findViewById(R.id.ivBurger);
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

    public String getitemIngredient(List<Integer> inIngredients) {

        List<InIngredient> ing = Singleton.getInstance().getInIngredientList();
        String list = "";
        for (Integer idIngredient : inIngredients) {
            int id = idIngredient;

            if (ing.size() > 0) {
                for (InIngredient i : ing) {
                    if (i.getId() == id) {
                        list = list + " *" + i.getName();
                    }

                }
            }

        }
        return list;

    }
    //endregion
}