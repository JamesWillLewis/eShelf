package za.ac.uct.cs.lwsjam005.eshelf.listeners;

import za.ac.uct.cs.lwsjam005.eshelf.activities.MainActivity;
import za.ac.uct.cs.lwsjam005.eshelf.activities.ReadActivity;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

public class LaunchReadActivityTabListener implements ActionBar.TabListener {

	private Activity activity;

	public LaunchReadActivityTabListener(Activity activity, ActionBar actionBar) {
		this.activity = activity;
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		Bundle b = new Bundle();
		b.putSerializable(ReadActivity.ARG_BOOK,
				((MainActivity) activity).getCurrentBook());
		activity.startActivity(new Intent(activity, ReadActivity.class).putExtras(b));
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

}
