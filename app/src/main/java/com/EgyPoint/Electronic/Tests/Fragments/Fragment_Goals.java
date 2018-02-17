package com.EgyPoint.Electronic.Tests.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.EgyPoint.Electronic.Tests.R;

import ooo.oxo.library.widget.TouchImageView;

/**
 * Created by Delta on 15/02/2018.
 */

public class Fragment_Goals extends Fragment {
    TouchImageView imageView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_goal,container,false);
        initView(view);
        getDataFromBundle();
        return view;
    }

    private void getDataFromBundle() {
        Bundle bundle = getArguments();
        if (bundle!=null)
        {
            if (bundle.get("goal")!=null)
            {
                setImageTitle();

            }
        }
    }

    private void setImageTitle() {
        imageView.setImageResource(R.drawable.test2);
    }

    private void initView(View view) {
        imageView = view.findViewById(R.id.goal);


    }
}
