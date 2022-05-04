package sg.edu.rp.c346.id21008946.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    Button splitBtn;
    EditText AmountFields;
    EditText PaxFields;
    EditText DiscountFields;
    TextView ResultView;
    ToggleButton SVStb;
    ToggleButton GSTtb;
    RadioGroup PayMethodrg;
    RadioButton Cashrb;
    RadioButton Paynowrb;
    TextView Resettv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splitBtn = findViewById(R.id.btnSplit);
        AmountFields = findViewById(R.id.etAmount);
        PaxFields = findViewById(R.id.etPax);
        DiscountFields = findViewById(R.id.etDiscount);
        ResultView = findViewById(R.id.tvResult);
        SVStb = findViewById(R.id.tbSVS);
        GSTtb = findViewById(R.id.tbGST);
        SVStb = findViewById(R.id.tbSVS);
        PayMethodrg = findViewById(R.id.rgPayMethod);
        Cashrb = findViewById(R.id.rbCash);
        Paynowrb = findViewById(R.id.rbPaynow);
        Resettv = findViewById(R.id.tvReset);


        splitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double total_amount = Integer.parseInt(AmountFields.getText().toString());
                double pax = Integer.parseInt(PaxFields.getText().toString());
                double discount = Integer.parseInt(DiscountFields.getText().toString());
                double final_amount = total_amount -(total_amount * (discount/100));
                if(SVStb.isChecked())
                {
                    final_amount = final_amount * 1.1;
                }
                if(GSTtb.isChecked())
                {
                    final_amount = final_amount * 1.07;
                }
                double split_amount = final_amount/pax;
                if(Cashrb.isChecked())
                {
                    String result = String.format("Total Bill: $%.2f\nEach Pays: $%.2f in cash",final_amount,split_amount);
                    ResultView.setText(result);
                }
                else
                {
                    String result = String.format("Total Bill: $%.2f\nEach Pays: $%.2f via PayNow to 91111111",final_amount,split_amount);
                    ResultView.setText(result);
                }
                
            }
        });

        Resettv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AmountFields.setText("");
                PaxFields.setText("");
                SVStb.setChecked(false);
                GSTtb.setChecked(true);
                DiscountFields.setText("");
                Cashrb.setChecked(true);
                Paynowrb.setChecked(false);
            }
        });
    }
}