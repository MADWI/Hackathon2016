package pl.edu.zut.mad.hackathon2016;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.edu.zut.mad.hackathon2016.activities.ReservationActivity;
import pl.edu.zut.mad.hackathon2016.model.Orlik;

public class TypeSelectorDialog extends android.support.v4.app.DialogFragment {

    private Orlik orlik = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        orlik = (Orlik) getArguments().getSerializable("orlik");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.selector_dialog, container);
        ButterKnife.bind(this, view);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(0));
        return view;
    }

    @OnClick({ R.id.football, R.id.volleyball, R.id.basketball, R.id.ping_pong })
    public void pickOrlikType(ImageView type) {
        startActivity(
                new Intent(getContext(), ReservationActivity.class)
                .putExtra("orlik", orlik)
        );
        dismiss();
    }
}
