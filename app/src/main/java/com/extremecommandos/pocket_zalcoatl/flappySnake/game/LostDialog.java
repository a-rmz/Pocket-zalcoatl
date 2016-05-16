package com.extremecommandos.pocket_zalcoatl.flappySnake.game;

import android.graphics.Rect;

import com.extremecommandos.pocket_zalcoatl.flappySnake.utils.Dimensions;

/**
 * Created by alex on 5/15/16.
 */
public class LostDialog {

    Dimensions dimensions;
    private Rect dialogFrame;
    private Rect btn;

    public LostDialog(Dimensions dimensions) {
        this.dimensions = dimensions;
        dialogFrame = new Rect(
                dimensions.getWidthPx()/2 - 200,
                dimensions.getHeightPx()/2 - 200,
                dimensions.getWidthPx()/2 + 200,
                dimensions.getHeightPx()/2 + 200
                );
        btn = new Rect(
                dialogFrame.centerX() - 40,
                dialogFrame.centerY() + 30,
                dialogFrame.centerX() + 40,
                dialogFrame.centerY() + 80
        );
    }

    public Rect getDialogFrame() {
        return dialogFrame;
    }

    public Rect getBtn() {
        return btn;
    }

}
