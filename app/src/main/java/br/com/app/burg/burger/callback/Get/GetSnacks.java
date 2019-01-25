package br.com.app.burg.burger.callback.Get;

import java.util.List;

import br.com.app.burg.burger.api.Module;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.model.api.in.InputDefault;
import br.com.app.burg.burger.utils.CancelableCallback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GetSnacks {

    GetSnacksOnListener getSnacksOnListener;

    public interface GetSnacksOnListener {
        void successGetSnacksOnListener(List<InSnack> data);

        void failGetSnacksOnListener(RetrofitError error);
    }

    public GetSnacks(GetSnacksOnListener getSnacksOnListener) {
        this.getSnacksOnListener = getSnacksOnListener;
    }

    public void get(String TAG) {
        Module.service(false).getSnack(
                new CancelableCallback<List<InSnack>>(TAG) {
                    @Override
                    public void onSuccess(List<InSnack> list, Response response) {
                        getSnacksOnListener.successGetSnacksOnListener(list);
                    }

                    @Override
                    public void onFailure(RetrofitError error) {
                        getSnacksOnListener.failGetSnacksOnListener(error);
                    }
                });
    }

}
