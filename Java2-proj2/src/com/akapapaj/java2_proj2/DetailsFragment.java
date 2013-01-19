package com.akapapaj.java2_proj2;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class DetailsFragment extends Fragment {
	public static DetailsFragment newInstance(int index) {
		DetailsFragment f = new DetailsFragment();
		Bundle args = new Bundle();
		args.putInt("index", index);
		f.setArguments(args);

		return f;
	}

	public int getShownIndex() {
		return getArguments().getInt("index", 0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		ScrollView scroller = new ScrollView(getActivity());
		TextView text = new TextView(getActivity());
		int padding = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, 4, getActivity().getResources()
						.getDisplayMetrics());
		text.setPadding(padding, padding, padding, padding);
		scroller.addView(text);
		String[] names = getResources().getStringArray(R.array.products_array);
		String[] values = getResources().getStringArray(R.array.desc);
		String[] skus = getResources().getStringArray(R.array.sku);
		String[] price = getResources().getStringArray(R.array.price);
		String[] urls = getResources().getStringArray(R.array.urls);
		Spanned sp = Html.fromHtml(urls[getShownIndex()]);
		text.setMovementMethod(LinkMovementMethod.getInstance());
		text.setText(sp);
		text.append("\n" + skus[getShownIndex()] + "\n"
				+ names[getShownIndex()] + "\n" + values[getShownIndex()]
				+ "\n" + price[getShownIndex()]);
		return scroller;
	}
}
