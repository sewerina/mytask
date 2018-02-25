package com.example.elena.mytask;

import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends SingleFragmentActivity {

    @Override
    protected Fragment getFragment() {
        return new MainFragment();
    }

    // Код для меню
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Код для выбора элемента меню
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                showMessage(R.string.settings);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, SettingsFragment.newInstance())
                        .addToBackStack(SettingsFragment.class.getName())
                        .commit();
                return true;

            case R.id.action_search:
                showMessage(R.string.search);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, SearchFragment.newInstance())
                        .addToBackStack(SearchFragment.class.getName())
                        .commit();

                return true;

            case R.id.action_exit:
                showMessage(R.string.exit);
                this.finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Код для показа уведомления
    private void showMessage(@StringRes int resId) {
        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }

}
