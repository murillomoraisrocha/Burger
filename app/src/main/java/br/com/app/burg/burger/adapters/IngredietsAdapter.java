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

import br.com.app.burg.burger.R;
import br.com.app.burg.burger.interfaces.RecyclerViewOnClickListenerHack;
import br.com.app.burg.burger.model.api.in.InIngredient;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.pattern.Singleton;
import br.com.app.burg.burger.utils.MyApplication;
import br.com.app.burg.burger.utils.Util;

;
//endregion

/**
 * Created by Márcio Lima on 4/5/15.
 */
public class IngredietsAdapter extends RecyclerView.Adapter<IngredietsAdapter.MyViewHolder> {

    //region GLOBAL VARIABLES
    private List<InIngredient> mList;
    private LayoutInflater mLayoutInflater;
    private RecyclerViewOnClickListenerHack mRecyclerViewOnClickListenerHack;
    //endregion

    //region OVERRIDES
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v;
        v = mLayoutInflater.inflate(R.layout.item_ingredients, viewGroup, false);
        MyViewHolder mvh = new MyViewHolder(v);

        return mvh;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, int position) {

        InIngredient item = mList.get(position);
        myViewHolder.etNameIngredient.setText(item.getName());
        Picasso.get().load(item.getImage()).into(myViewHolder.ivIngredient);


        myViewHolder.etAmountIngredient.setText(Util.formatMoney(getAmountIngredient(item), true));


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    //endregion

    //region PUBLIC METHODS
    public IngredietsAdapter(List<InIngredient> l) {
        mList = l;
        mLayoutInflater = (LayoutInflater) MyApplication.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setRecyclerViewOnClickListenerHack(RecyclerViewOnClickListenerHack r) {
        mRecyclerViewOnClickListenerHack = r;
    }

    public InIngredient getItem(int position) {
        InIngredient item = mList.get(position);
        return item;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView etNameIngredient;
        TextView etAmountIngredient;
        ImageView ivIngredient;

        public MyViewHolder(View itemView) {
            super(itemView);

            etNameIngredient = itemView.findViewById(R.id.etNameIngredient);
            etAmountIngredient = itemView.findViewById(R.id.etAmountIngredient);
            ivIngredient = itemView.findViewById(R.id.ivIngredient);
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

    public double getAmountIngredient(InIngredient inIngredients) {

        double amount = 0d;
        List<InIngredient> ing = Singleton.getInstance().getInIngredientList();

            int id = inIngredients.getId();

            if (ing.size() > 0) {
                for (InIngredient i : ing) {
                    if (i.getId() == id) {
                        amount += i.getPrice();
                    }

                }
        }

        return amount;

    }



    //endregion
}