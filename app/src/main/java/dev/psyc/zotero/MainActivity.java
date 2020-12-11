package dev.psyc.zotero;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    // Global Variables.
    public SharedPreferences preferences;
    public SharedPreferences.Editor preferenceEditor;
    public ZoteroAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_library, R.id.navigation_read, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        preferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        preferenceEditor = preferences.edit();


        String api_key = preferences.getString("APIKey", "");
        String base_url = getResources().getString(R.string.api_url);
        api = new ZoteroAPI(this, base_url, api_key);

        AppCompatDelegate.setDefaultNightMode(preferences.getInt("DarkMode", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM));
    }

    public void changeDarkMode(int pos) {
        int mode;
        switch (pos) {
            case 0:
                mode = AppCompatDelegate.MODE_NIGHT_NO;
                break;
            case 1:
                mode = AppCompatDelegate.MODE_NIGHT_YES;
                break;
            default:
                mode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM;
        }
        preferenceEditor.putInt("DarkMode", pos);
        AppCompatDelegate.setDefaultNightMode(mode);
    }

    public void changeZoteroAPIKey(String key) {
        preferenceEditor.putString("APIKey", key);
        preferenceEditor.commit();
        api.updateAPIKey(key);
    }

}