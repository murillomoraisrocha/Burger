package br.com.app.burg.burger.pattern;


import java.util.List;
import br.com.app.burg.burger.model.api.in.InIngredient;
import br.com.app.burg.burger.model.api.in.InSnack;


public class Singleton {

    private static Singleton mInstance = null;
    private List<InIngredient> inIngredientList;
    private InSnack inSnack;


    public static Singleton getInstance() {
        if (mInstance == null) {
            mInstance = new Singleton();
        }
        return mInstance;
    }

    public List<InIngredient> getInIngredientList() {
        return inIngredientList;
    }

    public void setInIngredientList(List<InIngredient> inIngredientList) {
        this.inIngredientList = inIngredientList;
    }

    public InSnack getInSnack() {
        return inSnack;
    }

    public void setInSnack(InSnack inSnack) {
        this.inSnack = inSnack;
    }
}