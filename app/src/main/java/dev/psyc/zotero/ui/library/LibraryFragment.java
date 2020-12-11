package dev.psyc.zotero.ui.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import dev.psyc.zotero.MainActivity;
import dev.psyc.zotero.R;

public class LibraryFragment extends Fragment {

    private LibraryViewModel libraryViewModel;
    private MainActivity activity;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        activity = (MainActivity) getActivity();

        libraryViewModel =
                ViewModelProviders.of(this).get(LibraryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_library, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        libraryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        final TextView textView4 = root.findViewById(R.id.output_response);

        final ImageButton sync = root.findViewById(R.id.input_sync_library);
//        sync.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                textView4.setText(getItems());
//            }
//        });

        return root;
    }

//    public String getItems() {
//        JSONObject res = activity.api.GET("/users/6843527/items", new JSONObject(), );
//        return res.toString();
//    }
}