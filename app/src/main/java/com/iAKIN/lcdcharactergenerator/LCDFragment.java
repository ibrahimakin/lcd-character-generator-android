package com.iAKIN.lcdcharactergenerator;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import com.iAKIN.lcdcharactergenerator.CustomButton.CustomButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LCDFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LCDFragment extends Fragment {

    public LCDFragment() { } // Required empty public constructor

    public static LCDFragment newInstance() { return new LCDFragment(); }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_lcd, container, false);
        GridLayout gl = root.findViewById(R.id.lcd_grid);
        CustomButton[][] cbs = new CustomButton[8][5];
        int[] rowRIds = {R.id.rowR1, R.id.rowR2, R.id.rowR3, R.id.rowR4, R.id.rowR5, R.id.rowR6, R.id.rowR7, R.id.rowR8};
        int[] rowIds = {R.id.row1, R.id.row2, R.id.row3, R.id.row4, R.id.row5, R.id.row6, R.id.row7, R.id.row8};
        int[] rowSum = {0, 0, 0, 0, 0, 0, 0, 0};
        for (int i = 1; i < gl.getRowCount(); i++) {
            GridLayout.Spec rowSpec = GridLayout.spec(i, 1, 1);
            for (int j = 1; j < gl.getColumnCount()-1; j++) {
                GridLayout.Spec colSpec = GridLayout.spec(j, 1, 1);
                CustomButton cb = new CustomButton(getContext(), i-1);
                if (j == 1) cb.setValue(16);
                else if (j == 2) cb.setValue(8);
                else if (j == 3) cb.setValue(4);
                else if (j == 4) cb.setValue(2);
                else cb.setValue(1);
                GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
                lp.rowSpec = rowSpec; lp.columnSpec = colSpec;
                lp.width = 0; lp.height = 0;
                lp.setMargins(5, 5, 5, 5);
                cb.setOnClickListener(v -> {
                    CustomButton button = (CustomButton) v;
                    boolean isClicked = button.isClicked();
                    int value = button.getValue();
                    int row = button.getRow();
                    if (isClicked) {
                        rowSum[row] -= value;
                        button.setClicked(false);
                    }
                    else {
                        rowSum[row] += value;
                        button.setClicked(true);
                    }
                    TextView tvr = root.findViewById(rowRIds[row]);
                    TextView tv = root.findViewById(rowIds[row]);
                    tvr.setText(rowSum[row]+""); tv.setText(rowSum[row]+"");
                });
                cbs[i-1][j-1] = cb;
                gl.addView(cb, lp);
            }
        }
        Button clr = root.findViewById(R.id.clear_lcd);
        clr.setOnClickListener(v -> {
            for (int i = 0; i < 8; i++) {
                TextView tvr = root.findViewById(rowRIds[i]);
                TextView tv = root.findViewById(rowIds[i]);
                tvr.setText("0"); tv.setText("0"); rowSum[i] = 0;
                for (int j = 0; j < 5; j++) cbs[i][j].setClicked(false);
            }
        });
        return root;
    }
}