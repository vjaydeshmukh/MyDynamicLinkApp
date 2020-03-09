package com.zo0okadev.mydynamiclinkapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.zo0okadev.mydynamiclinkapp.databinding.FragmentDeepLinkBinding;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class DeepLinkFragment extends Fragment {

    private static final String TAG = DeepLinkFragment.class.getSimpleName();
    private FragmentDeepLinkBinding binding;
    private FirebaseAnalytics analytics;

    public DeepLinkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_deep_link, container, false);

        analytics = FirebaseAnalytics.getInstance(getContext());

        if (getArguments() != null) {
            String name = getArguments().getString("item_name");
            int id = getArguments().getInt("item_id");
            Log.d(TAG, "onCreateView: Name: " + name);
            Log.d(TAG, "onCreateView: ID: " + id);
            binding.dataIdTextView.setText(String.format(Locale.ENGLISH, "Received ID: %d", id));
            binding.dataNameTextView.setText(String.format("Received Name: %s", name));

            Bundle bundle = new Bundle();
            bundle.putInt(FirebaseAnalytics.Param.ITEM_ID, id);
            bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
            analytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, bundle);
        }
        return binding.getRoot();
    }
}
