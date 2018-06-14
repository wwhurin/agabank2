package com.ghen61.agabankh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by LG on 2018-06-13.
 */

public class SendActivity extends AppCompatActivity {

    Button button1;
    EditText name;
    EditText acc;
    EditText money;
    Spinner spinner;
    TextView myAcc;

    Intent intent = null;

    String sendName;
    String sendMoney;


    protected void onCreate(Bundle savedInstanceState) {

        Intent accIntent = getIntent();

        // 계좌번호가 들어가 있는 변수. 백엔드 친구들 이걸 이용하세요~!~
        String userAcc = accIntent.getStringExtra("acc");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_send);

        button1=(Button)findViewById(R.id.submitB);
        name=(EditText)findViewById(R.id.name);
        acc=(EditText)findViewById(R.id.acc);
        money=(EditText)findViewById(R.id.money);
        spinner=(Spinner)findViewById(R.id.spend);
        myAcc = (TextView)findViewById(R.id.myAcc);

        myAcc.setText(userAcc);





        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                sendMoney = money.getText().toString();
                sendName = name.getText().toString();

                intent = new Intent(SendActivity.this,NoticeActivity.class);
                intent.putExtra("name",sendName);
                intent.putExtra("money",sendMoney);
                startActivity(intent);
                finish();

            }
        });
    }
}