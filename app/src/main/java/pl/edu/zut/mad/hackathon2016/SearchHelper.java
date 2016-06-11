package pl.edu.zut.mad.hackathon2016;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/**
 * Created by Łukasz on 2016-06-11.
 */
public class SearchHelper implements SearchView.OnSuggestionListener, SearchView.OnQueryTextListener {
    SearchManager searchManager;
    SearchView searchView;

    public void setSearchView(Activity activity, Menu menu) {
        searchManager = (SearchManager) activity.getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.getComponentName()));
        searchView.setOnSuggestionListener(this);
        searchView.setSuggestionsAdapter(new SearchSuggestionsAdapter(activity));
        searchView.setOnQueryTextListener(this);
    }

    @Override
    public boolean onSuggestionSelect(int position) {
        Log.i("Suggestion: ", position + "___");
        searchView.clearFocus();
        return true;
    }

    @Override
    public boolean onSuggestionClick(int position) {
        return false;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.i("Query: ", query);
        searchView.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    public static class SearchSuggestionsAdapter extends SimpleCursorAdapter {
        private static final String[] mFields = {"_id", "result"};
        private static final String[] mVisible = {"result"};
        private static final int[] mViewIds = {android.R.id.text1};

        public SearchSuggestionsAdapter(Activity activity) {
            super(activity, android.R.layout.simple_list_item_1, null, mVisible, mViewIds, 0);
        }

        @Override
        public Cursor runQueryOnBackgroundThread(CharSequence constraint) {
            return new SuggestionsCursor(constraint);
        }

        private static class SuggestionsCursor extends AbstractCursor {
            private ArrayList<String> mResults;

            public SuggestionsCursor(CharSequence constraint) {
                final int count = 100;
                mResults = new ArrayList<>(count);
                for (int i = 0; i < count; i++) {
                    mResults.add("Result " + (i + 1));
                }
                if (!TextUtils.isEmpty(constraint)) {
                    String constraintString = constraint.toString().toLowerCase(Locale.ROOT);
                    Iterator<String> iter = mResults.iterator();
                    while (iter.hasNext()) {
                        if (!iter.next().toLowerCase(Locale.ROOT).startsWith(constraintString)) {
                            iter.remove();
                        }
                    }
                }
            }

            @Override
            public int getCount() {
                return mResults.size();
            }

            @Override
            public String[] getColumnNames() {
                return mFields;
            }

            @Override
            public long getLong(int column) {
                if (column == 0) {
                    return mPos;
                }
                throw new UnsupportedOperationException("unimplemented");
            }

            @Override
            public String getString(int column) {
                if (column == 1) {
                    return mResults.get(mPos);
                }
                throw new UnsupportedOperationException("unimplemented");
            }

            @Override
            public short getShort(int column) {
                throw new UnsupportedOperationException("unimplemented");
            }

            @Override
            public int getInt(int column) {
                throw new UnsupportedOperationException("unimplemented");
            }

            @Override
            public float getFloat(int column) {
                throw new UnsupportedOperationException("unimplemented");
            }

            @Override
            public double getDouble(int column) {
                throw new UnsupportedOperationException("unimplemented");
            }

            @Override
            public boolean isNull(int column) {
                return false;
            }
        }
    }
}

