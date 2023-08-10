package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btSomar;
    Button btSubtrair;
    Button btDividir;
    Button btMultiplicar;
    Button btClear; // Novo botão para limpar
    TextView tv1;
    EditText edNum1;
    EditText edNum2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar um ouvinte de toque para a raiz do layout
        View rootView = findViewById(android.R.id.content);
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(); // Esconder o teclado
            }
        });

        // Inicializar os elementos da interface
        btSomar = findViewById(R.id.button);
        btSubtrair = findViewById(R.id.button2);
        btMultiplicar = findViewById(R.id.button3);
        btDividir = findViewById(R.id.button4);
        btClear = findViewById(R.id.buttonClear); // Encontrar o botão de limpar

        edNum1 = findViewById(R.id.editText1);
        edNum2 = findViewById(R.id.editText2);
        tv1 = findViewById(R.id.result);

        // Definir listeners para os botões
        btSomar.setOnClickListener(this);
        btSubtrair.setOnClickListener(this);
        btMultiplicar.setOnClickListener(this);
        btDividir.setOnClickListener(this);
        btClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        float n1;
        float n2;
        float result = 0;

        hideKeyboard(); // Ocultar o teclado

        if (view.getId() == R.id.buttonClear) {
            limpar();
            return;
        }

        String num1Str = edNum1.getText().toString().trim();
        String num2Str = edNum2.getText().toString().trim();

        if (num1Str.isEmpty() || num2Str.isEmpty() || num1Str.contains(" ") || num2Str.contains(" ")) {
            if (num1Str.isEmpty() && num2Str.isEmpty()) {
                edNum1.setError("Preencha!");
                edNum2.setError("Preencha!");
            } else if (num1Str.isEmpty()) {
                edNum1.setError("Você esqueceu de digitar aqui!");
            } else {
                edNum2.setError("Você esqueceu de digitar aqui!");
            }
            return;
        }


            n1 = Integer.parseInt(num1Str);
            n2 = Integer.parseInt(num2Str);

            if (view.getId() == R.id.button) {
                result = n1 + n2;
            } else if (view.getId() == R.id.button2) {
                result = n1 - n2;
            } else if (view.getId() == R.id.button3) {
                result = n1 * n2;
            } else if (view.getId() == R.id.button4) {
                if (n2 != 0) {
                    result = n1 / n2;
                } else {
                    tv1.setText("Não é possível dividir por zero!");
                    return;
                }
            }

        String resultado;

        if (result == (int) result) {
            resultado = String.format("%d", (int) result);
        } else {
            resultado = String.format("%.2f", result);
        }

        tv1.setText("Resultado: " + resultado);

    }





    // Método para limpar os campos de entrada e o resultado
    public void limpar() {
        edNum1.getText().clear();
        edNum2.getText().clear();
        tv1.setText("Resultado:");
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null && getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }
}
