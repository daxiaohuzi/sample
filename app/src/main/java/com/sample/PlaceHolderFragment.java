package com.sample;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.library.Activity.Fragment.BasePagerFragment;


public class PlaceHolderFragment extends BasePagerFragment<MainActivity> implements View.OnClickListener {
    Handler handler = new Handler();
    ProgressBar progressBar;
    TextView textView;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final int DELAY_TIME = 2000;

    public PlaceHolderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceHolderFragment newInstance(int sectionNumber) {
        PlaceHolderFragment fragment = new PlaceHolderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void init() {
        super.init();
        progressBar = (ProgressBar) getView().findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        textView = (TextView) getView().findViewById(R.id.section_label);
        textView.setOnClickListener(this);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
                progressBar.setVisibility(View.GONE);
            }
        }, DELAY_TIME);
    }


    @Override
    protected int getContentView() {
        return R.layout.fragment_main;
    }


    @Override
    public void onClick(View v) {
//        getSelftActivity().addFragment(new CanvasFragment());

        //动画效果 很神奇
        getSelftActivity().addFragment(new GuideFragment());
    }
}
