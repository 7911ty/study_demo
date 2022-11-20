package com.example.studydemo.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.studydemo.R;

public class BlankFragment extends Fragment {
    private static final String TAG = "BlankFragment";
    private static final String ARG_TEXT = "param1";
    private String mParam1;
    private View inflate;
    private IFragmentCollBack collBack;

    public BlankFragment() {
        // Required empty public constructor
    }

    public IFragmentCollBack getCollBack() {
        return collBack;
    }

    public void setCollBack(IFragmentCollBack collBack) {
        this.collBack = collBack;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    public static BlankFragment newInstance(String param1) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TEXT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Bundle bundle = getArguments();
//        String message = bundle.getString("message");
//        Student student = bundle.getParcelable("student");
//        Log.d(TAG,"message = " + message);
//        Log.d(TAG,"message = " + student.name);
//        Log.d(TAG,"message = " + student.id);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_TEXT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (inflate == null) {
            inflate = inflater.inflate(R.layout.fragment_blank, container, false);
        }
        TextView textView = inflate.findViewById(R.id.fragment_text);
        Button btBlank = inflate.findViewById(R.id.bt_blank);
        textView.setText(mParam1);
        if (collBack != null) {
            btBlank.setVisibility(View.VISIBLE);
            btBlank.setOnClickListener(view -> collBack.sendMsgToActivity("msg111"));
        } else {
            btBlank.setVisibility(View.GONE);
        }
        return inflate;
    }
}