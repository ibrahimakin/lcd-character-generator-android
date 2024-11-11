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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DisplayFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DisplayFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DisplayFragment newInstance(String param1, String param2) {
        DisplayFragment fragment = new DisplayFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_display, container, false);
        GridLayout gl = (GridLayout) root.findViewById(R.id.display_grid);
        CustomButton[] cbs = new CustomButton[8];
        int gw = 10; int lw = 2;
        int[] rowRIds = {R.id.dA, R.id.dB, R.id.dC, R.id.dD, R.id.dE, R.id.dF, R.id.dG, R.id.dDP};
        int[][] bIndex = {{0, 1, lw, gw}, {1, 2, gw, lw}, {3, 2, gw, lw}, {4, 1, lw, gw}, {3, 0, gw, lw}, {1, 0, gw, lw}, {2, 1, lw, gw}, {4, 3, lw, lw}}; // { rowIndex, columnIndex, rowWeight, columnWeight }
        String[] c = {"a", "b", "c", "d", "e", "f", "g", "dp" };
        final int[] result = {0};
        for (int i = 0; i < 8; i++) {
            GridLayout.Spec rowSpec = GridLayout.spec(bIndex[i][0], 1, bIndex[i][2]);
            GridLayout.Spec colSpec = GridLayout.spec(bIndex[i][1], 1, bIndex[i][3]);
            CustomButton cb = new CustomButton(getContext(), i, (int) Math.pow(2, i), c[i]);

            GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
            lp.rowSpec = rowSpec; lp.columnSpec = colSpec;
            lp.width = 0; lp.height = 0;
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
                    TextView tvr = (TextView) root.findViewById(R.id.dR);
                    TextView tv = (TextView) root.findViewById(rowRIds[row]);
                    tvr.setText(result[0]+""); tv.setText(a);
                }
            });
            cbs[i] = cb;
            gl.addView(cb, lp);
        }
        Button clr = (Button) root.findViewById(R.id.clear_display);
        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tvr = (TextView) root.findViewById(R.id.dR);
                tvr.setText("0");
                result[0] = 0;
                for (int i = 0; i < 8; i++) {
                    TextView tv = (TextView) root.findViewById(rowRIds[i]);
                    tv.setText("0");
                    cbs[i].setClicked(false);
                }
            }
        });
        return root;
    }
}