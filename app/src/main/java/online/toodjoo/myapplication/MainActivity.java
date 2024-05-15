package online.toodjoo.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {



        private EditText T1, T2;
        private Button B1, B2;
        private TextView T0;
        private ActivityResultLauncher<Intent> ptrpro;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            T0 = findViewById(R.id.textViewForResult);
            T1 = findViewById(R.id.editTextNumber1);
            T2 = findViewById(R.id.editTextNumber2);
            B1 = findViewById(R.id.button1);
            B2 = findViewById(R.id.button2);
            B2.setEnabled(false);


          ActivityResultLauncher<Intent>
                  ptrpro = registerForActivityResult(
                  new ActivityResultContracts.StartActivityForResult(),
                  new ActivityResultCallback<ActivityResult>() {
                      @Override
                      public void onActivityResult(ActivityResult result) {
                          if (result.getResultCode() == Activity.RESULT_OK) {
                              Intent data = result.getData();
                              if (data != null) {
                                  String resultValue = data.getStringExtra("toodjoo");
                                  if (resultValue != null) {
                                      T0.setText(resultValue);
                                  } else {
                                      Toast.makeText(MainActivity.this, "No result value received from MainActivity2", Toast.LENGTH_SHORT).show();
                                  }
                              } else {
                                  Toast.makeText(MainActivity.this, "No data received from MainActivity2", Toast.LENGTH_SHORT).show();
                              }
                          }
                      }
                  });



            B1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    B2.setEnabled(true);
                    if (!T1.getText().toString().isEmpty() && !T2.getText().toString().isEmpty()) {
                        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                        intent.putExtra("number1", T1.getText().toString());
                        intent.putExtra("number2", T2.getText().toString());


                        if (intent.resolveActivity(getPackageManager()) != null) {
                            ptrpro.launch(intent);
                        } else {
                            Toast.makeText(MainActivity.this, "No application can handle this action", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Please enter numbers!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            B2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sendBySms();
                }
            });
        }

        private void sendBySms() {
            SmsManager smsManager = SmsManager.getDefault();
            try {
                smsManager.sendTextMessage("+213669375344", null, T0.getText().toString(), null, null);
                Toast.makeText(MainActivity.this, "Message sent successfully :)", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, "Failed to send message!", Toast.LENGTH_SHORT).show();
            }
        }
    }

