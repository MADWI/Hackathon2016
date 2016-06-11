package pl.edu.zut.mad.hackathon2016.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pl.edu.zut.mad.hackathon2016.R;

/**
 * Created by Łukasz on 2016-06-11.
 */
public class SearchActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, SearchActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);
        Intent intent = getIntent();

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            searchOrliks(query);
        }
    }

    private void searchOrliks(String query){

    }
}

