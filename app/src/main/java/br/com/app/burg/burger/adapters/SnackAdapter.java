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

;import br.com.app.burg.burger.R;
import br.com.app.burg.burger.interfaces.RecyclerViewOnClickListenerHack;
import br.com.app.burg.burger.model.api.in.InIngredient;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.pattern.Singleton;
import br.com.app.burg.burger.utils.MyApplication;
import br.com.app.burg.burger.utils.Util;
//endregion

/**
 * Created by Márcio Lima on 4/5/15.
 */
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

//        myViewHolder.tvAddress.setText(String.format("%s, %s\n%s/%s", item.getStreet(), item.getNumber(), item.getCity(), item.getState()));
//        if (position % 2 == 0) {
//            myViewHolder.rlAddress.setBackgroundColor(ContextCompat.getColor(MyApplication.getContext(), R.color.gray2));
//        } else {
//            myViewHolder.rlAddress.setBackgroundColor(ContextCompat.getColor(MyApplication.getContext(), R.color.gray4));
//        }

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
        ImageView ivBurger;

        public MyViewHolder(View itemView) {
            super(itemView);

            etTitleSnack = itemView.findViewById(R.id.etTitleSnack);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ivBurger = itemView.findViewById(R.id.ivBurger);


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