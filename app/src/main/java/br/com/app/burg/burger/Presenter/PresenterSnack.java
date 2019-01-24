package br.com.app.burg.burger.Presenter;

import java.util.List;

import br.com.app.burg.burger.MainActivity;
import br.com.app.burg.burger.callback.Get.GetSnacks;
import br.com.app.burg.burger.model.api.in.InSnack;
import retrofit.RetrofitError;

public class PresenterSnack implements GetSnacks.GetSnacksOnListener {

    private MainActivity mainActivity;

    public PresenterSnack(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void getSnacks(){
        GetSnacks getSnacks = new GetSnacks(this);
        getSnacks.get("");

    }


    @Override
    public void successGetSnacksOnListener(List<InSnack> data) {

    }

    @Override
    public void failGetSnacksOnListener(RetrofitError error) {

    }
}
