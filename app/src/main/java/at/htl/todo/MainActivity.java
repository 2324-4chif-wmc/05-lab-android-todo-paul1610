package at.htl.todo;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.ComponentActivity;

import javax.inject.Inject;

import at.htl.todo.ui.layout.MainView;
import at.htl.todo.util.Config;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends ComponentActivity {
    public static final String TAG = MainActivity.class.getName();

    @Inject
    MainView mainView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Config.load(this);
        super.onCreate(savedInstanceState);
        String baseUrl = Config.getProperty("json.placeholder.baseurl");

        if (baseUrl == null) {
            Log.e(TAG, "Base URL is not defined.");
        } else {
            Log.i(TAG, "onCreate - Base URL: " + baseUrl);
        };

        mainView.buildContent(this);
    }
}