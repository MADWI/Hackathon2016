package pl.edu.zut.mad.hackathon2016;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.Menu;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity {

    public static Intent createIntent(Context context)
    {
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

    @Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        final SearchView searchView = (SearchView)menu.findItem(R.id.action_search).getActionView();
        searchView.setSuggestionsAdapter(new SearchSuggestionsAdapter(this));
        searchView.setOnSuggestionListener(new SearchView.OnSuggestionListener()
        {
            @Override
            public boolean onSuggestionClick(int position)
            {
                Toast.makeText(SearchActivity.this, "Position: " + position, Toast.LENGTH_SHORT).show();
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onSuggestionSelect(int position)
            {
                return false;
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                Toast.makeText(SearchActivity.this, query, Toast.LENGTH_SHORT).show();
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                return false;
            }
        });

        return true;
    }

    public static class SearchSuggestionsAdapter extends SimpleCursorAdapter
    {
        private static final String[] mFields  = { "_id", "result" };
        private static final String[] mVisible = { "result" };
        private static final int[]    mViewIds = { android.R.id.text1 };

        public SearchSuggestionsAdapter(Context context)
        {
            super(context, android.R.layout.simple_list_item_1, null, mVisible, mViewIds, 0);
        }

        @Override
        public Cursor runQueryOnBackgroundThread(CharSequence constraint)
        {
            return new SuggestionsCursor(constraint);
        }

        private static class SuggestionsCursor extends AbstractCursor
        {
            private ArrayList<String> mResults;

            public SuggestionsCursor(CharSequence constraint)
            {
                final int count = 100;
                mResults = new ArrayList<>(count);
                for(int i = 0; i < count; i++){
                    mResults.add("Result " + (i + 1));
                }
                if(!TextUtils.isEmpty(constraint)){
                    String constraintString = constraint.toString().toLowerCase(Locale.ROOT);
                    Iterator<String> iter = mResults.iterator();
                    while(iter.hasNext()){
                        if(!iter.next().toLowerCase(Locale.ROOT).startsWith(constraintString))
                        {
                            iter.remove();
                        }
                    }
                }
            }

            @Override
            public int getCount()
            {
                return mResults.size();
            }

            @Override
            public String[] getColumnNames()
            {
                return mFields;
            }

            @Override
            public long getLong(int column)
            {
                if(column == 0){
                    return mPos;
                }
                throw new UnsupportedOperationException("unimplemented");
            }

            @Override
            public String getString(int column)
            {
                if(column == 1){
                    return mResults.get(mPos);
                }
                throw new UnsupportedOperationException("unimplemented");
            }

            @Override
            public short getShort(int column)
            {
                throw new UnsupportedOperationException("unimplemented");
            }

            @Override
            public int getInt(int column)
            {
                throw new UnsupportedOperationException("unimplemented");
            }

            @Override
            public float getFloat(int column)
            {
                throw new UnsupportedOperationException("unimplemented");
            }

            @Override
            public double getDouble(int column)
            {
                throw new UnsupportedOperationException("unimplemented");
            }

            @Override
            public boolean isNull(int column)
            {
                return false;
            }
        }
    }

    private void searchOrliks(String query){

    }
}
