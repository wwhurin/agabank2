package com.ghen61.agabankh;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by LG on 2018-06-13.
 */

public class CreateActivity extends AppCompatActivity {


    Button createB;
    Intent intent =null;

    EditText nameText;
    EditText numText;
    EditText monthText;
    EditText spendText;

    TextView mTextViewResult;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_create);


        createB = (Button) findViewById(R.id.createB);

        nameText = (EditText) findViewById(R.id.nameText);
        numText = (EditText) findViewById(R.id.numText);
        monthText = (EditText) findViewById(R.id.monthText);
        spendText = (EditText) findViewById(R.id.spendText);

        mTextViewResult = (TextView) findViewById(R.id.result);



        createB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getId = nameText.getText().toString();
                String getPw = numText.getText().toString();
                String getMonth = monthText.getText().toString();
                String getSpend = spendText.getText().toString();

                InsertData task = new InsertData();
                Log.d("!!!!!!", var.ID+"!!!!!!!!!!!"+var.PW);
                task.execute(getId, getPw, getMonth, getSpend);
            }
        });

    }


    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(CreateActivity.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            mTextViewResult.setText(result);//php에서 echo 해주는 내용 출력해준다
            if (result.equals("ok")) {
                Log.d("od", "dddd");
                Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                //intent.putExtra("id", Namego);
                startActivity(intent);
                finish();
            }
            //Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String id = (String) params[0];
            String pass = (String) params[1];
            String mlimit = (String) params[2];
            String slimit = (String) params[3];

            String serverURL = "http://wwhurin0834.dothome.co.kr/agabank/getCode.php";
            String postParameters = "id=" + id + "&pass=" + pass+"&mlimit="+mlimit+"&slimit="+slimit;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                //httpURLConnection.setRequestProperty("content-type", "application/json");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                //Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                } else {
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                //Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
    }

}
