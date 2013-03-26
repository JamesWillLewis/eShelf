package za.ac.uct.cs.lwsjam005.eshelf.dialogs;

import za.ac.uct.cs.lwsjam005.eshelf.activities.ReadActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.widget.EditText;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass. Use the
 * {@link NewBookmarkDialog#newInstance} factory method to create an instance of
 * this fragment.
 * 
 */
public class NewBookmarkDialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		final EditText editText = new EditText(getActivity());
	
		builder.setTitle("Enter a short description").setView(
				editText).setPositiveButton("Place Bookmark", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {					
			
						((ReadActivity)getActivity()).placeNewBookmark(editText.getText().toString());
					}
				}).setNegativeButton("Cancel", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//do nothing
					}
				});

		AlertDialog d = builder.create();
		return d;
	}

}
