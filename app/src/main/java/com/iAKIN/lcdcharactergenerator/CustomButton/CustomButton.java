package com.iAKIN.lcdcharactergenerator.CustomButton;

import android.annotation.SuppressLint;
import android.content.Context;
import com.iAKIN.lcdcharactergenerator.R;

@SuppressLint("ViewConstructor")
public class CustomButton extends androidx.appcompat.widget.AppCompatButton {
    boolean isClicked = false;
    int value = 0;
    int row;

    public CustomButton(Context context, int row, int value) {
        super(context);
        this.row = row;
        this.value = value;
        setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
    }

    public CustomButton(Context context, int row) {
        super(context);
        this.row = row;
        setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
    }

    public void setClicked(boolean clicked) {
        if (clicked) setBackgroundColor(getResources().getColor(R.color.yellow));
        else setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        isClicked = clicked;
    }

    public boolean isClicked() { return isClicked; }

    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }

    public int getRow() { return row; }
}