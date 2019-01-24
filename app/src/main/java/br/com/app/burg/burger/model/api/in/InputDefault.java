package br.com.app.burg.burger.model.api.in;

import java.util.List;

public class InputDefault<T> {
    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}