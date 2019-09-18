package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {
	private int SETTINGS_ACTION = 1;

	public void onCreate(final Bundle savedInstanceState) {
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		String themeName = pref.getString("theme", "Theme1");
		if (themeName.equals("Theme1")) {
			setTheme(R.style.Theme1);
		} else if (themeName.equals("Theme2")) {
			Toast.makeText(this, "set theme", Toast.LENGTH_SHORT).show();
			setTheme(R.style.Theme2);
		}
		Toast.makeText(this, "Theme has been reset to " + themeName,
				Toast.LENGTH_SHORT).show();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.settings:
			startActivityForResult(new Intent(this,
					ThemePreferenceActivity.class), SETTINGS_ACTION);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == SETTINGS_ACTION) {
			if (resultCode == ThemePreferenceActivity.RESULT_CODE_THEME_UPDATED) {
				finish();
				startActivity(getIntent());
				return;
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
