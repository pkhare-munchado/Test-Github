package com.android.gifts.butterknife;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.gifts.butterknife.EventModel.ClickEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FragmentA extends Fragment {

    @BindView(R.id.fragment_text_view) TextView fragmentTextView;

    @BindView(R.id.fragment_layout) FrameLayout fragmentContainer;

    @BindColor(R.color.colorAccent) int fancyColor;
    @BindColor(R.color.colorPrimaryDark) int fancyColor2;
    @BindColor(android.R.color.white) int whiteColor;
    private EventBus bus=EventBus.getDefault();
    public FragmentA() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_a, container, false);

        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick(R.id.color_fragment_btn)
    public void colorFragment() {
        setBackground(fancyColor2);
    }

   /* @Subscribe
    public void onClickEvent(ClickEvent evt)
    {
        setBackground(fancyColor);

    }*/
    @Subscribe
    public void onEvent(String str)
    {
        setBackground(fancyColor);
    }
    private void setBackground(int color)
    {
        fragmentContainer.setBackgroundColor(color);

        fragmentTextView.setTextColor(whiteColor);
    }

    @Override
    public void onStart() {
        super.onStart();
        bus.register(this);
    }

    @Override
    public void onStop() {
        bus.unregister(this);
        super.onStop();
    }
}
