package za.ac.uct.cs.lwsjam005.eshelf.fragments;

import za.ac.uct.cs.lwsjam005.eshelf.R;
import za.ac.uct.cs.lwsjam005.eshelf.R.id;
import za.ac.uct.cs.lwsjam005.eshelf.R.layout;
import za.ac.uct.cs.lwsjam005.eshelf.R.menu;
import za.ac.uct.cs.lwsjam005.eshelf.activities.MainActivity;
import za.ac.uct.cs.lwsjam005.eshelf.adapters.LibraryListAdapter;
import za.ac.uct.cs.lwsjam005.eshelf.dialogs.PurchaseBooksDialog;
import za.ac.uct.cs.lwsjam005.eshelf.model.Book;
import za.ac.uct.cs.lwsjam005.eshelf.model.BookComparators;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the
 * {@link LibraryFragment#newInstance} factory method to create an instance of
 * this fragment.
 * 
 */
public class LibraryFragment extends Fragment implements
		OnMenuItemClickListener {

	private LibraryListAdapter libraryListAdapter;

	private ListView listView;

	public static LibraryFragment newInstance(String param1, String param2) {
		LibraryFragment fragment = new LibraryFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	public LibraryFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View V = inflater.inflate(R.layout.fragment_library, container, false);

		listView = (ListView) V;

		libraryListAdapter = new LibraryListAdapter(V.getContext(),
				getActivity());
		listView.setAdapter(libraryListAdapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// ignore spacer
				if (position > 0) {
					Book b = ((MainActivity) getActivity()).getBookCollection()[position - 1];
					if (!b.isOwned()) {
						if(b.isInCart()){
							b.setInCart(false);
						} else{
							b.setInCart(true);
						}
						libraryListAdapter.notifyDataSetChanged();
					}
				}
			}
		});

		// Inflate the layout for this fragment

		return V;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.library, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// if sort button pressed
		if (item.getItemId() == R.id.action_sort_library) {
			PopupMenu popup = new PopupMenu(getActivity(), getActivity()
					.findViewById(R.id.action_sort_library));
			popup.setOnMenuItemClickListener(this);
			MenuInflater inflater = popup.getMenuInflater();
			inflater.inflate(R.menu.popup_menu_library_sort, popup.getMenu());
			popup.show();
		} else if (item.getItemId() == R.id.action_checkout){
			PurchaseBooksDialog dialog = new PurchaseBooksDialog();
			dialog.show(getFragmentManager(), "confirm");
		}

		return true;
	}

	public void doSearch(final String query) {

		final int animDuration = getResources().getInteger(
				android.R.integer.config_shortAnimTime);

		listView.animate().alpha(0f).setDuration(animDuration)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						libraryListAdapter.doSearch(query);
						libraryListAdapter.notifyDataSetChanged();
						listView.animate().alpha(1f).setDuration(animDuration);
					}
				});
	}

	@Override
	public boolean onMenuItemClick(final MenuItem item) {

		final int animDuration = getResources().getInteger(
				android.R.integer.config_shortAnimTime);

		listView.animate().alpha(0f).setDuration(animDuration)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						switch (item.getItemId()) {
						case R.id.action_sort_title:
							libraryListAdapter
									.doSort(new BookComparators.CompareTitle());
							break;
						case R.id.action_sort_author:
							libraryListAdapter
									.doSort(new BookComparators.CompareAuthor());
							break;
						case R.id.action_sort_publisher:
							libraryListAdapter
									.doSort(new BookComparators.ComparePublisher());
							break;
						case R.id.action_sort_genre:
							libraryListAdapter
									.doSort(new BookComparators.CompareClassification());
							break;
						case R.id.action_sort_date:
							libraryListAdapter
									.doSort(new BookComparators.CompareDate());
							break;
						case R.id.action_sort_price:
							libraryListAdapter
									.doSort(new BookComparators.ComparePrice());
							break;
						case R.id.action_sort_owned:
							libraryListAdapter
									.doSort(new BookComparators.CompareOwned());
							break;
						case R.id.action_sort_rating:
							libraryListAdapter
									.doSort(new BookComparators.CompareRating());
							break;
						}
						libraryListAdapter.notifyDataSetChanged();
						listView.animate().alpha(1f).setDuration(animDuration);
					}
				});

		return true;
	}

}
