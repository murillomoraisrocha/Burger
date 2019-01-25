package br.com.app.burg.burger.callback.Get;

import java.util.List;

import br.com.app.burg.burger.api.Module;
import br.com.app.burg.burger.model.api.in.InIngredient;
import br.com.app.burg.burger.utils.CancelableCallback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GetIngredients {

    GetIngredientsOnListener getIngredientsOnListener;

    public interface GetIngredientsOnListener {
        void successGetIngredientsOnListener(List<InIngredient> data);

        void failGetIngredientsOnListener(RetrofitError error);
    }

    public GetIngredients(GetIngredientsOnListener getIngredientsOnListener) {
        this.getIngredientsOnListener = getIngredientsOnListener;
    }

    public void get(String TAG) {
        Module.service(false).getIngredients(
                new CancelableCallback<List<InIngredient>>(TAG) {
                    @Override
                    public void onSuccess(List<InIngredient> list, Response response) {
                        getIngredientsOnListener.successGetIngredientsOnListener(list);
                    }

                    @Override
                    public void onFailure(RetrofitError error) {
                        getIngredientsOnListener.failGetIngredientsOnListener(error);
                    }
                });
    }

}
