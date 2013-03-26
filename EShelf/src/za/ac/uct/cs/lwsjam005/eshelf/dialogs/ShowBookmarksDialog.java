package za.ac.uct.cs.lwsjam005.eshelf.dialogs;

import java.util.List;

import za.ac.uct.cs.lwsjam005.eshelf.activities.ReadActivity;
import za.ac.uct.cs.lwsjam005.eshelf.model.Bookmark;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

public class ShowBookmarksDialog extends DialogFragment {
	
	private List<Bookmark> bookmarks;
	private Bookmark bookmark;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		bookmarks = ((ReadActivity) getActivity())
				.getBookmarks();

		final CharSequence[] list = new CharSequence[bookmarks.size()];
		
		for (int i = 0; i < list.length; i++) {
			list[i] = bookmarks.get(i).toString();
			System.out.println(list[i]);
		}



		builder.setTitle("Your bookmarks").setNeutralButton("Close",
				new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// do nothing
					}
				});

		builder.setItems(list, new DialogClickListener());

		AlertDialog alertDialog = builder.create();

		return alertDialog;
	}

	public class DialogClickListener implements OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			new BookmarkOptionsDialog().show(getFragmentManager(),
					"bookmark_options");
			bookmark = bookmarks.get(which);
		}
	}
	
	public int getPageNumber() {
		return bookmark.getPageNumber();
	}

}
