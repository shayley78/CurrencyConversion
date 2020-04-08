package net.androidbootcamp.currencyconversion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    // conversion rates as of 4/7/2020
    double euroConversionRate = 0.916856;
    double pesoConversionRate = 24.0588;
    double canadianConversionRate = 1.39871;
    double dollarsEntered;
    double convertedAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        final EditText usDollars = (EditText)findViewById(R.id.txtAmtToConvert);
        final RadioButton euro = (RadioButton)findViewById(R.id.radEuros);
        final RadioButton peso = (RadioButton)findViewById(R.id.radMexicanPesos);
        final RadioButton canDollar = (RadioButton)findViewById(R.id.radCanadianDollars);
        final TextView result = (TextView)findViewById(R.id.txtResult);
        Button convert = (Button)findViewById(R.id.btnConvert);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dollarsEntered = Double.parseDouble(usDollars.getText().toString());
                DecimalFormat currency = new DecimalFormat("$###,###.##");
                DecimalFormat euroCurrency = new DecimalFormat("€###,###.##");
                DecimalFormat pesoCurrency = new DecimalFormat("₱###,###.##");
                DecimalFormat canCurrency = new DecimalFormat("Can$###,###.##");
                if(dollarsEntered <= 100000) {
                    if(euro.isChecked()) {
                        convertedAmount = dollarsEntered * euroConversionRate;
                        result.setText(currency.format(dollarsEntered) + " US Dollars is equal to " + euroCurrency.format(convertedAmount) + " Euros");
                    }
                    if(peso.isChecked()) {
                        convertedAmount = dollarsEntered * pesoConversionRate;
                        result.setText(currency.format(dollarsEntered) + " US Dollars is equal to " + pesoCurrency.format(convertedAmount) + " Mexican Pesos");
                    }
                    if(canDollar.isChecked()) {
                        convertedAmount = dollarsEntered * canadianConversionRate;
                        result.setText(currency.format(dollarsEntered) + " US Dollars is equal to " + canCurrency.format(convertedAmount) + " Canadian Dollars");
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "US Dollars must be $100,000 or less", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
