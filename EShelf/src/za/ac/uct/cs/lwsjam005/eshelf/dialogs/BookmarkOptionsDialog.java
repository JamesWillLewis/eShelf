package za.ac.uct.cs.lwsjam005.eshelf.dialogs;

import za.ac.uct.cs.lwsjam005.eshelf.activities.ReadActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

public class BookmarkOptionsDialog extends DialogFragment {

	public BookmarkOptionsDialog() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		//retrieve parent dialog
		final ShowBookmarksDialog bookmarksDialog = (ShowBookmarksDialog) getFragmentManager()
				.findFragmentByTag("show_bookmarks");

		AlertDialog.Builder b = new AlertDialog.Builder(getActivity());

		b.setNegativeButton("Delete", null).setCancelable(true)
				.setNeutralButton("Edit", null)
				.setPositiveButton("Go to", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						((ReadActivity)getActivity()).setCurrentPage(bookmarksDialog.getPageNumber());
					}
				});

		return (AlertDialog) b.create();
	}

}
