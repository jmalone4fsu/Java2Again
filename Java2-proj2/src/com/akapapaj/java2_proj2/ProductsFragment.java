package com.akapapaj.java2_proj2;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ProductsFragment extends ListFragment {
	boolean isDualPane;
	int checkPosition = 0;
	int requestCode;

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// Check that build version Honeycomb or higher
		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
			// list using static array of products
			setListAdapter(new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_1, getResources()
							.getStringArray(R.array.products_array)));

		} else {
			setListAdapter(new ArrayAdapter<String>(getActivity(),
					android.R.layout.simple_list_item_activated_1,
					getResources().getStringArray(R.array.products_array)));
		}
		// Check to see if we have a frame in which to embed the details
		// fragment directly in the containing UI.
		View detailsFrame = getActivity().findViewById(R.id.details);
		isDualPane = detailsFrame != null
				&& detailsFrame.getVisibility() == View.VISIBLE;

		if (savedInstanceState != null) {
			// restore state for last checked position
			checkPosition = savedInstanceState.getInt("curChoice", 0);
		}
		if (isDualPane) {
			// in dual pane, the list view will highlight a selected item
			getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
			showDetails(checkPosition);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("curChoice", checkPosition);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		showDetails(position);
	}

	/**
	 * Helper method to show the details of a selected item. Either it will
	 * display a fragment in the current UI (if landscape) or start a new
	 * activity to display it.
	 */
	void showDetails(int index) {
		checkPosition = index;

		if (isDualPane) {
			getListView().setItemChecked(index, true);

			// check what fragment is currently being shown, replace if needed
			DetailsFragment details = (DetailsFragment) getActivity()
					.getFragmentManager().findFragmentById(R.id.details);
			if (details == null || details.getShownIndex() != index) {
				// make new fragment to show this selection
				details = DetailsFragment.newInstance(index);

				// execute a transaction, replacing any existing fragment
				// with this one.
				FragmentTransaction ft = getActivity().getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.details, details);
				ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
				ft.commit();
			}
		} else {
			// launch new activity to display fragment with selected text
			Intent intent = new Intent();
			intent.setClass(getActivity(), DetailsActivity.class);
			intent.putExtra("index", index);
			// startActivity(intent);
			startActivityForResult(intent, requestCode);
		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Activity activity = getActivity();
		if (resultCode == 1) {
			Log.i("Intent Result", "worked");
			Toast.makeText(activity, "It Worked!", Toast.LENGTH_LONG).show();
		} else {
			Log.i("Intent Result", "didn't work");
		}
	}

}
