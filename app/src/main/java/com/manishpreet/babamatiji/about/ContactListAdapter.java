package com.manishpreet.babamatiji.about;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.manishpreet.babamatiji.R;

public class ContactListAdapter extends BaseAdapter {

    Context context;

    TypedArray contactImage;
    String [] contactNames;
    String [] contactNumbers;
    String [] contactEmails;


    public ContactListAdapter(Context context) {
        this.context = context;
        contactImage = context.getResources().obtainTypedArray(R.array.contact_image);
        contactNames = context.getResources().getStringArray(R.array.contact_name);
        contactNumbers = context.getResources().getStringArray(R.array.contact_number);
        contactEmails = context.getResources().getStringArray(R.array.contact_email);
    }

    @Override
    public int getCount() {
        return contactNames.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        view = LayoutInflater.from(context).inflate(R.layout.contact_list_layout_single_item, parent, false);

        ImageView image = view.findViewById(R.id.iv_contact_image);
        TextView name = view.findViewById(R.id.tv_contact_name);
        TextView phone = view.findViewById(R.id.tv_contact_phone);
        TextView email = view.findViewById(R.id.tv_contact_email);


        image.setImageResource(contactImage.getResourceId(position, 0));
        name.setText(contactNames[position]);
        phone.setText(contactNumbers[position]);
        email.setText(contactEmails[position]);


        return view;
    }
}
