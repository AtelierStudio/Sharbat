package kr.edcan.sharbat.activity;

import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import kr.edcan.sharbat.R;

public class PasswordAuthActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView head01, head02, head03, head04, head05, head06, delete, cancel;
    ImageView passcode01, passcode02, passcode03, passcode04, passcode05, passcode06, passcode07, passcode08, passcode09, passcode00;
    ImageView head[];
    String inputPasscode = "";
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_auth);
        setDefault();
    }

    private void setDefault() {
        count = 0;
        head01 = (ImageView) findViewById(R.id.passauth_header_1);
        head02 = (ImageView) findViewById(R.id.passauth_header_2);
        head03 = (ImageView) findViewById(R.id.passauth_header_3);
        head04 = (ImageView) findViewById(R.id.passauth_header_4);
        head05 = (ImageView) findViewById(R.id.passauth_header_5);
        head06 = (ImageView) findViewById(R.id.passauth_header_6);
        passcode01 = (ImageView) findViewById(R.id.passauth_passcode1);
        passcode02 = (ImageView) findViewById(R.id.passauth_passcode2);
        passcode03 = (ImageView) findViewById(R.id.passauth_passcode3);
        passcode04 = (ImageView) findViewById(R.id.passauth_passcode4);
        passcode05 = (ImageView) findViewById(R.id.passauth_passcode5);
        passcode06 = (ImageView) findViewById(R.id.passauth_passcode6);
        passcode07 = (ImageView) findViewById(R.id.passauth_passcode7);
        passcode08 = (ImageView) findViewById(R.id.passauth_passcode8);
        passcode09 = (ImageView) findViewById(R.id.passauth_passcode9);
        passcode00 = (ImageView) findViewById(R.id.passauth_passcode0);
        cancel = (ImageView) findViewById(R.id.passauth_cancel);
        delete = (ImageView) findViewById(R.id.passauth_delete);
        head = new ImageView[]{head01, head02, head03, head04, head05, head06};
        passcode01.setOnClickListener(this);
        passcode02.setOnClickListener(this);
        passcode03.setOnClickListener(this);
        passcode04.setOnClickListener(this);
        passcode05.setOnClickListener(this);
        passcode06.setOnClickListener(this);
        passcode07.setOnClickListener(this);
        passcode08.setOnClickListener(this);
        passcode09.setOnClickListener(this);
        passcode00.setOnClickListener(this);
        cancel.setOnClickListener(this);
        delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int result = 0;
        switch (view.getId()) {
            case R.id.passauth_passcode1:
                result = 1;
                appendPassword(result);
                break;
            case R.id.passauth_passcode2:
                result = 2;
                appendPassword(result);
                break;
            case R.id.passauth_passcode3:
                result = 3;
                appendPassword(result);
                break;
            case R.id.passauth_passcode4:
                result = 4;
                appendPassword(result);
                break;
            case R.id.passauth_passcode5:
                result = 5;
                appendPassword(result);
                break;
            case R.id.passauth_passcode6:
                result = 6;
                appendPassword(result);
                break;
            case R.id.passauth_passcode7:
                result = 7;
                appendPassword(result);
                break;
            case R.id.passauth_passcode8:
                result = 8;
                appendPassword(result);
                break;
            case R.id.passauth_passcode9:
                result = 9;
                appendPassword(result);
                break;
            case R.id.passauth_passcode0:
                result = 0;
                appendPassword(result);
                break;
            case R.id.passauth_delete:
                if (count != 0) {
                    count--;
                    inputPasscode = deleteOne(inputPasscode);
                    head[count].setImageDrawable(getResources().getDrawable(R.drawable.pivot_round_square_disable));
                }
                break;
        }
    }

    private void appendPassword(int result) {
        inputPasscode += result;
        for (int i = 0; i <= count; i++) {
            head[i].setImageDrawable(getResources().getDrawable(R.drawable.pivot_round_square));
        }
        Log.e("ASdf", count + "");
        if (count == 5) validatePasscode();
        else count++;
    }

    private void validatePasscode() {
        final String passcode = "123456";
        if (inputPasscode.equals(passcode)) {
            Toast.makeText(this, "인증성공", Toast.LENGTH_SHORT).show();
            inputPasscode = "";
            count = 0;
            cleanHeader();
        } else {
            Toast.makeText(this, "인증실패", Toast.LENGTH_SHORT).show();
            Vibrator vb = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            vb.vibrate(500);
            inputPasscode = "";
            count = 0;
            cleanHeader();
        }
    }

    private void cleanHeader() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (ImageView i : head) {
                    i.setImageDrawable(getResources().getDrawable(R.drawable.pivot_round_square_disable));
                }
            }
        }, 100);
    }

    private String deleteOne(String s) {
        return String.valueOf(Integer.parseInt(s) / 10);
    }
}
