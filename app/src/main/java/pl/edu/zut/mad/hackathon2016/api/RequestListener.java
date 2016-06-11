package pl.edu.zut.mad.hackathon2016.api;

import retrofit.RetrofitError;

public interface RequestListener<T> {

    void onSuccess(T response);

    void onFailure(RetrofitError error);
}