package br.com.app.burg.burger.api;

import retrofit.RequestInterceptor;

public class HeaderAPI implements RequestInterceptor {

    public HeaderAPI() {

    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("Accept", "application/json");
        request.addHeader("Content-Type", "application/json");
    }
}
