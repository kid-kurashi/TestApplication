package ru.dwdm.testapplication.presentation.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import ru.dwdm.testapplication.R;

public class PreviewActivity extends BaseActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_preview);
    }

    @Override
    protected void initViewModel() {

    }

    @Override
    protected void attachBinding() {

    }
}
