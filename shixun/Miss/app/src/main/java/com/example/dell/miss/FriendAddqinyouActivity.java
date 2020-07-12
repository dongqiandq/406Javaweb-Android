package com.example.dell.miss;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import static com.google.gson.internal.bind.util.ISO8601Utils.format;

public class FriendAddqinyouActivity extends AppCompatActivity {
    private ImageView imgReturn;
    private Bitmap returnBp1;
    private static final int CHOOSE_PICTURE = 2;
    private static final int CROP_1_REQUEST = 3;
    private static final int CROP_2_REQUEST = 4;

    private static final int CAMERA_REQUEST = 1;
    private File file;
    private String path, saveName;
    // 照片保存路径
    private File cameraFile;
    private Uri photoUri;
    private Uri returnUri;
    private byte[] returnBitmap;

    private RelativeLayout rlHead;
    private ImageView imgTou;

    private RelativeLayout rlName;
    private TextView tvName;

    private RelativeLayout rlSex;
    private TextView tvSex;

    private RelativeLayout rlAddress;
    private TextView tvAddress;

    private String nick;
    private int which0;
    private int sex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friend_addfriend);

        TextView back = findViewById(R.id.tv_addqinyouback);

        imgTou = findViewById(R.id.iv_headphoto);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgTou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            openDialog();

            }
        });
//        EditText mudi = findViewById(R.id.et_qinyouMudi);


        Button ok = findViewById(R.id.btn_addqinyou_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText name1 = findViewById(R.id.et_qinyouName);
//                Log.e("qinyou",name1.getText().toString()+"aaaaaaaaaaaaaaa");
                EditText time1 = findViewById(R.id.et_qinyouTime);
                EditText position1 = findViewById(R.id.et_qinyouProsition);
                EditText description = findViewById(R.id.et_qinyouDescription);
                EditText shengping = findViewById(R.id.et_qinyouShengping);
                //获得到用户在添加页面输入的信息。将信息存入数据库，更新列表
                FriendQinyouMessage message = new FriendQinyouMessage();
                message.img = R.drawable.f3;
                message.description = description.getText().toString();
                message.shangping = shengping.getText().toString();
                message.name = name1.getText().toString();
                message.possition = position1.getText().toString();
                message.time = time1.getText().toString();
                message.lookNum=0;
                Log.e("yuanlai",message.toString());
                final Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("message",message);
                intent.putExtra("add",bundle);

                setResult(11,intent);
                finish();
            }
        });

    }
    private void openDialog() {
        final MineHeadDialog headDialog = new MineHeadDialog(this);
        headDialog.setCancelable(false);
        Window win=headDialog.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        win.setAttributes(lp);

        headDialog.show();
        headDialog.setClicklistener(new MineHeadDialog.ClickListenerInterface() {
            @Override
            public void doGetCamera() {
                // 相机
                headDialog.dismiss();
                camera();
            }

            @Override
            public void doGetPic() {
                // 图库
                headDialog.dismiss();
                // Toast.makeText(getApplicationContext(), "tuku", Toast.LENGTH_SHORT).show();
                crop(); // 打开图库并剪切
//                 gallery(); // 只是打开图库选一张照片
            }

            @Override
            public void doCancel() {
                // 取消
                headDialog.dismiss();
            }
        });
    }

    private void camera() {
        Intent cameraintent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        String filename = format(new Date());
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, filename);
        photoUri =getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
//        cameraFile =generatePhotoFile(this);//DiskUtils
        cameraintent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri );//Uri.fromFile(cameraFile)

        String camera=photoUri.getPath();
        cameraFile=new File(camera);
        this.startActivityForResult(cameraintent, CAMERA_REQUEST);
    }

    // 图库——剪裁
    private void crop() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,CROP_1_REQUEST);
    }

//    // 图库
//    private void gallery() {
//        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////        intent.setType("image/*");
//        startActivityForResult(intent,CHOOSE_PICTURE);
//    }
public void saveBitmap(Bitmap bm){
    try {
        FileOutputStream fos=new FileOutputStream(getFilesDir()+"/b.png");
        bm.compress(Bitmap.CompressFormat.PNG,80,fos);
        fos.flush();
        fos.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        this.returnUri=data.getData();
        switch (requestCode) {

            // 图库返回值
            case CHOOSE_PICTURE:
                if (data==null){
                    //实现时改为从服务器端获取图片
//                    imgTou.setImageResource(R.drawable.photo);
                }else{
                    Uri uri = data.getData();
                    this.returnUri=uri;
                    //显示图片
                    path = getRealPathFromURI(uri);
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    // 设置进头像
                    this.returnBitmap=Bitmap2Bytes(bitmap);
                    this.returnBp1=bitmap;
                    saveBitmap(returnBp1);
                    imgTou.setImageBitmap(bitmap);
                    file = new File(path);
                }
                break;

            // 图库剪切
            case CROP_1_REQUEST:
                if (data!=null){
                    Uri uri_1 = data.getData();
                    this.returnUri=uri_1;
                    startPhotoZoom(uri_1);
                }
                break;
            case CROP_2_REQUEST:
//                this.returnUri=data.getData();
                Bitmap bitmap1 = data.getExtras().getParcelable("data");
                this.returnBitmap=Bitmap2Bytes(bitmap1);
                this.returnBp1=bitmap1;
                Log.e("returnBitmap的值",returnBitmap+"");
                /*//上传图片到服务器
                UpdateImageAsync updateImageAsync=new UpdateImageAsync();
                updateImageAsync.execute(Constant.URL+"user/header",LoginUser.getId(),LoginUser.getHeader(),bitmap1);*/
                saveBitmap(returnBp1);
                imgTou.setImageBitmap(bitmap1);
                break;

            // 相机
            case CAMERA_REQUEST:
                this.returnUri=photoUri;
                try {
                    Bitmap bitmap3 = BitmapFactory.decodeStream(getContentResolver()
                            .openInputStream(photoUri));
                    this.returnBitmap=Bitmap2Bytes(bitmap3);
                    this.returnBp1=bitmap3;
                    saveBitmap(returnBp1);
                    imgTou.setImageBitmap(bitmap3);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // 图片剪切
    private void startPhotoZoom(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop为true是设置在开启的intent中设置显示的view可以剪裁
        intent.putExtra("crop", "true");

        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        // outputX,outputY 是剪裁图片的宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);

        this.startActivityForResult(intent, CROP_2_REQUEST);
    }


    public String getRealPathFromURI(Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public byte[] Bitmap2Bytes(Bitmap bm){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG,100,baos);
        return baos.toByteArray();
    }
}

