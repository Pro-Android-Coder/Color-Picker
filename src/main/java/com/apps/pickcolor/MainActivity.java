package com.apps.pickcolor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.skydoves.colorpickerview.ColorEnvelope;
import com.skydoves.colorpickerview.ColorPickerDialog;
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener;

public class MainActivity extends AppCompatActivity {

    Button button;
    CardView cardView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.setColor);
        cardView = findViewById(R.id.color);
        textView = findViewById(R.id.value);
    }

    public void setColor(View view) {
        changeColor();
    }

    private void changeColor() {
        new ColorPickerDialog.Builder(this)
                .setTitle("Choose Color")
                .setPreferenceName("ColorPickerDialog")
                .setPositiveButton(getString(R.string.confirm),
                        new ColorEnvelopeListener() {
                            @Override
                            public void onColorSelected(ColorEnvelope envelope, boolean fromUser) {
                                int[] argbArray = envelope.getArgb();
                                textView.setText(argbArray[1] + ", " + argbArray[2] + ", " + argbArray[3]);
                                setCardColor(envelope);
                                Toast.makeText(MainActivity.this, "Hex Code "+envelope.getHexCode(), Toast.LENGTH_LONG).show();
                            }
                        })
                .setNegativeButton(getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                .show();
    }

    private void setCardColor(ColorEnvelope envelope) {
        int color = envelope.getColor();
        cardView.setCardBackgroundColor(color);

        Window win = getWindow();
        win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        win.setStatusBarColor(color);
    }
}