package com.hy.mdms.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    @BindView(R.id.true_btn)
    Button trueBtn;
    @BindView(R.id.false_btn)
    Button falseBtn;
    @BindView(R.id.question_text_view)
    TextView questionTextView;
    @BindView(R.id.next_btn)
    Button nextBtn;
    private TrueFalse[] questionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, true),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, true)
    };

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
       /* trueBtn= (Button) findViewById(R.id.true_btn);
        trueBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(QuizActivity.this, "True", Toast.LENGTH_SHORT).show();
            }
        });*/
        ButterKnife.bind(this);
        updateQuestion();
    }

    private void updateQuestion() {
        int question = questionBank[currentIndex].getQuestion();
        questionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTre) {
        boolean answerIsTrue = questionBank[currentIndex].isTrueQuestion();
        int messageResId = 0;
        if (userPressedTre == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.true_btn, R.id.false_btn,R.id.next_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.true_btn:
                checkAnswer(true);
                break;
            case R.id.false_btn:
                checkAnswer(false);
                break;
            case R.id.next_btn:
                currentIndex = (currentIndex + 1) % questionBank.length;
                updateQuestion();
                break;
        }
    }
}