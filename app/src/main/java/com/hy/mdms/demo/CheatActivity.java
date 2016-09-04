package com.hy.mdms.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CheatActivity extends AppCompatActivity {
    public static final String EXTRA_ANSWER_IS_TRUE = "com.hy.mdms.demo.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "com.hy.mdms.demo.answer_shown";
    @BindView(R.id.answerTextView)
    TextView answerTextView;
    @BindView(R.id.showAnswerBtn)
    Button showAnswerBtn;

    private boolean booleanExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        ButterKnife.bind(this);
        booleanExtra = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, true);
        setAnswerShownResult(false);
    }

    @OnClick(R.id.showAnswerBtn)
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.showAnswerBtn:
                if(booleanExtra){
                    answerTextView.setText(R.string.true_btn);
                }else{
                    answerTextView.setText(R.string.false_btn);
                }
                setAnswerShownResult(true);
        }
    }

    private void setAnswerShownResult(boolean b) {
        Intent data=new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,b);
        setResult(RESULT_OK,data);
    }
}
