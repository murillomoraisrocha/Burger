package br.com.app.burg.burger.callback.Get;

import java.util.List;

import br.com.app.burg.burger.api.Module;
import br.com.app.burg.burger.model.api.in.InOrder;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.utils.CancelableCallback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class GetOrders {

    GetOrdersOnListener getOrdersOnListener;

    public interface GetOrdersOnListener {
        void successGetOrdersOnListener(List<InOrder> data);

        void failGetOrdersOnListener(RetrofitError error);
    }

    public GetOrders(GetOrdersOnListener getOrdersOnListener) {
        this.getOrdersOnListener = getOrdersOnListener;
    }

    public void get(String TAG) {
        Module.service(false).getOrders(
                new CancelableCallback<List<InOrder>>(TAG) {
                    @Override
                    public void onSuccess(List<InOrder> list, Response response) {
                        getOrdersOnListener.successGetOrdersOnListener(list);
                    }

                    @Override
                    public void onFailure(RetrofitError error) {
                        getOrdersOnListener.failGetOrdersOnListener(error);
                    }
                });
    }

}
