package za.ac.uct.cs.lwsjam005.eshelf.adapters;

import za.ac.uct.cs.lwsjam005.eshelf.R;
import za.ac.uct.cs.lwsjam005.eshelf.model.Book;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class LibraryListAdapter extends AbstractBookListAdapter {

	public LibraryListAdapter(Context c, Activity mainActivity) {
		super(c, mainActivity);
	}

	public int getCount() {
		return displayBooks.length + 1;
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
		// insert spacer
		if (position == 0) {
			view = li.inflate(R.layout.spacer_rectangle, null);
		} else {

			view = li.inflate(R.layout.book_display, null);

			Book thisBook = displayBooks[position - 1];

			TextView titleView = (TextView) view
					.findViewById(R.id.book_display_title);
			titleView.setText(thisBook.getTitle());

			TextView authorView = (TextView) view
					.findViewById(R.id.book_display_author);
			authorView.setText(thisBook.getAuthor());

			ImageView iv = (ImageView) view
					.findViewById(R.id.book_display_icon);
			iv.setImageResource(thisBook.getIcon());

			RatingBar bar = (RatingBar) view.findViewById(R.id.ratingBar);
			bar.setRating(thisBook.getRating());

			TextView costView = (TextView) view
					.findViewById(R.id.book_display_cost);
			if (!thisBook.isOwned())
				costView.setText("R " + thisBook.getPrice());
			else {
				costView.setText("R " + thisBook.getPrice());
				costView.setAlpha(0.3f);
			}
			
		

			if (thisBook.isOwned()) {
				ImageView pi = (ImageView) view
						.findViewById(R.id.purchase_icon);
				pi.setImageResource(R.drawable.icon_owned);
				
				View descriptionPanel = view
						.findViewById(R.id.description_panel);
				View cartPanel = view.findViewById(R.id.cart_panel);
				View ratingPanel = view.findViewById(R.id.rating_panel);
				descriptionPanel.setBackgroundColor(Color
						.parseColor("#5500cc00"));
				cartPanel.setBackgroundColor(Color.parseColor("#4400bb44"));
				ratingPanel.setBackgroundColor(Color.parseColor("#55005500"));
				
			} else{
				ImageView pi = (ImageView) view
						.findViewById(R.id.purchase_icon);
				if(thisBook.isInCart()){
					pi.setImageResource(R.drawable.icon_remove_from_cart);
					
					View descriptionPanel = view
							.findViewById(R.id.description_panel);
					View cartPanel = view.findViewById(R.id.cart_panel);
					View ratingPanel = view.findViewById(R.id.rating_panel);
					descriptionPanel.setBackgroundColor(Color
							.parseColor("#55cc0000"));
					cartPanel.setBackgroundColor(Color.parseColor("#44882200"));
					ratingPanel.setBackgroundColor(Color.parseColor("#55550000"));
					
					
				} else{
					pi.setImageResource(R.drawable.icon_add_to_cart);
				}
			}

		}

		return view;
	}

}
