package br.com.app.burg.burger.ServiceCalculate;

import java.util.List;

import br.com.app.burg.burger.model.api.in.InIngredient;
import br.com.app.burg.burger.model.api.in.InSnack;
import br.com.app.burg.burger.pattern.Singleton;

public class Calculate {

    double amount = 0.0;


    public double calculateAmount(List<Integer> inIngredients) {
        List<InIngredient> ing = Singleton.getInstance().getInIngredientList();

        if (ing != null) {
            for (Integer idIngrediet : inIngredients) {
                int id = idIngrediet;

                if (ing.size() > 0) {
                    for (InIngredient i : ing) {
                        if (i.getId() == id) {
                            amount =amount + i.getPrice();
                        }

                    }
                }

            }

        }

        return amount;

    }

    public double calculateAmountWithExtras(InIngredient inIngredients, double amountSnack) {
        amount = amountSnack;
        amount += inIngredients.getPrice();
        return amount;

    }

    public double calculatePromotionLight(double amountSnack) {
        amount = amountSnack -  (amountSnack * 0.1);
        return amount;

    }

}
