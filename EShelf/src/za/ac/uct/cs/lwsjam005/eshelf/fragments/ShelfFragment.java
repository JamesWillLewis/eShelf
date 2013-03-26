package za.ac.uct.cs.lwsjam005.eshelf.fragments;

import za.ac.uct.cs.lwsjam005.eshelf.R;
import za.ac.uct.cs.lwsjam005.eshelf.activities.MainActivity;
import za.ac.uct.cs.lwsjam005.eshelf.activities.ReadActivity;
import za.ac.uct.cs.lwsjam005.eshelf.adapters.ShelfListAdapter;
import za.ac.uct.cs.lwsjam005.eshelf.model.BookComparators;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.Toast;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Activities that
 * contain this fragment must implement the
 * {@link ShelfFragment.OnFragmentInteractionListener} interface to handle
 * interaction events. Use the {@link ShelfFragment#newInstance} factory method
 * to create an instance of this fragment.
 * 
 */
public class ShelfFragment extends Fragment implements OnMenuItemClickListener {

	private ShelfListAdapter shelfListAdapter;
	private GridView gridview;

	public static ShelfFragment newInstance(String param1, String param2) {
		ShelfFragment fragment = new ShelfFragment();

		return fragment;
	}

	public ShelfFragment() {
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

		View V = inflater.inflate(R.layout.fragment_shelf, container, false);

		gridview = (GridView) V;

		shelfListAdapter = new ShelfListAdapter(V.getContext(), getActivity());
		gridview.setAdapter(shelfListAdapter);

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				// first 2 thumbnail views are spacers, so ignore
				if (position >= 2) {
					position -= 2;

					MainActivity mainActivity = (MainActivity) getActivity();

					Toast t = Toast.makeText(
							getActivity(),
							"You are reading "
									+ mainActivity.getBookCollection()[position]
											.getTitle(), Toast.LENGTH_LONG);
					t.getView().setBackgroundColor(
							Color.parseColor("#33000088"));
					t.show();

					Bundle args = new Bundle();

					mainActivity.setCurrentBook(mainActivity
							.getBookCollection()[position]);
					shelfListAdapter.notifyDataSetChanged();
					
					args.putSerializable(ReadActivity.ARG_BOOK,
							mainActivity.getCurrentBook());

					Intent i = new Intent(getActivity(), ReadActivity.class);
					i.putExtras(args);
					getActivity().startActivity(i);
				}
			}
		});

		// Inflate the layout for this fragment

		return V;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.shelf, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// if sort button pressed
		if (item.getItemId() == R.id.action_sort_shelf) {
			PopupMenu popup = new PopupMenu(getActivity(), getActivity()
					.findViewById(R.id.action_sort_shelf));
			popup.setOnMenuItemClickListener(this);
			MenuInflater inflater = popup.getMenuInflater();
			inflater.inflate(R.menu.popup_menu_shelf_sort, popup.getMenu());
			popup.show();
		}

		return true;
	}

	public void doSearch(final String query) {
		final int animDuration = getResources().getInteger(
				android.R.integer.config_shortAnimTime);

		gridview.animate().alpha(0f).setDuration(animDuration)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						shelfListAdapter.doSearch(query);
						shelfListAdapter.notifyDataSetChanged();
						gridview.animate().alpha(1f).setDuration(animDuration);
					}
				});
	}

	@Override
	public boolean onMenuItemClick(final MenuItem item) {

		final int animDuration = getResources().getInteger(
				android.R.integer.config_shortAnimTime);

		gridview.animate().alpha(0f).setDuration(animDuration)
				.setListener(new AnimatorListenerAdapter() {
					@Override
					public void onAnimationEnd(Animator animation) {
						switch (item.getItemId()) {
						case R.id.action_sort_title:
							shelfListAdapter
									.doSort(new BookComparators.CompareTitle());
							break;
						case R.id.action_sort_author:
							shelfListAdapter
									.doSort(new BookComparators.CompareAuthor());
							break;
						case R.id.action_sort_genre:
							shelfListAdapter
									.doSort(new BookComparators.CompareClassification());
							break;
						case R.id.action_sort_latest:
							shelfListAdapter
									.doSort(new BookComparators.CompareDate());
							break;
						case R.id.action_sort_publisher:
							shelfListAdapter
									.doSort(new BookComparators.ComparePublisher());
							break;
						case R.id.action_sort_read:
							shelfListAdapter
									.doSort(new BookComparators.CompareRead());
							break;
						case R.id.action_sort_rating:
							shelfListAdapter
									.doSort(new BookComparators.CompareRating());
							break;
						}
						shelfListAdapter.notifyDataSetChanged();
						gridview.animate().alpha(1f).setDuration(animDuration);
					}
				});

		return true;
	}

}
