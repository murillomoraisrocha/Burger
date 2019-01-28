package br.com.app.burg.burger.ServiceCalculate;

import java.util.List;

import br.com.app.burg.burger.pattern.Singleton;


public class Promotions {

    public boolean Light(List<Integer> inIngredients) {
        boolean hasCheese = false;
        boolean hasBacon = false;

        for (Integer idIngrediet : inIngredients) {
            Integer id = idIngrediet;
            if (id == 5){
                hasCheese = true;
            }

            if (id == 2){
                hasBacon = true;
            }

        }

        if (! hasBacon  && hasCheese){
            return true;
        }
        return  false;
    }



}
