package br.com.app.burg.burger.pattern;


import java.util.List;
import br.com.app.burg.burger.model.api.in.InIngredient;
import br.com.app.burg.burger.model.api.in.InSnack;


public class Singleton {

    private static Singleton mInstance = null;
    private List<InIngredient> inIngredientList;
    private InSnack inSnack;
    private List<InSnack> snacks;
    private double cartAmount;
    private int counCheese;




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

    public List<InSnack> getSnacks() {
        return snacks;
    }

    public void setSnacks(List<InSnack> snacks) {
        this.snacks = snacks;
    }

    public double getCartAmount() {
        return cartAmount;
    }

    public void setCartAmount(double cartAmount) {
        this.cartAmount = cartAmount;
    }

    public int getCounCheese() {
        return counCheese;
    }

    public void setCounCheese(int counCheese) {
        this.counCheese = counCheese;
    }
}