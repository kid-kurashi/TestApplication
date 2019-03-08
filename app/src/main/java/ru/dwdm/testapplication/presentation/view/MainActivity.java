package ru.dwdm.testapplication.presentation.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.HashMap;

import ru.dwdm.testapplication.R;
import ru.dwdm.testapplication.databinding.ActivityMainBinding;
import ru.dwdm.testapplication.domain.object.ValidationObject;
import ru.dwdm.testapplication.presentation.model.factory.ModelFactory;
import ru.dwdm.testapplication.presentation.utils.EmailTextWatcher;
import ru.dwdm.testapplication.presentation.utils.ErrorDispatcher;
import ru.dwdm.testapplication.presentation.utils.Fixes;
import ru.dwdm.testapplication.presentation.utils.IImageCallback;
import ru.dwdm.testapplication.presentation.utils.PasswordTextWatcher;
import ru.dwdm.testapplication.presentation.utils.PhoneTextWatcher;
import ru.dwdm.testapplication.presentation.utils.PickerImageUtil;
import ru.dwdm.testapplication.presentation.view_model.MainViewModel;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
        implements View.OnClickListener, IImageCallback {

    private static final int CAMERA_REQUEST_CODE = 234;
    private static final int GALLERY_REQUEST_CODE = 231;

    private AlertDialog pickerDialog;
    private PickerImageUtil pickerImageUtil;

    private HashMap<ValidationObject, TextInputLayout> layoutHashMap = new HashMap<>();
    private ErrorDispatcher errorDispatcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        errorDispatcher = new ErrorDispatcher();

        setImage(viewModel.getModel().getImagePath());

        binding.mainEmailEdit.addTextChangedListener(new EmailTextWatcher(
                binding.mainEmailLayout,
                text -> viewModel.onEmailChanged(text)));
        binding.mainPhoneEdit.addTextChangedListener(new PhoneTextWatcher(
                binding.mainPhoneLayout,
                text -> viewModel.onPhoneChanged(text)));
        binding.mainPasswordEdit.addTextChangedListener(new PasswordTextWatcher(
                binding.mainPasswordLayout,
                text -> viewModel.onPasswordChanged(text)
        ));

        initPickerDialog();
        pickerImageUtil = new PickerImageUtil(this, getString(R.string.authority));
        pickerImageUtil.setCameraRequest(CAMERA_REQUEST_CODE);
        pickerImageUtil.setGalleryRequest(GALLERY_REQUEST_CODE);
        if (!pickerImageUtil.checkPermissions())
            pickerImageUtil.requestPermissions();

        putLayoutsToMap();
    }

    private void putLayoutsToMap() {
        layoutHashMap.put(ValidationObject.EMAIL, binding.mainEmailLayout);
        layoutHashMap.put(ValidationObject.PASS, binding.mainPasswordLayout);
        layoutHashMap.put(ValidationObject.PHONE, binding.mainPhoneLayout);
    }

    @Override
    public void onImageReady(String path) {
        setImage(path);
    }

    private void setImage(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            Glide.with(this).load(imagePath).into(binding.mainPhoto);
        }
    }

    @Override
    protected void fixHintsForMeizu() {
        new Fixes().hackFixHintsForMeizu(
                binding.mainPasswordEdit,
                binding.mainPhoneEdit,
                binding.mainEmailEdit);
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
        viewModel.getLiveModel().observe(this, model -> binding.setModel(model));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_REQUEST_CODE && data != null) {
                viewModel.onImageSelected(pickerImageUtil.getRealPathFromURI(data.getData()), this);
            }
            if (requestCode == CAMERA_REQUEST_CODE) {
                viewModel.onImageSelected(pickerImageUtil.getFile(CAMERA_REQUEST_CODE, data).getPath(), this);
            }
        }
    }

    private void closePicker() {
        pickerDialog.dismiss();
    }

    private void openPicker() {
        pickerDialog.show();
    }

    private void initPickerDialog() {
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_pick_image, null);
        pickerDialog = new AlertDialog.Builder(this)
                .setView(dialogView)
                .create();
    }

    private void pickFromCamera() {
        pickerDialog.dismiss();
        try {
            pickerImageUtil.openCamera();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void pickFromGallery() {
        pickerDialog.dismiss();
        pickerImageUtil.openGallery();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_button_validate: {
                validateClick();
            }
            break;
            case R.id.main_select_photo: {
                openPicker();
            }
            break;
            case R.id.dialog_pick_image_cancel: {
                closePicker();
            }
            break;
            case R.id.dialog_pick_image_camera: {
                pickFromCamera();
            }
            break;
            case R.id.dialog_pick_image_gallery: {
                pickFromGallery();
            }
            default:
                break;
        }
    }

    private void validateClick() {
        viewModel.onButtonValidateClick(validationResult -> {
            if (validationResult) {
                startActivity(new Intent(MainActivity.this, PreviewActivity.class));
            } else {
                errorDispatcher.dispatch(viewModel.getModel(), layoutHashMap, this);
            }
        });
    }
}
