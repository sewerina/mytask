package com.example.elena.mytask;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class SearchFragment extends Fragment {

    private EditText mEditText;
    private Button mSearchBtn;
    private View.OnClickListener mSearchBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Integer selectedId = 0;
            SharedPreferences sp = getActivity().getSharedPreferences(SettingsFragment.SHARED_PREF_SETTINGS, Context.MODE_PRIVATE);
            if (sp != null) {
                selectedId = sp.getInt(SettingsFragment.KEY_SEARCH, 0);
            }

            String search = "";
            switch (selectedId) {
                case 0:
                    search = "https://www.google.com/search?q=";
                    break;
                case 1:
                    search = "https://yandex.ru/search/?text=";
                    break;
                case 2:
                    search = "https://www.bing.com/search?q=";
                    break;
                default:
                    search = "https://www.google.com/search?q=";
                    break;
            }

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(search + mEditText.getText().toString()));
            startActivity(intent);
        }
    };

    public static SearchFragment newInstance() {
        return new SearchFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);

        mEditText = v.findViewById(R.id.edit_text);
        mSearchBtn = v.findViewById(R.id.btn_search);
        mSearchBtn.setOnClickListener(mSearchBtnListener);
        return v;
    }

}
