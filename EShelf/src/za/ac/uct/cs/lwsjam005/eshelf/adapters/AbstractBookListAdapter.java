package za.ac.uct.cs.lwsjam005.eshelf.adapters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

import za.ac.uct.cs.lwsjam005.eshelf.activities.MainActivity;
import za.ac.uct.cs.lwsjam005.eshelf.model.Book;
import android.app.Activity;
import android.content.Context;
import android.widget.BaseAdapter;

/**
 * Super class of any adapter which lists books
 * 
 * @author James Lewis
 * 
 */
public abstract class AbstractBookListAdapter extends BaseAdapter {

	protected Context mContext;

	protected Book[] bookCollection;

	protected Book[] displayBooks;
	
	protected MainActivity mainActivity;


	public AbstractBookListAdapter(Context c, Activity mainActivity) {
		mContext = c;
		bookCollection = ((MainActivity) mainActivity).getBookCollection();
		displayBooks = Arrays.copyOf(bookCollection, bookCollection.length);
		this.mainActivity = (MainActivity) mainActivity;
	}

	public void doSearch(String query) {
		if (query.equals("")) {
			System.out.println("RESET FILTER");
			displayBooks = Arrays.copyOf(bookCollection, bookCollection.length);
		} else {
			System.out.println("SETTING FILTER: " + query);
			ArrayList<Book> results = new ArrayList<Book>();
			// perform linear search of list
			query = query.toLowerCase();
			for (Book book : bookCollection) {
				// if match
				if (book.getTitle().toLowerCase().contains(query)
						|| book.getAuthor().toLowerCase().contains(query)) {
					results.add(book);
				} else {

					String[] classif = book.getClassification().split("#");
					if (classif[0].toLowerCase().contains(query)) {
						results.add(book);
					} else if (classif[1].toLowerCase().contains(query)) {
						results.add(book);
					} else if (classif[2].toLowerCase().contains(query)) {
						results.add(book);
					} else {

						for (String tag : book.getTags()) {
							if (tag.toLowerCase().contains(query)) {
								results.add(book);
							}
						}
					}
				}

			}

			displayBooks = results.toArray(new Book[results.size()]);

		}
	}
	
	public void doSort(Comparator<Book> bookCompare){
		Arrays.sort(displayBooks, bookCompare);
	}

}
