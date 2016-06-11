package pl.edu.zut.mad.hackathon2016.api;

import retrofit.RestAdapter;

public class RestClient {
    private RestInterface restInterface;

    public RestClient(String BASE_URL){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(BASE_URL)
                .build();

        restInterface = restAdapter.create(RestInterface.class);
    }

    public RestInterface getService(){
        return restInterface;
    }
}
