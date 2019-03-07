package ru.dwdm.testapplication.presentation.view;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.MenuItem;
import android.view.View;

import ru.dwdm.testapplication.R;
import ru.dwdm.testapplication.databinding.ActivityPreviewBinding;
import ru.dwdm.testapplication.presentation.model.PreviewModel;
import ru.dwdm.testapplication.presentation.model.factory.ModelFactory;
import ru.dwdm.testapplication.presentation.view_model.PreviewViewModel;

public class PreviewActivity extends BaseActivity<ActivityPreviewBinding, PreviewViewModel> implements View.OnClickListener {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void fixHintsForMeizu() {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_preview);
    }

    @Override
    protected void initViewModel() {
        viewModel = ViewModelProviders.of(this, new ModelFactory(getApplication())).get(PreviewViewModel.class);
    }

    @Override
    protected void attachBinding() {
        ((MutableLiveData<PreviewModel>) viewModel.getLiveModel()).observe(this, model -> binding.setModel(model));
    }

    @Override
    public void onClick(View v) {

    }
}
