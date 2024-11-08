package com.iAKIN.lcdcharactergenerator.CustomButton;

import android.content.Context;

public class CustomButton extends androidx.appcompat.widget.AppCompatButton {
    boolean isClicked = false;
    int value = 0;
    int row;

    public CustomButton(Context context, int row, int value, String c) {
        super(context);
        this.row = row;
        this.value = value;
        setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        setAllCaps(false);
        setText(c);
    }

    public CustomButton(Context context, int row) {
        super(context);
        this.row = row;
        setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setClicked(boolean clicked) {
        if (clicked) {
            setBackgroundColor(getResources().getColor(android.R.color.black));
            setTextColor(getResources().getColor(android.R.color.white));
        }
        else {
            setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            setTextColor(getResources().getColor(android.R.color.black));
        }
        isClicked = clicked;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
