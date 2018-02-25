package com.example.elena.mytask;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsFragment extends Fragment {

    private RadioGroup mRadioGroup;
    public static final String SHARED_PREF_SETTINGS = "SHARED_PREF_SETTINGS";
    public static final String KEY_SEARCH = "KEY_SEARCH";

    private RadioGroup.OnCheckedChangeListener mRadioGroupListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            SharedPreferences sp = getActivity().getSharedPreferences(SHARED_PREF_SETTINGS, Context.MODE_PRIVATE);

            int search = 0;
            switch (checkedId) {
                case R.id.radioBtn_google:
                    search = 0;
                    break;
                case R.id.radioBtn_yandex:
                    search = 1;
                    break;
                case R.id.radioBtn_bing:
                    search = 2;
                    break;
            }

            sp.edit().putInt(KEY_SEARCH, search).commit();
        }
    };

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        mRadioGroup = v.findViewById(R.id.radio_group_settings);
        mRadioGroup.setOnCheckedChangeListener(mRadioGroupListener);

        SharedPreferences sp = getActivity().getSharedPreferences(SHARED_PREF_SETTINGS, Context.MODE_PRIVATE);
        if (sp == null) {
            RadioButton btn = mRadioGroup.findViewById(R.id.radioBtn_google);
            btn.setChecked(true);
        } else {
            int search = sp.getInt(KEY_SEARCH, 0);
            int id = R.id.radioBtn_google;
            switch (search) {
                case 0:
                    id = R.id.radioBtn_google;
                    break;
                case 1:
                    id = R.id.radioBtn_yandex;
                    break;
                case 2:
                    id = R.id.radioBtn_bing;
                    break;
            }
            RadioButton btn = mRadioGroup.findViewById(id);
            btn.setChecked(true);
        }

        return v;
    }

}
