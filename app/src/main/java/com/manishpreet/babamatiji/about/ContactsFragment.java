package com.manishpreet.babamatiji.about;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.manishpreet.babamatiji.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {

    private ListView listView;
    private ContactListAdapter adapter;


    public ContactsFragment() {
        // Required empty public constructor
    }

    public static ContactsFragment instance;

    public static ContactsFragment getInstance()
    {
        if (instance == null)
        {
            instance = new ContactsFragment();
        }
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listView = view.findViewById(R.id.contact_list_view);
        adapter = new ContactListAdapter(getActivity());
        listView.setAdapter(adapter);


    }
}
