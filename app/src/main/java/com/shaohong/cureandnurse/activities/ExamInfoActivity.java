package com.shaohong.cureandnurse.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.shaohong.cureandnurse.R;
import com.shaohong.cureandnurse.bean.Exam;
import com.shaohong.cureandnurse.modules.exam.ExamAppliedFragment;
import com.shaohong.cureandnurse.modules.exam.ExamApplyFragment;
import com.shaohong.cureandnurse.modules.exam.ExamInvigilateFragment;

import butterknife.ButterKnife;

public class ExamInfoActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exam_info_activity);
        ButterKnife.bind(this);
        fragmentManager=getSupportFragmentManager();

        Exam exam= getIntent().getParcelableExtra("EXAM_INFO");
        initView(exam);
    }

    private void initView(Exam exam){
        switch (exam.getStatus()){
            case "已报名": {
                ExamAppliedFragment to = new ExamAppliedFragment();
                to.exam = exam;
                fragmentManager.beginTransaction()
                        .add(R.id.container_info_exam, to)
                        .commit();
                break;
            }
            case "可报名": {
                ExamApplyFragment to = new ExamApplyFragment();
                to.exam = exam;
                fragmentManager.beginTransaction()
                        .add(R.id.container_info_exam, to)
                        .commit();
                break;
            }
            case "监考": {
                ExamInvigilateFragment to = new ExamInvigilateFragment();
                to.exam = exam;
                fragmentManager.beginTransaction()
                        .add(R.id.container_info_exam, to)
                        .commit();
                break;
            }
        }
    }
}
