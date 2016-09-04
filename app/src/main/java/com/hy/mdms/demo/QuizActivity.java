package com.hy.mdms.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    @BindView(R.id.true_btn)
    Button trueBtn;
    @BindView(R.id.false_btn)
    Button falseBtn;
    @BindView(R.id.question_text_view)
    TextView questionTextView;
    @BindView(R.id.next_btn)
    ImageButton nextBtn;
    @BindView(R.id.pre_btn)
    ImageButton preBtn;
    @BindView(R.id.cheat_btn)
    Button cheatBtn;

    private TrueFalse[] questionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_oceans, true),
            new TrueFalse(R.string.question_mideast, false),
            new TrueFalse(R.string.question_americas, true),
            new TrueFalse(R.string.question_asia, false)
    };

    private int currentIndex = 0;
    private boolean isCheater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        Log.d(TAG, "onCreate: called");
       /* trueBtn= (Button) findViewById(R.id.true_btn);
        trueBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(QuizActivity.this, "True", Toast.LENGTH_SHORT).show();
            }
        });*/
        ButterKnife.bind(this);

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX);
        }
        updateQuestion();
    }

    private void updateQuestion() {
        int question = questionBank[currentIndex].getQuestion();
        questionTextView.setText(question);
        isCheater=false;
    }

    private void checkAnswer(boolean userPressedTre) {
        boolean answerIsTrue = questionBank[currentIndex].isTrueQuestion();
        int messageResId = 0;
        if(isCheater){
            messageResId=R.string.judgment_toast;
        }else{
            if (userPressedTre == answerIsTrue) {
                messageResId = R.string.correct_toast;
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @OnClick({R.id.true_btn, R.id.false_btn, R.id.next_btn, R.id.pre_btn, R.id.question_text_view,R.id.cheat_btn})
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
            case R.id.question_text_view:
                currentIndex = (currentIndex + 1) % questionBank.length;
                updateQuestion();
                break;
            case R.id.pre_btn:
                if (currentIndex == 0) {
                    currentIndex = questionBank.length;
                }
                currentIndex = (currentIndex - 1) % questionBank.length;
                updateQuestion();
                break;
            case R.id.cheat_btn:
                Intent i=new Intent(QuizActivity.this,CheatActivity.class);
                boolean trueQuestion = questionBank[currentIndex].isTrueQuestion();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE,trueQuestion);
                startActivity(i);
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: called");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: called");
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putInt(KEY_INDEX, currentIndex);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if(data==null){
           return ;
       }
        isCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }
}