package br.com.app.burg.burger.callback.Get;

import java.util.List;

import br.com.app.burg.burger.api.Module;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.utils.CancelableCallback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GetIngredientsSnacks {

    GetIngredientsSnacksOnListener getIngredientsSnacksOnListener;

    public interface GetIngredientsSnacksOnListener {
        void successGetIngredientsSnacksOnListener(InSnack data);

        void failGetIngredientsSnacksOnListener(RetrofitError error);
    }

    public GetIngredientsSnacks(GetIngredientsSnacksOnListener getIngredientsSnacksOnListener) {
        this.getIngredientsSnacksOnListener = getIngredientsSnacksOnListener;
    }

    public void get(String TAG, int lanche_id) {
        Module.service(false).getIngredientsSnack(
                lanche_id,
                new CancelableCallback<InSnack>(TAG) {
                    @Override
                    public void onSuccess(InSnack list, Response response) {
                        getIngredientsSnacksOnListener.successGetIngredientsSnacksOnListener(list);
                    }

                    @Override
                    public void onFailure(RetrofitError error) {
                        getIngredientsSnacksOnListener.failGetIngredientsSnacksOnListener(error);
                    }
                });
    }

}
