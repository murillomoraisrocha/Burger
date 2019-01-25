package br.com.app.burg.burger.interfaces;

import android.view.View;

/**
 * Created by Murillo Lima on 24/1/19.
 */
public interface RecyclerViewOnClickListenerHack<T> {
    public void onClickListener(View view, int position, T item);
    public void onLongPressClickListener(View view, int position, T item);
}
