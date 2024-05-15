package online.toodjoo.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity2 extends AppCompatActivity {
    private TextView t;
    private Button B3,B4;
    private double N1,N2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //link between xml and java with R class
        setContentView(R.layout.activity_main2);
        B3 = (Button) findViewById(R.id.button3);
        B4 = (Button) findViewById(R.id.button4);
        B4.setEnabled(false);
        t = (TextView) findViewById(R.id.t);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null || bundle.isEmpty()) {
            Toast.makeText(MainActivity2.this, "No Data received", Toast.LENGTH_LONG).show();
        } else {
            N1 = Double.parseDouble(bundle.getString("number1"));
            N2 = Double.parseDouble(bundle.getString("number2"));
            t.setText("N1: " + N1 + " N2: " + N2);
        }

        B3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                B4.setEnabled(true);
                Intent intent = new Intent();
                intent.putExtra("toodjoo",String.valueOf(clcMath(N1, N2)));
                t.setText("clc : "+clcMath(N1,N2));
                setResult(RESULT_OK, intent);
               finish();
            }
        });

        B4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("toodjoo",String.valueOf(clcMath(N1, N2)));
                setResult(RESULT_OK, intent);

                finish();
            }
        });





    }


    private double clcMath(double number1,double number2){
        return (number1+number2)/10;
    }

}