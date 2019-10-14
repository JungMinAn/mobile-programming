package io.github.jungminan.assignment1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText t_id, t_pw;
    String s_id, s_pw;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
        Intent intent = getIntent();
        t_id = findViewById(R.id.idtext);
        t_pw = findViewById(R.id.passwordtext);
        tv = findViewById(R.id.testview);
        try {
            StringBuffer data = new StringBuffer();
            FileInputStream fis = openFileInput("myaccount.txt");
            BufferedReader buffer = new BufferedReader(new InputStreamReader(fis));
            String[] account;
            String str = buffer.readLine(); // 파일에서 한줄을 읽어옴
            account = str.split(" ");
            t_id.setText(account[0]);
            t_pw.setText(account[1]);
            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public boolean isaccount(View view, String[] iaccount){
        try {
            // 파일에서 읽은 데이터를 저장하기 위해서 만든 변수
            StringBuffer data = new StringBuffer();
            FileInputStream fis = openFileInput("account.txt");//파일명
            BufferedReader buffer = new BufferedReader(new InputStreamReader(fis));
            String[] account;
            String str = buffer.readLine(); // 파일에서 한줄을 읽어옴
            while (str != null) {
                account = str.split(" ");
                //tv.append("\n" + account[0] + " " + account[1]);
                if (account[0].equals(iaccount[0])){
                    if (account[1].equals(iaccount[1])){
                        return true;
                    }
                    else{
                        Toast.makeText(MainActivity.this, "비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                        t_pw.requestFocus();
                        return false;
                    }
                }
                str = buffer.readLine();
            }
            Toast.makeText(MainActivity.this, "존재하지 않는 ID 입니다.", Toast.LENGTH_SHORT).show();
            t_id.requestFocus();
            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void saveaccount(String str) {
        try {
            FileOutputStream fos = openFileOutput
                    ("account.txt", // 파일명 지정
                            Context.MODE_APPEND);// 저장모드
            PrintWriter out = new PrintWriter(fos);
            out.println(str);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void savemyaccount(String str) {
        try {
            FileOutputStream fos = openFileOutput
                    ("myaccount.txt", // 파일명 지정
                            Context.MODE_PRIVATE);// 저장모드
            PrintWriter out = new PrintWriter(fos);
            out.println(str);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void bt_sign_in(View view)
    {
        s_id = t_id.getText().toString();
        s_pw = t_pw.getText().toString();
        String[] account = {s_id, s_pw};
        //tv.append("\n" + account[0] + " " + account[1]);
        if (isaccount(view, account)){
            //tv.append("\n" + account[0] + "& " + account[1] + "is login");
            Intent calcintent = new Intent(MainActivity.this, Calculator.class);
            startActivity(calcintent);
        }
    }

    public void bt_sign_up(View view)
    {
        Intent suintent = new Intent(MainActivity.this, SignUp.class);
        String accountlist = "";
        try {
            // 파일에서 읽은 데이터를 저장하기 위해서 만든 변수
            StringBuffer data = new StringBuffer();
            FileInputStream fis = openFileInput("account.txt");//파일명
            BufferedReader buffer = new BufferedReader(new InputStreamReader(fis));
            String[] account;
            String str = buffer.readLine(); // 파일에서 한줄을 읽어옴
            while (str != null) {
                account = str.split(" ");
                accountlist += account[0];
                accountlist += " ";
                str = buffer.readLine();
            }
            buffer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        suintent.putExtra("accountlist", accountlist);

        startActivityForResult(suintent, 1111);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1111){
            if(resultCode==RESULT_OK) {
                String Newaccount = data.getStringExtra("account");
                saveaccount(Newaccount);
                savemyaccount(Newaccount);
            }
        }
    }
}
