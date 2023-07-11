package com.atakmap.android.takcad;

import android.graphics.Color;

import androidx.annotation.NonNull;

public class Constants {

    // Shape types
    public static final String SHAPE_TYPE_META_ATTR_NAME = "shape_type";
    public static final int DEFAULT_SHAPE_COLOR = Color.rgb(255, 255, 255); // white
    public static final int DEFAULT_SHAPE_COLOR_SELECTED = Color.rgb(0, 107, 179);// darker blue
    public static final int GOTO_SHAPE_COLOR = Color.rgb(255, 255, 255); // white
    public static final int GOTO_SHAPE_COLOR_SELECTED = Color.rgb(0, 107, 179);// darker blue
    public static final int WATCH_SHAPE_COLOR = Color.rgb(255, 255, 255); // white
    public static final int WATCH_SHAPE_COLOR_SELECTED = Color.rgb(0, 107, 179);// darker blue
    public static final int UNSELECTED_SHAPE_FILL_OPACITY = 200;
    public static final int SELECTED_SHAPE_FILL_OPACITY = 200;
    public static final int UNSELECTED_SHAPE_STROKE_COLOR = Color.rgb(255, 255, 255); // white
    public static final int SELECTED_SHAPE_STROKE_COLOR = Color.rgb(57, 0, 156);// dark blue

    // Map item selections
    public static final Integer MAP_OBJECT_SELECTED_TEXT_COLOR = Color.MAGENTA;
    public static final Integer MAP_AGENT_UNSELECTED_TEXT_COLOR = Color.WHITE;
    public static final Integer MAP_OBJECT_UNSELECTED_TEXT_COLOR = Color.GREEN;

    public enum SHAPE_TYPE {
        UNSPECIFIED("Default"),
        GOTO("GoTo"),
        WATCH("Watch");

        private final String text;

        /**
         * @param text
         */
        SHAPE_TYPE(final String text) {
            this.text = text;
        }

        /* (non-Javadoc)
         * @see java.lang.Enum#toString()
         */
        @NonNull
        @Override
        public String toString() {
            return text;
        }
    }

    public static final double MINIMUM_POLYLINE_POINT_DISTANCE_METERS = 1.0;
    public static final double FEET_METERS_RATIO = 0.3048;


}
