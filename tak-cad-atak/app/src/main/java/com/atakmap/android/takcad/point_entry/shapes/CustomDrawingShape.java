package com.atakmap.android.takcad.point_entry.shapes;

import com.atakmap.android.drawing.mapItems.DrawingShape;
import com.atakmap.android.maps.MapGroup;
import com.atakmap.android.maps.MapView;
import com.atakmap.android.maps.Marker;

public class CustomDrawingShape extends DrawingShape {
    public CustomDrawingShape(MapView mapView, MapGroup mapGroup, String uid) {
        super(mapView, mapGroup, uid);
    }

    public void setShapeMarker(Marker marker) {
        super.setShapeMarker(marker);
    }

    @Override
    public Marker getShapeMarker() {
        return super.getShapeMarker();
    }
}
