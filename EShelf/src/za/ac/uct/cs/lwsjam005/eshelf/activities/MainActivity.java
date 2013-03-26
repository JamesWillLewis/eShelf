package za.ac.uct.cs.lwsjam005.eshelf.activities;

import java.lang.reflect.Field;
import java.util.ArrayList;

import za.ac.uct.cs.lwsjam005.eshelf.R;
import za.ac.uct.cs.lwsjam005.eshelf.fragments.LibraryFragment;
import za.ac.uct.cs.lwsjam005.eshelf.fragments.ShelfFragment;
import za.ac.uct.cs.lwsjam005.eshelf.listeners.LaunchReadActivityTabListener;
import za.ac.uct.cs.lwsjam005.eshelf.listeners.TabListener;
import za.ac.uct.cs.lwsjam005.eshelf.model.Book;
import za.ac.uct.cs.lwsjam005.eshelf.model.Bookmark;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.SearchManager;
import android.app.SearchManager.OnCancelListener;
import android.app.SearchManager.OnDismissListener;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;
import android.widget.SearchView;

public class MainActivity extends Activity {

	private Book[] bookCollection;
	private Book currentBook;
	public static final int BOOK_COLLECTION_SIZE = 7;

	public static boolean NO_MENU_BUTTON = true;

	public static final String ACTION_RETURN_FROM_READ = "ACTION_RETURN_FROM_READ";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initViewConfig();
		initBookCollection();
		initActionBar();
		handleIntent(getIntent());
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		handleIntent(intent);
	}

	int searchCount = 0;

	private void handleIntent(Intent intent) {
		// just restarting the activity to previous state
		if (MainActivity.ACTION_RETURN_FROM_READ.equals(intent.getAction())) {

			getActionBar().setSelectedNavigationItem(0);

			Bundle b = intent.getExtras();

			currentBook.setCurrentPage(b.getInt(ReadActivity.ARG_CURRENT_PAGE));
			Object serializable = b.getSerializable(ReadActivity.ARG_BOOKMARKS);
			if (serializable != null) {
				currentBook.setBookmarks((ArrayList<Bookmark>) serializable);
			}

		}
		// performing search
		if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
			searchCount++;
			String query = intent.getStringExtra(SearchManager.QUERY);

			performSearch(query);
		}
	}

	private void performSearch(String query) {

		ShelfFragment shelfFragment = (ShelfFragment) getFragmentManager()
				.findFragmentByTag("shelf");

		LibraryFragment libraryFragment = (LibraryFragment) getFragmentManager()
				.findFragmentByTag("library");

		// always check that the fragment has actually been loaded already
		if (shelfFragment != null) {
			shelfFragment.doSearch(query);
		}
		if (libraryFragment != null) {
			libraryFragment.doSearch(query);
		}

		if (!query.equals(""))
			getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	private void initViewConfig() {
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
		View mainView = new View(this);
		mainView.setBackgroundResource(R.drawable.background_dark);
		setContentView(mainView);
		if (NO_MENU_BUTTON) {
			try {
				ViewConfiguration config = ViewConfiguration.get(this);
				Field menuKeyField = ViewConfiguration.class
						.getDeclaredField("sHasPermanentMenuKey");
				if (menuKeyField != null) {
					menuKeyField.setAccessible(true);
					menuKeyField.setBoolean(config, false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void initSearch(Menu menu) {
		// Associate searchable configuration with the SearchView
		SearchManager searchManager = (SearchManager) this
				.getSystemService(Context.SEARCH_SERVICE);

		searchManager.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel() {
				System.out.println("SEARCH CANCEL");
			}
		});

		searchManager.setOnDismissListener(new OnDismissListener() {

			@Override
			public void onDismiss() {
				System.out.println("SEARCH DISMISS ");
			}
		});

		SearchView searchView = (SearchView) menu.findItem(
				R.id.menu_item_search).getActionView();

		searchView.setSearchableInfo(searchManager
				.getSearchableInfo(getComponentName()));
		searchView.setIconifiedByDefault(false);

	}

	private void initActionBar() {

		// setup action bar for tabs
		ActionBar actionBar = getActionBar();

		actionBar.setBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#aa000022")));
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#aa000022")));
		actionBar.setSplitBackgroundDrawable(new ColorDrawable(Color
				.parseColor("#aa000022")));

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		initTabs(actionBar);
	}

	private void initTabs(ActionBar actionBar) {

		Tab tab = actionBar
				.newTab()
				.setText(R.string.shelf_tab)
				.setIcon(R.drawable.icon_shelf)
				.setTabListener(
						new TabListener<ShelfFragment>(this, "shelf",
								ShelfFragment.class));
		actionBar.addTab(tab);

		
		tab = actionBar
				.newTab()
				.setText(R.string.read_tab)
				.setIcon(R.drawable.icon_read)
				.setTabListener(
						new LaunchReadActivityTabListener(this, actionBar));
		actionBar.addTab(tab);

		tab = actionBar
				.newTab()
				.setText(R.string.library_tab)
				.setIcon(R.drawable.icon_store)
				.setTabListener(
						new TabListener<LibraryFragment>(this, "library",
								LibraryFragment.class));
		actionBar.addTab(tab);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		initSearch(menu);
		return true;
	}

	private void initBookCollection() {
		bookCollection = new Book[BOOK_COLLECTION_SIZE];

		bookCollection[0] = new Book("Book 1", "Author 1", "class1#div1#sect1",
				"publisher1", 250.00, new String[] { "tag1", "tag2", "tag3" },
				R.drawable.cover1, false, 5);
		bookCollection[1] = new Book("Book 2", "Author 2", "class1#div1#sect1",
				"publisher1", 350.00, new String[] { "tag1", "tag2", "tag3" },
				R.drawable.cover2, false, 3);
		bookCollection[2] = new Book("Book 3", "Author 3", "class1#div1#sect2",
				"publisher1", 250.00, new String[] { "tag1", "tag2", "tag3" },
				R.drawable.cover3, true, 2);
		bookCollection[3] = new Book("Book 4", "Author 1", "class1#div2#sect1",
				"publisher2", 150.00, new String[] { "tag1", "tag2", "tag3" },
				R.drawable.cover4, false, 3);
		bookCollection[4] = new Book("Book 5", "Author 2", "class2#div1#sect1",
				"publisher2", 120.00, new String[] { "tag1", "tag2", "tag3" },
				R.drawable.cover5, false, 4);
		bookCollection[5] = new Book("Book 6", "Author 3", "class2#div2#sect1",
				"publisher2", 250.00, new String[] { "tag1", "tag2", "tag3" },
				R.drawable.cover6, true, 1);
		bookCollection[6] = new Book("Book 7", "Author 1", "class1#div1#sect1",
				"publisher1", 50.00, new String[] { "tag1", "tag2", "tag3" },
				R.drawable.cover7, false, 2);
		currentBook = bookCollection[0];
	}

	public Book[] getBookCollection() {
		return bookCollection;
	}

	public Book getCurrentBook() {
		return currentBook;
	}

	public void setCurrentBook(Book currentBook) {
		this.currentBook = currentBook;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			getActionBar().setDisplayHomeAsUpEnabled(false);
			performSearch("");
		}
		return super.onOptionsItemSelected(item);
	}

}
