package ru.dwdm.testapplication.presentation.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;

import ru.dwdm.testapplication.R;
import ru.dwdm.testapplication.databinding.ActivityPreviewBinding;
import ru.dwdm.testapplication.presentation.model.factory.ModelFactory;
import ru.dwdm.testapplication.presentation.view_model.PreviewViewModel;

public class PreviewActivity extends BaseActivity<ActivityPreviewBinding, PreviewViewModel> implements View.OnClickListener {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewModel.getLiveModel().observe(this, previewModel -> {
            binding.setModel(previewModel);
            setImage(viewModel.getModel().getImagePath());
        });
    }

    private void setImage(String imagePath) {
        if (imagePath != null && !imagePath.isEmpty()) {
            Glide.with(this).load(imagePath).into(binding.previewPhoto);
        }
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
        viewModel.getLiveModel().observe(this, model -> binding.setModel(model));
    }

    private void sendByEmail(Intent intent) {
        try {
            startActivity(Intent.createChooser(intent, getString(R.string.send_by)));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, getString(R.string.app_not_found), Toast.LENGTH_LONG).show();
        }
    }

    private Intent getEmailIntent() {
        Intent intent = new Intent(android.content.Intent.ACTION_SEND);
        intent.setType("application/image");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.putExtra(android.content.Intent.EXTRA_EMAIL, viewModel.getEmailRecipients());
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, viewModel.getEmailSubject());
        intent.putExtra(android.content.Intent.EXTRA_TEXT, viewModel.getEmailBody());
        Uri uri = FileProvider.getUriForFile(
                PreviewActivity.this,
                getString(R.string.authority),
                new File(viewModel.getImagePath()));
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        return intent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.preview_send_button: {
                sendByEmail(getEmailIntent());
            }
            break;
            default:
                break;
        }
    }
}
