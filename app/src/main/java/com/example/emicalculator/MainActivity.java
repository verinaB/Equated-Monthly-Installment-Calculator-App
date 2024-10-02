package com.example.emicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declare variables for UI elements
    private EditText mortgageAmount, loanPeriod, interestRate;
    private Button calculateButton;
    private TextView emiResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the UI elements
        mortgageAmount = findViewById(R.id.mortgageAmount);
        loanPeriod = findViewById(R.id.Amortizationperiod);
        interestRate = findViewById(R.id.interestRate);
        calculateButton = findViewById(R.id.calculateButton);
        emiResult = findViewById(R.id.emiResult);

        // Set click listener for Calculate Button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateEMI();
            }
        });
    }

    // Method to calculate EMI
    private void calculateEMI() {
        // Get input values as Strings
        String principalStr = mortgageAmount.getText().toString();
        String periodStr = loanPeriod.getText().toString();
        String interestStr = interestRate.getText().toString();

        // Check if any field is empty
        if (principalStr.isEmpty() || periodStr.isEmpty() || interestStr.isEmpty()) {
            emiResult.setText("Please fill out all fields.");
            return;
        }

        // Convert inputs to numbers
        double principal = Double.parseDouble(principalStr);
        int period = Integer.parseInt(periodStr);
        double annualInterestRate = Double.parseDouble(interestStr);

        // Calculate EMI
        double monthlyInterestRate = annualInterestRate / 12 / 100;
        int months = period * 12;
        double emi = (principal * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, months)) /
                (Math.pow(1 + monthlyInterestRate, months) - 1);

        // Display the result
        emiResult.setText(String.format("Your EMI is: %.2f", emi));
    }
}
