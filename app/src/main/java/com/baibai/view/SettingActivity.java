package com.baibai.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baibai.R;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class SettingActivity extends BaseActivity {
    private TextView mTvUsername, mTvNickname, mTvSex;
    private ImageView mIvUserhead;

    public static final int PHOTOINTENTREQUESTCODE = 1;
    public static final int PHOTOZOOMQUESTCODE = 2;
    private static final int PHOTORESOULTQUESTCODE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initView();
    }

    private void initView() {
        setRightBtnVisible(View.GONE);
        setLeftBtnOnclick();
        setLeftText("");
        setCenterText(R.string.title_activity_setting);

        mIvUserhead = (ImageView) findViewById(R.id.setting_iv_userhead);
        mTvUsername = (TextView) findViewById(R.id.setting_tv_username);
        mTvNickname = (TextView) findViewById(R.id.setting_tv_nickname);
        mTvSex = (TextView) findViewById(R.id.setting_tv_sex);

        mTvUsername.setOnClickListener(this);
        mTvNickname.setOnClickListener(this);
        mTvSex.setOnClickListener(this);
        mIvUserhead.setOnClickListener(this);
    }

    public void showPhtoes() {
        String[] s = {"照相", "从照册取出", "取消"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setItems(s, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        if (Environment.isExternalStorageEmulated()) {
                            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            intent.putExtra(
                                    MediaStore.EXTRA_OUTPUT,
                                    Uri.fromFile(new File(Environment
                                            .getExternalStorageDirectory(), "temp.jpg")));
                            startActivityForResult(intent,
                                    PHOTOINTENTREQUESTCODE);
                        } else {
                            Toast.makeText(SettingActivity.this, "SD卡不存在", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 1:
                        if (Environment.isExternalStorageEmulated()) {
                            Intent intent1 = new Intent(Intent.ACTION_GET_CONTENT, null);
                            intent1.setDataAndType(
                                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                                    "image/*");
                            startActivityForResult(intent1,
                                    PHOTOZOOMQUESTCODE);
                        } else {
                            Toast.makeText(SettingActivity.this, "SD卡不存在", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        break;
                }
            }
        }).create().show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setting_iv_userhead:
                showPhtoes();
                break;
            case R.id.setting_tv_username:
                startActivity(getIntent(ModifyUserInfoActivity.MODIFY_TYPE_USERNAME));
                break;
            case R.id.setting_tv_nickname:
                startActivity(getIntent(ModifyUserInfoActivity.MODIFY_TYPE_NICKNAME));
                break;
            case R.id.setting_tv_sex:
                startActivity(getIntent(ModifyUserInfoActivity.MODIFY_TYPE_SEX));
                break;
        }
    }

    public Intent getIntent(int type) {
        Intent intent = new Intent(this, ModifyUserInfoActivity.class);
        intent.putExtra(ModifyUserInfoActivity.MODIFY_TYPE, type);
        return intent;
    }

    /**
     * 裁剪图片方法实现 &nbsp;
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("return-data", true);
        startActivityForResult(intent,
                PHOTORESOULTQUESTCODE);
    }

    /**
     * 保存裁剪之后的图片数据 &nbsp;
     *
     * @param picdata
     */
    private void setPicToView(Intent picdata) {

        Bundle extras = picdata.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            /**
             * 下面注释的方法是将裁剪之后的图片以Base64Coder的字符方式上 传到服务器
             */
            //  String tp = StringUtil.ConvertByteArrayToBase64(BitmapUtil
            // .bitmap2Bytes(photo));
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            photo.compress(Bitmap.CompressFormat.PNG, 75, stream);
            // (0 -100)压缩文件
            mIvUserhead.setImageBitmap(photo);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            // 如果是直接从相册获取
            case PHOTOZOOMQUESTCODE:
                startPhotoZoom(data.getData());
                break;
            // 如果是调用相机拍照时
            case PHOTOINTENTREQUESTCODE:
                File temp = new File(Environment.getExternalStorageDirectory()
                        + "/temp.jpg");
                startPhotoZoom(Uri.fromFile(temp));
                break;
            // 取得裁剪后的图片
            case PHOTORESOULTQUESTCODE:
                /**
                 * 非空判断大家一定要验证，如果不验证的话， 在剪裁之后如果发现不满意，要重新裁剪，丢弃
                 * 当前功能时，会报NullException，只 在这个地方加下，大家可以根据不同情况在合适的 地方做判断处理类似情况 &nbsp;
                 */
                if (data != null) {
                    setPicToView(data);
                }
                break;
            case RESULT_CANCELED:
                return;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
