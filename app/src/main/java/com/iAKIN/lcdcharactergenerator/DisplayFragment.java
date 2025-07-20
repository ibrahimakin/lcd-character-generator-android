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
 * Use the {@link DisplayFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisplayFragment extends Fragment {

    public DisplayFragment() { } // Required empty public constructor

    public static DisplayFragment newInstance() { return new DisplayFragment(); }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_display, container, false);
        GridLayout gl = root.findViewById(R.id.display_grid);
        CustomButton[] cbs = new CustomButton[8];
        int gw = 10; int lw = 2;
        int[] rowRIds = {R.id.dA, R.id.dB, R.id.dC, R.id.dD, R.id.dE, R.id.dF, R.id.dG, R.id.dDP};
        int[][] bIndex = {{1, 2, lw, gw, 1}, {2, 3, gw, lw, 2}, {5, 3, gw, lw, 2}, {7, 2, lw, gw, 1}, {5, 1, gw, lw, 2}, {2, 1, gw, lw, 2}, {4, 2, lw, gw, 1}, {7, 4, lw, lw, 1}}; // { rowIndex, columnIndex, rowWeight, columnWeight, rowSpan }
        final int[] result = {0};
        for (int i = 0; i < 8; i++) {
            GridLayout.Spec rowSpec = GridLayout.spec(bIndex[i][0], bIndex[i][4], bIndex[i][2]);
            GridLayout.Spec colSpec = GridLayout.spec(bIndex[i][1], 1, bIndex[i][3]);
            CustomButton cb = new CustomButton(getContext(), i, (int) Math.pow(2, i));
            GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
            lp.rowSpec = rowSpec; lp.columnSpec = colSpec;
            lp.width = 0; lp.height = 0;
            cb.setOnClickListener(v -> {
                CustomButton button = (CustomButton) v;
                boolean isClicked = button.isClicked();
                int value = button.getValue();
                int row = button.getRow();
                String a = "0";
                if (isClicked) {
                    result[0] -= value;
                    button.setClicked(false);
                }
                else {
                    a = "1"; result[0] += value;
                    button.setClicked(true);
                }
                TextView tvr = root.findViewById(R.id.dR);
                TextView tv = root.findViewById(rowRIds[row]);
                tvr.setText(result[0]+""); tv.setText(a);
            });
            cbs[i] = cb;
            gl.addView(cb, lp);
        }
        Button clr = root.findViewById(R.id.clear_display);
        clr.setOnClickListener(v -> {
            TextView tvr = root.findViewById(R.id.dR);
            tvr.setText("0");
            result[0] = 0;
            for (int i = 0; i < 8; i++) {
                TextView tv = root.findViewById(rowRIds[i]);
                tv.setText("0");
                cbs[i].setClicked(false);
            }
        });
        return root;
    }
}