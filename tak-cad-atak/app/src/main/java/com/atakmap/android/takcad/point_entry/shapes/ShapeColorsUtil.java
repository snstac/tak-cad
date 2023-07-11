package com.atakmap.android.takcad.point_entry.shapes;

import com.atakmap.android.takcad.Constants;

public class ShapeColorsUtil {
    public static int getColorForShapeType(String shapeType){
        int color = Constants.DEFAULT_SHAPE_COLOR;
        if(shapeType.contentEquals(Constants.SHAPE_TYPE.GOTO.toString())){
            color = Constants.GOTO_SHAPE_COLOR;
        }else if(shapeType.contentEquals(Constants.SHAPE_TYPE.WATCH.toString())){
            color = Constants.WATCH_SHAPE_COLOR;
        }
        return color;
    }
}
