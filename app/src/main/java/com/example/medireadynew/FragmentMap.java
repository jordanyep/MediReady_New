package com.example.medireadynew;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * Created by jordanyep on 2018-03-20.
 */

public class FragmentMap extends Fragment {
    Button policeSearch, hospitalSearch;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            //return inflater.inflate(R.layout.fragment_map, container, false);
        View v = inflater.inflate(R.layout.fragment_map, container, false);


        Button policeButton = (Button) v.findViewById(R.id.policeButton);
        policeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=police station");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        Button hospitalButton = (Button) v.findViewById(R.id.hospitalButton);
        hospitalButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Uri gmmIntentUri = Uri.parse("geo:0,0?q=hospitals");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
        return v;
    }


}
