package pl.edu.zut.mad.hackathon2016;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.edu.zut.mad.hackathon2016.api.RequestListener;
import pl.edu.zut.mad.hackathon2016.model.Orlik;
import retrofit.RetrofitError;

/**
 * Created by mb on 11.06.16.
 */
public class OrliksListFragment extends Fragment implements RequestListener<List<Orlik>> {

    public static final String ARG_MODE = "D_MODE";
    public static final int MODE_MY_RESERVATIONS = 1;
    public static final int MODE_FAVORITES = 2;
    public static final int MODE_ALL = 3;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private Adapter adapter;
    boolean mHasBallsRow = true;
    private int mMode;

    List<Orlik> mEntries = Collections.emptyList();
    List<Orlik> allOrliks = Collections.emptyList();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMode = getArguments().getInt(ARG_MODE);
        mHasBallsRow = mMode == MODE_ALL;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orlik_list, container, false);
        ButterKnife.bind(this, view);
        adapter = new Adapter();
        mRecyclerView.setAdapter(adapter);

        if (mMode == MODE_FAVORITES) {
            loadFavourites();
        } else {
            DataProvider.getOrliks(this);
        }

        return view;
    }

    private void loadFavourites() {
        mEntries = DataProvider.getFavouritesOrliks();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (mMode == MODE_FAVORITES) {
            loadFavourites();
        }
    }

    @Override
    public void onSuccess(List<Orlik> response) {
        mEntries = response;
        allOrliks = new ArrayList<>(mEntries);

        adapter.notifyDataSetChanged();

        for (Orlik orlik : response) {
            orlik.save();
        }
    }

    @Override
    public void onFailure(RetrofitError error) {

    }

    class BaseViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.football_icon)
        ImageView footballView;

        @Bind(R.id.volleyball_icon)
        ImageView volleyballView;

        @Bind(R.id.basketball_icon)
        ImageView basketballView;

        @Bind(R.id.ping_pong_icon)
        ImageView pingPong;

        BaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setDefaultColors() {
            footballView.clearColorFilter();
            volleyballView.clearColorFilter();
            basketballView.clearColorFilter();
            pingPong.clearColorFilter();
        }
    }

    class FilterRowViewHolder extends BaseViewHolder {
        final int color;

        FilterRowViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.orlik_list_filter, parent, false));
            color = ContextCompat.getColor(getContext(), R.color.colorAccent);
        }

        @OnClick(R.id.football_icon)
        public void onClickFootball() {
            setDefaultColors();
            footballView.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

            mEntries.clear();
            mEntries.addAll(allOrliks);

            adapter.notifyDataSetChanged();
        }

        @OnClick(R.id.volleyball_icon)
        public void onClickVolley() {
            setDefaultColors();
            volleyballView.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

            mEntries.clear();

            for (Orlik orlik : allOrliks) {
                if (orlik.getType() == 2)
                    mEntries.add(orlik);
            }
            adapter.notifyDataSetChanged();
        }

        @OnClick(R.id.basketball_icon)
        public void onClickBasket() {
            setDefaultColors();
            basketballView.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

            mEntries.clear();

            for (Orlik orlik : allOrliks) {
                if (orlik.getType() == 2)
                    mEntries.add(orlik);
            }
            adapter.notifyDataSetChanged();
            adapter.notifyDataSetChanged();
        }

        @OnClick(R.id.ping_pong_icon)
        public void onClickPingPong() {
            setDefaultColors();
            pingPong.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);

            mEntries.clear();

            for (Orlik orlik : allOrliks) {
                if (orlik.getType() == 3)
                    mEntries.add(orlik);
            }
            adapter.notifyDataSetChanged();
        }
    }

    class OrlikRowViewHolder extends BaseViewHolder implements View.OnClickListener {
        @Bind(R.id.orlik_name)
        TextView mNameTextView;

        @Bind(R.id.favourite_icon)
        ImageView mFavoriteIcon;

        @Bind(R.id.football_icon)
        ImageView footballView;

        @Bind(R.id.volleyball_icon)
        ImageView volleyballView;

        @Bind(R.id.basketball_icon)
        ImageView basketballView;

        @Bind(R.id.ping_pong_icon)
        ImageView pingPong;

        OrlikRowViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.orlik_list_item, parent, false));

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Orlik argument = mEntries.get(getAdapterPosition());
            Bundle args = new Bundle();
            args.putSerializable("orlik", argument);
            TypeSelectorDialog typeSelectorDialog = new TypeSelectorDialog();
            typeSelectorDialog.setArguments(args);
            typeSelectorDialog.show(getActivity().getFragmentManager(), "SelectorDialog");
        }
    }


    private class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == R.layout.orlik_list_item) {
                return new OrlikRowViewHolder(parent);
            } else {
                return new FilterRowViewHolder(parent);
            }
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof OrlikRowViewHolder) {
                final Orlik orlik = mEntries.get(position - (mHasBallsRow ? 1 : 0));
                final OrlikRowViewHolder oHolder = (OrlikRowViewHolder) holder;
                oHolder.mNameTextView.setText(orlik.getAdres());

                final int colorSport = ContextCompat.getColor(getContext(), R.color.colorAccent);
                final int colorFavourite = ContextCompat.getColor(getContext(), R.color.colorPrimary);

                oHolder.setDefaultColors();
                oHolder.mFavoriteIcon.clearColorFilter();

                if (orlik.getType() == 1) {
                    oHolder.footballView.setColorFilter(colorSport, PorterDuff.Mode.SRC_ATOP);
                }

                if (orlik.getType() == 2) {
                    oHolder.footballView.setColorFilter(colorSport, PorterDuff.Mode.SRC_ATOP);
                    oHolder.volleyballView.setColorFilter(colorSport, PorterDuff.Mode.SRC_ATOP);
                    oHolder.basketballView.setColorFilter(colorSport, PorterDuff.Mode.SRC_ATOP);
                }

                if (orlik.getType() == 3) {
                    oHolder.footballView.setColorFilter(colorSport, PorterDuff.Mode.SRC_ATOP);
                    oHolder.volleyballView.setColorFilter(colorSport, PorterDuff.Mode.SRC_ATOP);
                    oHolder.basketballView.setColorFilter(colorSport, PorterDuff.Mode.SRC_ATOP);
                    oHolder.pingPong.setColorFilter(colorSport, PorterDuff.Mode.SRC_ATOP);
                }

                if(orlik.isFavourite()){
                    oHolder.mFavoriteIcon.setColorFilter(colorFavourite, PorterDuff.Mode.SRC_ATOP);
                }

                oHolder.mFavoriteIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        orlik.setFavourite(!orlik.isFavourite());
                        orlik.save();

                        if (orlik.isFavourite()) {
                            oHolder.mFavoriteIcon.setColorFilter(colorFavourite, PorterDuff.Mode.SRC_ATOP);
                        } else {
                            oHolder.mFavoriteIcon.clearColorFilter();
                        }
                    }
                });

                if (mMode == MODE_FAVORITES) {
                    oHolder.mFavoriteIcon.setColorFilter(colorFavourite, PorterDuff.Mode.SRC_ATOP);
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            return position == 0 && mHasBallsRow ? 1234 : R.layout.orlik_list_item;
        }

        @Override
        public int getItemCount() {
            if (mEntries == null) {
                return 0;
            }
            return (mHasBallsRow ? 1 : 0) + mEntries.size();
        }

    }
}
