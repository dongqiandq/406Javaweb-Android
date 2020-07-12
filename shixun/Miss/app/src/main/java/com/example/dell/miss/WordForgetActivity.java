package com.example.dell.miss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class WordForgetActivity extends AppCompatActivity {

    private ImageView imageView;
    private Boolean flag = true;
    private EditText phone;
    private EditText code;
    private Button getCode;
    private Button submit;

    private String phoneNum;
    private String codeNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_layout);

        imageView = findViewById(R.id.img_return);
        phone = findViewById(R.id.et_forget_phone);
        phoneNum=phone.getText().toString();

        code = findViewById(R.id.et_forget_yanzheng);
        codeNum=code.getText().toString();

        getCode = findViewById(R.id.btn_forget_getcode);
        submit = findViewById(R.id.btn_forget_submit);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WordForgetActivity.this,LoginActivity.class);
                startActivity(intent);
                WordForgetActivity.this.finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(WordForgetActivity.this,MineResetActivity.class);
                startActivity(intent1);
                WordForgetActivity.this.finish();
            }
        });



    }
}
