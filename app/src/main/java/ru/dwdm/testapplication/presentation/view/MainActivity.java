package ru.dwdm.testapplication.presentation.view;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ru.dwdm.testapplication.R;
import ru.dwdm.testapplication.databinding.ActivityMainBinding;
import ru.dwdm.testapplication.presentation.model.MainModel;
import ru.dwdm.testapplication.presentation.model.factory.ModelFactory;
import ru.dwdm.testapplication.presentation.view_model.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements View.OnClickListener {

    private static final int CAMERA_REQUEST_CODE = 234;
    private static final int GALLERY_REQUEST_CODE = 231;

    private AlertDialog pickerDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initPicker();

        binding.mainSelectPhoto.setOnClickListener(this);
        binding.mainButtonValidate.setOnClickListener(this);
    }

    @Override
    protected void initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void initViewModel() {
        viewModel = ViewModelProviders.of(this, new ModelFactory(getApplication())).get(MainViewModel.class);
    }

    @Override
    protected void attachBinding() {
        ((MutableLiveData<MainModel>) viewModel.getModel()).observe(this, model -> binding.setModel(model));
    }

    private void closePicker() {
        pickerDialog.dismiss();
    }

    private void openPicker() {
        pickerDialog.show();
    }

    private void initPicker() {

        View dialogView = getLayoutInflater().inflate(R.layout.dialog_pick_image, null);

        pickerDialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();

    }

    private void pickFromCamera() {

    }

    private void pickFromGallery() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_button_validate: {
                viewModel.buttonValidateClick();
            }
            break;
            case R.id.main_select_photo: {

            }
            break;
            default:
                break;
        }
    }
}
