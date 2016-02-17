package com.jbtest.testfragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TestFragment extends Fragment {
    public TestFragment() {
        // Required empty public constructor
    }

    public static TestFragment newInstance() {
        TestFragment fragment = new TestFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstance) {
        super.onViewStateRestored(savedInstance);
        Log.d("TestFragment", "onViewStateRestored");

        try {
            getView().findViewById(R.id.btnText).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    TestDialogFragment dialogFragment = TestDialogFragment.newInstance();
                    dialogFragment.show(ft, "TestDialog");
                }
            });
        } catch (Exception e) {
            Log.e("TEST", "caught exception!", e);
        }
    }


    public void setLabelText(String text) {
        // getview is null when called from actiivty dialog listener after orientation change!!
        try {
            ((TextView)getView().findViewById(R.id.lblText)).setText(text);
        } catch (Exception e) {
            Log.e("TEST", "caught exception!", e);
        }
    }

}
