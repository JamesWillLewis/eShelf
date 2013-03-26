package za.ac.uct.cs.lwsjam005.eshelf.activities;

/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.List;

import za.ac.uct.cs.lwsjam005.eshelf.R;
import za.ac.uct.cs.lwsjam005.eshelf.dialogs.NewBookmarkDialog;
import za.ac.uct.cs.lwsjam005.eshelf.dialogs.ShowBookmarksDialog;
import za.ac.uct.cs.lwsjam005.eshelf.fragments.PageFragment;
import za.ac.uct.cs.lwsjam005.eshelf.model.Book;
import za.ac.uct.cs.lwsjam005.eshelf.model.Bookmark;
import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

/**
 * 
 * @see ScreenSlidePageFragment
 */
public class ReadActivity extends FragmentActivity implements
		OnMenuItemClickListener {
	/**
	 * The number of pages (wizard steps) to show in this demo.
	 */
	private static final int NUM_PAGES = 10;

	public static final String ARG_BOOK = "ARG_BOOK";

	public static final String ARG_CURRENT_PAGE = "ARG_CURRENT_PAGE";

	public static final String ARG_BOOKMARKS = "ARG_BOOKMARKS";

	/**
	 * The pager widget, which handles animation and allows swiping horizontally
	 * to access previous and next wizard steps.
	 */
	private ViewPager mPager;

	/**
	 * The pager adapter, which provides the pages to the view pager widget.
	 */
	private PagerAdapter mPagerAdapter;

	private int currentPage;

	private Book thisBook;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Bundle extras = getIntent().getExtras();
		thisBook = (Book) extras.getSerializable(ARG_BOOK);

		currentPage = thisBook.getCurrentPage();

		setContentView(R.layout.activity_read);

		setupActionBar();

		// Instantiate a ViewPager and a PagerAdapter.
		mPager = (ViewPager) findViewById(R.id.read_pager);

		mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
		mPager.setAdapter(mPagerAdapter);
		mPager.setCurrentItem(currentPage);
		
		

		mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			@Override
			public void onPageSelected(int position) {
				currentPage = position;

				Toast t = Toast.makeText(getApplicationContext(),
						(position + 1) + "/" + NUM_PAGES, Toast.LENGTH_SHORT);
				t.getView().setBackgroundColor(Color.parseColor("#33000088"));
				t.show();

				invalidateOptionsMenu();
			}

		});
	}

	private void setupActionBar() {
		ActionBar actionBar = getActionBar();

		actionBar.setDisplayHomeAsUpEnabled(true);

		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#aa000022")));
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#aa000022")));
		actionBar.setSplitBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#aa000022")));
	

		actionBar.setIcon(thisBook.getIcon());
		actionBar.setTitle(thisBook.getTitle());
		actionBar.setSubtitle(thisBook.getAuthor());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.read, menu);

		if (mPager.getCurrentItem() > 0) {
			menu.findItem(R.id.action_previous).setEnabled(true);
			menu.findItem(R.id.action_previous).setIcon(
					R.drawable.navigation_previous_item);
		} else {
			menu.findItem(R.id.action_previous).setEnabled(false);
			menu.findItem(R.id.action_previous).setIcon(
					R.drawable.navigation_previous_item_faded);
		}

		if (mPager.getCurrentItem() < mPagerAdapter.getCount() - 1) {
			menu.findItem(R.id.action_next).setEnabled(true);
			menu.findItem(R.id.action_next).setIcon(
					R.drawable.navigation_next_item);

		} else {
			menu.findItem(R.id.action_next).setEnabled(false);
			menu.findItem(R.id.action_next).setIcon(
					R.drawable.navigation_next_item_faded);
		}
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			// go back home

			// ensure we are resuming the existing activity
			Intent intent = new Intent(this, MainActivity.class)
					.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra(ARG_CURRENT_PAGE, currentPage);
			intent.putExtra(ARG_BOOKMARKS, thisBook.getBookmarks());
			intent.setAction(MainActivity.ACTION_RETURN_FROM_READ);

			startActivity(intent);

			return true;

		case R.id.action_previous:
			// Go to the previous step in the wizard. If there is no previous
			// step,
			// setCurrentItem will do nothing.
			mPager.setCurrentItem(mPager.getCurrentItem() - 1);
			return true;

		case R.id.action_next:
			// Advance to the next step in the wizard. If there is no next step,
			// setCurrentItem
			// will do nothing.
			mPager.setCurrentItem(mPager.getCurrentItem() + 1);
			return true;
		case R.id.action_bookmarks:
			PopupMenu popup = new PopupMenu(this,
					this.findViewById(R.id.action_bookmarks));
			popup.setOnMenuItemClickListener(this);
			MenuInflater inflater = popup.getMenuInflater();
			inflater.inflate(R.menu.popup_menu_bookmarks, popup.getMenu());

			popup.show();

			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	/**
	 * A simple pager adapter that represents 5 {@link ScreenSlidePageFragment}
	 * objects, in sequence.
	 */
	private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
		public ScreenSlidePagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			return PageFragment.create(position);
		}

		@Override
		public int getCount() {
			return NUM_PAGES;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return "Page " + (position + 1);
		}
	}

	@Override
	public boolean onMenuItemClick(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_new_bookmark:
			NewBookmarkDialog bookmarkDialog = new NewBookmarkDialog();
			bookmarkDialog.show(getFragmentManager(), "new_bookmark");
			break;
		case R.id.action_list_bookmarks:
			ShowBookmarksDialog bookmarksDialog = new ShowBookmarksDialog();
			bookmarksDialog.show(getFragmentManager(), "show_bookmarks");
			break;
		}

		return true;
	}

	public void placeNewBookmark(String description) {
		System.out.println("NEW BOOKMARK: PAGE=" + currentPage + " BRIEF="
				+ description);
		thisBook.getBookmarks().add(new Bookmark(currentPage, description));
	}

	public List<Bookmark> getBookmarks() {
		return thisBook.getBookmarks();
	}

	public void setCurrentPage(int page) {
		mPager.setCurrentItem(page);
	}
}
