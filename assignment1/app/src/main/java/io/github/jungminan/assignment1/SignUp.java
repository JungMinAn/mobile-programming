package io.github.jungminan.assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    EditText t_id, t_pw, t_pwc, t_name, t_phone, t_addr;
    boolean canuseid = false, idchecked = false;
    RadioButton agreebtn;
    String[] accountlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        t_id = findViewById(R.id.etId);
        t_pw = findViewById(R.id.etPassword);
        t_pwc = findViewById(R.id.etPasswordConfirm);
        t_name= findViewById(R.id.etName);
        t_phone = findViewById(R.id.etPhone);
        t_addr = findViewById(R.id.etAddress);
        canuseid = false;
        idchecked = false;
        agreebtn = findViewById(R.id.agree_btn);
        Intent intent = getIntent();
        String accountliststring = intent.getStringExtra("accountlist");
        accountlist= accountliststring.split(" ");

    }

    public void IdUsable(View view){
        idchecked = true;
        for (int i = 0; i<accountlist.length; i++){
            if (TextUtils.isEmpty(t_id.getText())){
                Toast.makeText(SignUp.this, "먼저 ID를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                break;
            }
            if (t_id.getText().toString().equals(accountlist[i])){
                Toast.makeText(SignUp.this, "이미 존재하는 ID 입니다.", Toast.LENGTH_SHORT).show();
            }
            else if (i == accountlist.length-1){
                Toast.makeText(SignUp.this, "사용 가능한 ID 입니다.", Toast.LENGTH_SHORT).show();
                canuseid = true;
            }
        }
    }

    public void makeaccount(View view){
        // 아이디 입력 확인
        if( t_id.getText().toString().length() == 0 ) {
            Toast.makeText(SignUp.this, "ID를 입력하세요.", Toast.LENGTH_SHORT).show();
            t_id.requestFocus();
            return;
        }

        // 아이디 중복 검사 여부 확인
        if(!idchecked) {
            Toast.makeText(SignUp.this, "ID 중복 여부를 확인하세요.", Toast.LENGTH_SHORT).show();
            t_id.requestFocus();
            return;
        }

        // 사용 가능한 아이디인지 확인
        if(!canuseid) {
            Toast.makeText(SignUp.this, "사용 불가능한 ID 입니다.", Toast.LENGTH_SHORT).show();
            t_id.requestFocus();
            return;
        }

        // 비밀번호 입력 확인
        if( t_pw.getText().toString().length() == 0 ) {
            Toast.makeText(SignUp.this, "비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show();
            t_pw.requestFocus();
            return;
        }

        // 비밀번호 길이 확인
        if( t_pw.getText().toString().length() < 8 ) {
            Toast.makeText(SignUp.this, "비밀번호는 8자리 이상이여야 합니다.", Toast.LENGTH_SHORT).show();
            t_pw.requestFocus();
            return;
        }

        // 비밀번호 확인 입력 확인
        if( t_pwc.getText().toString().length() == 0 ) {
            Toast.makeText(SignUp.this, "비밀번호 확인을 입력하세요.", Toast.LENGTH_SHORT).show();
            t_pwc.requestFocus();
            return;
        }

        // 비밀번호 일치 확인
        if( !t_pw.getText().toString().equals(t_pwc.getText().toString()) ) {
            Toast.makeText(SignUp.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            t_pw.setText("");
            t_pwc.setText("");
            t_pw.requestFocus();
            return;
        }

        // 이름 입력 확인
        if( t_name.getText().toString().length() == 0 ) {
            Toast.makeText(SignUp.this, "이름을 입력하세요.", Toast.LENGTH_SHORT).show();
            t_name.requestFocus();
            return;
        }

        // 전화번호 입력 확인
        if( t_phone.getText().toString().length() == 0 ) {
            Toast.makeText(SignUp.this, "전화번호를 입력하세요.", Toast.LENGTH_SHORT).show();
            t_phone.requestFocus();
            return;
        }

        // 약관 동의 확인
        if(!agreebtn.isChecked()) {
            Toast.makeText(SignUp.this, "약관 동의에 체크해주세요.", Toast.LENGTH_SHORT).show();
            agreebtn.requestFocus();
            return;
        }

        String str = t_id.getText().toString() + " " + t_pw.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("account", str);
        setResult(RESULT_OK, intent);
        finish();
    }


}
