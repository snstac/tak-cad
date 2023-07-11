package com.atakmap.android.takcad;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.atakmap.android.takcad.plugin.R;
import com.atakmap.android.dropdown.DropDownReceiver;
import com.atakmap.android.ipc.AtakBroadcast;
import com.atakmap.android.maps.MapView;
import com.atakmap.android.takcad.receivers.ReportIncidentDropDownReceiver;
import com.atakmap.android.takcad.receivers.ActiveIncidentsDropDownReceiver;
import com.atakmap.coremap.log.Log;
import com.google.android.material.tabs.TabLayout;

public abstract class TabbedDropDownReceiver extends DropDownReceiver {
    private static final String TAG = TabbedDropDownReceiver.class.getSimpleName();

    private TabLayout.OnTabSelectedListener listener = new TabLayout.OnTabSelectedListener() {
        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            Log.d(TAG, "onTagSelected: Position = " + tab.getPosition());
            Intent intent = new Intent();
            if (tab.getPosition()==ownedTabIndex()) {
                return;
            }
            switch (tab.getPosition()) {
                case 0:
                    Log.i(TAG, "INCIDENT CREATOR");
                    intent.setAction(ReportIncidentDropDownReceiver.SHOW_INCIDENT_CREATOR);
                    break;
                case 1:
                    Log.i(TAG, "INCIDENT VIEWER");
                    intent.setAction(ActiveIncidentsDropDownReceiver.SHOW_INCIDENT_VIEWER);
                    break;
                default:
                    Log.e(TAG, "Unexpected tab entry encountered");
            }
            AtakBroadcast.getInstance().sendBroadcast(intent);
        }



        @Override
        public void onTabUnselected(TabLayout.Tab tab) {
            // ** NO-OP
        }

        @Override
        public void onTabReselected(TabLayout.Tab tab) {
            Log.d(TAG, "onTabReselected: Position = " + tab.getPosition());
            switch (tab.getPosition()) {
                case 0:
                    Log.v(TAG, "onTabReselected: INCIDENT CREATOR");
                    break;
                case 1:
                    Log.v(TAG, "onTabReselected: INCIDENT VIEWER");
                    break;
                default:
                    Log.e(TAG, "Unexpected tab entry encountered");
            }
        }
    };

    protected TabbedDropDownReceiver(MapView mapView) {
        super(mapView);
    }

    public void setupTabs(Context context, View mainView) {
        Log.d(TAG, "Adding a new on tab selected listener...");
        TabLayout tabs = mainView.findViewById(R.id.main_tab_layout);

//        LinearLayout layout = ((LinearLayout) ((LinearLayout) tabs.getChildAt(0)).getChildAt(4));
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout.getLayoutParams();
//        layoutParams.weight = 0f;
//        layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT;
//        layout.setLayoutParams(layoutParams);

        tabs.addOnTabSelectedListener(listener);
    }

    public void selectTab(View v, int index) {
        TabLayout tabs = v.findViewById(R.id.main_tab_layout);
        TabLayout.Tab tab = tabs.getTabAt(index);
        if (tab == null) {
            Log.e(TAG, "tab at index "+index+" was null");
            return;
        }
        tab.select();
    }

    public void removeTabsListener(View mainView) {
        TabLayout tabs = mainView.findViewById(R.id.main_tab_layout);
        tabs.removeOnTabSelectedListener(listener);
    }

    /**The index of the tab managed by this DropDownReceiver*/
    protected abstract int ownedTabIndex();

}
