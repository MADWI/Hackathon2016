package pl.edu.zut.mad.hackathon2016.api;

import retrofit.RestAdapter;

public class RestClient {
    public static final String BASE_URL = "http://bm29640.zut.edu.pl/orl_api/";
    private RestInterface restInterface;

    public RestClient(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .build();

        restInterface = restAdapter.create(RestInterface.class);
    }

    public RestInterface getService(){
        return restInterface;
    }
}
