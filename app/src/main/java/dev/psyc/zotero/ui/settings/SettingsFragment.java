package dev.psyc.zotero.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import dev.psyc.zotero.MainActivity;
import dev.psyc.zotero.R;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    public MainActivity activity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        activity = (MainActivity) getActivity();

        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        final TextView textView = root.findViewById(R.id.textView2);
        settingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final TextView input_api_key = root.findViewById(R.id.input_api_key);
        String key = activity.preferences.getString("APIKey", "Test");
        input_api_key.setText(key);

        final ImageButton sync = root.findViewById(R.id.input_sync_api);
        sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.changeZoteroAPIKey(input_api_key.getText().toString());
            }
        });


        // Dark Mode Spinner
        final Spinner spinnerDarkMode = root.findViewById(R.id.spinner_dark_mode);
        spinnerDarkMode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                activity.changeDarkMode(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do Nothing...
            }
        });

        return root;
    }
}