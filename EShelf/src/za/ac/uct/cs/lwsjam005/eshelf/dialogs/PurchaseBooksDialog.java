package za.ac.uct.cs.lwsjam005.eshelf.dialogs;

import za.ac.uct.cs.lwsjam005.eshelf.R;
import za.ac.uct.cs.lwsjam005.eshelf.R.drawable;
import za.ac.uct.cs.lwsjam005.eshelf.activities.LoginActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class PurchaseBooksDialog extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Proceed to checkout")
				.setIcon(R.drawable.icon_cart_black)
				.setMessage("Are you sure you want to purchase those books?")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								getActivity().startActivity(new Intent(getActivity(), LoginActivity.class));
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						//nothing
					}
				});
		// Create the AlertDialog object and return it

		AlertDialog d = builder.create();
		return d;
	}
}