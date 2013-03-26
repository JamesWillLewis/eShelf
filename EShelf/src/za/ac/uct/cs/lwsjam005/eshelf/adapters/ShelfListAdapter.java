package za.ac.uct.cs.lwsjam005.eshelf.adapters;

import java.util.Arrays;

import za.ac.uct.cs.lwsjam005.eshelf.R;
import za.ac.uct.cs.lwsjam005.eshelf.R.drawable;
import za.ac.uct.cs.lwsjam005.eshelf.R.id;
import za.ac.uct.cs.lwsjam005.eshelf.R.layout;
import za.ac.uct.cs.lwsjam005.eshelf.activities.MainActivity;
import za.ac.uct.cs.lwsjam005.eshelf.model.Book;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class ShelfListAdapter extends AbstractBookListAdapter {

	public ShelfListAdapter(Context c, Activity mainActivity) {
		super(c, mainActivity);
	}

	public int getCount() {
		return displayBooks.length + 2;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	// create a new ImageView for each item referenced by the Adapter
	public View getView(int position, View convertView, ViewGroup parent) {

		View view;
		LayoutInflater li = LayoutInflater.from(mContext);
		if (position < 2) {

			view = li.inflate(R.layout.spacer_rectangle, null);
		} else { // if it's not recycled, initialize some
					// attributes
			position -= 2;
			Book thisBook = displayBooks[position];

			view = li.inflate(R.layout.book_thumbnail, null);

			View thumbBottom = view.findViewById(R.id.thumbnail_bottom);
			if ((position + 1) % 2 == 0) {
				thumbBottom.setBackgroundColor(Color.parseColor("#55008800"));
			}
			if (thisBook.getTitle().equals(
					mainActivity.getCurrentBook().getTitle())) {
				thumbBottom.setBackgroundColor(Color.parseColor("#55cc0000"));
			}

			TextView titleView = (TextView) view
					.findViewById(R.id.book_display_title);
			titleView.setText(thisBook.getTitle());
			TextView authorView = (TextView) view
					.findViewById(R.id.book_display_author);
			authorView.setText(thisBook.getAuthor());
			ImageView iv = (ImageView) view
					.findViewById(R.id.book_display_icon);
			iv.setImageResource(thisBook.getIcon());
		}

		return view;
	}

}