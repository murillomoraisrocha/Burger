package br.com.app.burg.burger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.mobsandgeeks.saripaar.Validator;

import br.com.app.burg.burger.Presenter.PresenterSnack;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity  {

    private PresenterSnack presenterSnack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenterSnack = new PresenterSnack(this);
        presenterSnack.getSnacks();
    }



}


