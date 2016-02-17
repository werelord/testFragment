package com.jbtest.testfragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;


public class TestDialogFragment extends DialogFragment {
    public interface TestDialogListener {
        void onUpdateText(String text);
    }

    TestDialogListener mListener;

    public static TestDialogFragment newInstance() {
        return new TestDialogFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the CustomDialogListener so we can send events to the host
            mListener = (TestDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString() + " must implement CustomDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        AlertDialog.Builder b = new AlertDialog.Builder(getActivity());
        b.setView(getActivity().getLayoutInflater().inflate(R.layout.fragment_test_dialog, null))
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String txt = ((EditText)getDialog().findViewById(R.id.dialogText)).getText().toString();
                        mListener.onUpdateText(txt);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // negative click, do nothing
                    }
                });
        return b.create();
    }

    @Override
    public void onCreate(Bundle saved) {
        super.onCreate(saved);
        setRetainInstance(true);
    }
}
