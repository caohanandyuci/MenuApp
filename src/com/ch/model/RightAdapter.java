package com.ch.model;

import java.util.List;

import com.ch.entity.Product;
import com.ch.menuapp.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class RightAdapter extends BaseAdapter implements StickyListHeadersAdapter, SectionIndexer {

	private final Context mContext;
	private String[] mCountries;
	private int[] mSectionIndices;
	private Character[] mSectionLetters;
	private LayoutInflater mInflater;

	private List<Product> mProducts = null;
	
	public RightAdapter(Context context,List<Product> products) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mProducts = products;
		mCountries = context.getResources().getStringArray(R.array.countries);
		mSectionIndices = getSectionIndices();
		mSectionLetters = getSectionLetters();
	}

	private int[] getSectionIndices() {
		ArrayList<Integer> sectionIndices = new ArrayList<Integer>();
		char lastFirstChar = mCountries[0].charAt(0);
		sectionIndices.add(0);
		for (int i = 1; i < mCountries.length; i++) {
			if (mCountries[i].charAt(0) != lastFirstChar) {
				lastFirstChar = mCountries[i].charAt(0);
				sectionIndices.add(i);
			}
		}
		int[] sections = new int[sectionIndices.size()];
		for (int i = 0; i < sectionIndices.size(); i++) {
			sections[i] = sectionIndices.get(i);
		}
		return sections;
	}

	private Character[] getSectionLetters() {
		Character[] letters = new Character[mSectionIndices.length];
		for (int i = 0; i < mSectionIndices.length; i++) {
			letters[i] = mCountries[mSectionIndices[i]].charAt(0);
		}
		return letters;
	}

	@Override
	public int getCount() {
		return mCountries.length;
	}

	@Override
	public Object getItem(int position) {
		return mCountries[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = LayoutInflater.from(mContext).inflate(R.layout.product, null);
		ImageView imageView = (ImageView) convertView.findViewById(R.id.productbg);
		ImageView addviewImageView = (ImageView) convertView.findViewById(R.id.addbg);
		addviewImageView.setBackground(mContext.getResources().getDrawable(R.drawable.add));
		ImageView subviewImageView = (ImageView) convertView.findViewById(R.id.subbg);
		subviewImageView.setBackground(mContext.getResources().getDrawable(R.drawable.sub));
		imageView.setImageDrawable(mContext.getResources().getDrawable(R.drawable.timg));
		return convertView;

	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		HeaderViewHolder holder;

		if (convertView == null) {
			holder = new HeaderViewHolder();
			convertView = mInflater.inflate(R.layout.header, parent, false);
			holder.text = (TextView) convertView.findViewById(R.id.text1);
			convertView.setTag(holder);
		} else {
			holder = (HeaderViewHolder) convertView.getTag();
		}

		//CharSequence headerChar = mCountries[position].subSequence(0, 1);
		//holder.text.setText(headerChar);

		String category = "this is category:"+mProducts.get(position).mCategory;
		holder.text.setText(category);
		return convertView;
	}

	@Override
	public long getHeaderId(int position) {
		//return mCountries[position].subSequence(0, 1).charAt(0);
		return mProducts.get(position).mCategory;
	}

	@Override
	public int getPositionForSection(int section) {
		if (section >= mSectionIndices.length) {
			section = mSectionIndices.length - 1;
		} else if (section < 0) {
			section = 0;
		}
		return mSectionIndices[section];
	}

	@Override
	public int getSectionForPosition(int position) {
		for (int i = 0; i < mSectionIndices.length; i++) {
			if (position < mSectionIndices[i]) {
				return i - 1;
			}
		}
		return mSectionIndices.length - 1;
	}

	@Override
	public Object[] getSections() {
		return mSectionLetters;
	}

	public void clear() {
		mCountries = new String[0];
		mSectionIndices = new int[0];
		mSectionLetters = new Character[0];
		notifyDataSetChanged();
	}

	public void restore() {
		mCountries = mContext.getResources().getStringArray(R.array.countries);
		mSectionIndices = getSectionIndices();
		mSectionLetters = getSectionLetters();
		notifyDataSetChanged();
	}

	class HeaderViewHolder {
		TextView text;
	}

	class ViewHolder {
		TextView text;
	}
