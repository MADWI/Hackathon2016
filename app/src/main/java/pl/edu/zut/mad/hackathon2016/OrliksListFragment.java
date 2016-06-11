package pl.edu.zut.mad.hackathon2016;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.edu.zut.mad.hackathon2016.api.RequestCallback;
import pl.edu.zut.mad.hackathon2016.api.RequestListener;
import pl.edu.zut.mad.hackathon2016.api.RestClientManager;
import pl.edu.zut.mad.hackathon2016.model.Orlik;
import retrofit.RetrofitError;

/**
 * Created by mb on 11.06.16.
 */
public class OrliksListFragment extends Fragment {


    public static final String ARG_MODE = "D_MODE";
    public static final int MODE_MY_RESERVATIONS = 1;
    public static final int MODE_FAVORITES = 2;
    public static final int MODE_ALL = 3;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    boolean mHasBallsRow = true;

    List<Orlik> mEntries = Collections.emptyList();
    private int mMode;
    private Adapter adapter;

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
        RestClientManager.getAllOrliks(new RequestCallback<>(new RequestListener<List<Orlik>>() {
            @Override
            public void onSuccess(List<Orlik> response) {
                mEntries = response;
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(RetrofitError error) {

            }
        }));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
    }

    class FilterRowViewHolder extends BaseViewHolder  {
        final int color;
        FilterRowViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.orlik_list_filter, parent, false));

           color  = ContextCompat.getColor(getContext(), R.color.colorAccent);
        }

        @OnClick(R.id.football_icon)
        public void onClickFootball(){
            footballView.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        }

        @OnClick(R.id.volleyball_icon)
        public void onClickVolley(){
            volleyballView.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        }

        @OnClick(R.id.basketball_icon)
        public void onClickBasket(){
            basketballView.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        }

        @OnClick(R.id.ping_pong_icon)
        public void onClickPingPong(){
            pingPong.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
        }
    }

    private class OrlikRowViewHolder extends BaseViewHolder implements View.OnClickListener {
        TextView mNameTextView;
        ImageView mFavoriteIcon;

        OrlikRowViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.orlik_list_item, parent, false));
            mNameTextView = (TextView) itemView.findViewById(R.id.orlik_name);
            mFavoriteIcon = (ImageView) itemView.findViewById(R.id.favourite_icon);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            TypeSelectorDialog typeSelectorDialog = new TypeSelectorDialog();
            typeSelectorDialog.show(getActivity().getFragmentManager(), "SelectorDialog");
            Log.d("TAG", "AG");
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
                Orlik orlik = mEntries.get(position - (mHasBallsRow ? 1 : 0));
                OrlikRowViewHolder oHolder = (OrlikRowViewHolder) holder;
                oHolder.mNameTextView.setText(orlik.getAdres());
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
