package com.ghen61.agabankh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class NoticeActivity extends AppCompatActivity {


    Button button;
    TextView nameT;
    TextView moneyT;
    Intent intent;


    String sendName;
    String sendMoney;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_result);


        intent = getIntent();

        sendName = intent.getStringExtra("name");
        sendMoney = intent.getStringExtra("money");

        button = (Button) findViewById(R.id.okB);
        nameT = (TextView) findViewById(R.id.name);
        moneyT = (TextView) findViewById(R.id.money);

        nameT.setText(sendName);
        moneyT.setText(sendMoney);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();

            }
        });


    }

    }
