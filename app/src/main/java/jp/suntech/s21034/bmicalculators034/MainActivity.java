package jp.suntech.s21034.bmicalculators034;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btEnter = findViewById(R.id.btEnter);
        Button btClear = findViewById(R.id.btClear);
        ClickListener listener = new ClickListener();

        btEnter.setOnClickListener(listener);
        btClear.setOnClickListener(listener);

    }

    private class ClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            //EditTextオブジェクト取得
            EditText age = findViewById(R.id.etAge);
            EditText height = findViewById(R.id.etHeight);
            EditText weight = findViewById(R.id.etWeight);

            //TextViewオブジェクト取得
            TextView result1 = findViewById(R.id.result1);
            TextView result2 = findViewById(R.id.result2);
            TextView result3 = findViewById(R.id.result3);
            TextView result4 = findViewById(R.id.result4);
            TextView result5 = findViewById(R.id.result5);

            try {
                //入力された文字列取得
                String ageStr = age.getText().toString();
                String heightStr = height.getText().toString();
                String weightStr = weight.getText().toString();

                int id = view.getId();
                switch (id) {
                    case R.id.btEnter:
                        float f_age = Float.parseFloat(ageStr);
                        float f_height = Float.parseFloat(heightStr) / 100;
                        float f_weight = Float.parseFloat(weightStr);

                        float bmi;
                        float tekisei;
                        bmi = f_weight / (f_height * f_height);
                        tekisei = (f_height * f_height) * 22;
                        result1.setText(R.string.tv_result1);
                        result3.setText(R.string.tv_result3);
                        result4.setText(String.format("%.1f", tekisei));
                        result5.setText(R.string.tv_result5);


                        //肥満度分岐
                        if (bmi < 18.5) {
                            result2.setText("低体重");
                        } else if (bmi >= 18.5 && bmi < 25) {
                            result2.setText("普通体重");
                        } else if (bmi >= 25 && bmi < 30) {
                            result2.setText("肥満(1度)");
                        } else if (bmi >= 30 && bmi < 35) {
                            result2.setText("肥満(2度)");
                        } else if (bmi >= 35 && bmi < 40) {
                            result2.setText("肥満(3度)");
                        } else {
                            result2.setText("肥満(4度)");
                        }

                        //年齢分岐
                        if (f_age < 16) {
                            DialogFragment dialogFragment = new DialogFragment();
                            dialogFragment.show(getSupportFragmentManager(), "DialogFragment");
                        }
                        break;


                    case R.id.btClear:
                        age.setText("");
                        height.setText("");
                        weight.setText("");
                        result1.setText("");
                        result2.setText("");
                        result3.setText("");
                        result4.setText("");
                        result5.setText("");
                        break;

                }
            }catch (NumberFormatException e){
                System.out.println("空欄があります");
            }
        }
    }
}