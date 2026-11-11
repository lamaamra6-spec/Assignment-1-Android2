package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.databinding.Fragment3Binding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment3 extends Fragment {

    public static Fragment3Binding fragment3Binding;

    private static final String ARG_NAME = "name";

    private String name;

    private OnCheckboxChangeListener listener;

    /**
     * Fragment3 uses this interface to tell the Host Activity
     * every time the checkbox is checked or unchecked.
     */
    interface OnCheckboxChangeListener {
        void onCheckboxChanged(boolean isChecked);
    }

    public Fragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided name.
     *
     * @param name The user's name (received from Fragment2 through the Host Activity).
     * @return A new instance of fragment Fragment3.
     */
    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * This method checks if the hosting activity has implemented
     * the OnCheckboxChangeListener interface. If it does not,
     * an exception is thrown.
     *
     * @param context
     */
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnCheckboxChangeListener) {
            listener = (OnCheckboxChangeListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + getResources().getString(R.string.exception_message));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ARG_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragment3Binding = Fragment3Binding.inflate(inflater, container, false);
        return fragment3Binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragment3Binding.textViewName.setText(getString(R.string.hello_name, name));
        fragment3Binding.buttonFinish.setEnabled(false);
        fragment3Binding.buttonFinish.setText(R.string.continue_text);
        fragment3Binding.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> listener.onCheckboxChanged(isChecked));
    }
}
