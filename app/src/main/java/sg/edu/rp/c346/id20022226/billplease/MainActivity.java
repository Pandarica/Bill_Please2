package sg.edu.rp.c346.id20022226.billplease;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView tv4;
    TextView tv5;
    TextView tv6;
    TextView tv7;
    ToggleButton tb1;
    ToggleButton tb2;
    Button bt1;
    Button bt2;
    EditText et1;
    EditText et2;
    EditText et3;
    RadioGroup rg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tb1 = findViewById(R.id.tb1);
        tb2 = findViewById(R.id.tb2);
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        et3 = findViewById(R.id.et3);
        rg = findViewById(R.id.rg);

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                String amount = et1.getText().toString();
                String pax = et2.getText().toString();
                String percent = et3.getText().toString();

                int amt = parseInt(amount);
                int no_of_ppl = parseInt(pax);
                double pct_discount = parseDouble(percent);
                int id = rg.getCheckedRadioButtonId();
                double discount = pct_discount / 100;
                double amt_discounted = 1.00 - discount;
                double aft_discount = amt * amt_discounted;

                if (amount.isEmpty() || pax.isEmpty() || percent.isEmpty()) {
                    tv7.setText("An input is missing");
                } else if (amt > 10000 || no_of_ppl > 100 || pct_discount > 100) {
                    tv7.setText("A value is invalid");
                } else {
                    double total = 0.0;

                    if (tb1.isChecked() == true && tb2.isChecked() == false) {
                        total = aft_discount * 1.1;
                    } else if (tb1.isChecked() == false && tb2.isChecked() == true) {
                        total = aft_discount * 1.07;
                    } else if (tb1.isChecked() == true && tb2.isChecked() == true) {
                        total = aft_discount * 1.1 * 1.07;
                    } else {
                        total = aft_discount;
                    }

                    String total_bill = String.format("%.2f", (total));
                    String each_pay = String.format("%.2f", (total / no_of_ppl));

                    tv4.setText("Total Bill: $" + total_bill);
                    if (id == R.id.rb1) {
                        tv5.setText("Each pays: $" + each_pay + " in cash");
                        tv6.setText("");
                    } else if (id == R.id.rb2) {
                        tv5.setText("Each pays: $" + each_pay + " via PayNow to");
                        tv6.setText("912345678");
                    }
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Code for the action
                et1.setText("");
                et2.setText("");
                et3.setText("");
                tb1.setChecked(false);
                tb2.setChecked(false);
                tv4.setText("");
                tv5.setText("");
                tv6.setText("");
                tv7.setText("");
            }
        });
    }
}