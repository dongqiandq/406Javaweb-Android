package com.example.dell.miss;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.Date;

import static com.google.gson.internal.bind.util.ISO8601Utils.format;
import static com.example.dell.miss.ConcreteLoginActivity.LoginUser;

public class PersonEditActivity extends AppCompatActivity {

    private ImageView imgReturn;
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
    private Bitmap returnBp;

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
        setContentView(R.layout.person_edit);

        findViews();
        initDate();

        imgReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                setResult(RESULT_OK,intent);
                PersonEditActivity.this.finish();
            }
        });

//        LoginUser=util.getUserInfo();

//        if(LoginUser.getPhoneNumber().length()>0){
//            if(LoginUser.getUserImage().contains("/img/header.png")){
//                RequestOptions requestOptions = new RequestOptions().circleCrop();
//                Glide.with(this).load(Constant.URL+"img/header.png")
//                        .apply(requestOptions).into(imghead);
//            }else{
//                RequestOptions requestOptions = new RequestOptions().circleCrop();
//                Glide.with(this).load(LoginUser.getUserImage()).apply(requestOptions).into(imghead);
//            }
//        }
        MyListener myListener=new MyListener();
        rlHead.setOnClickListener(myListener);
        rlName.setOnClickListener(myListener);
        rlSex.setOnClickListener(myListener);
        rlAddress.setOnClickListener(myListener);
    }

    private void findViews(){
        imgReturn= findViewById(R.id.img_return);

        rlHead = findViewById(R.id.rl_head);
        imgTou = findViewById(R.id.img_tou);
        Bitmap bitmap=BitmapFactory.decodeFile("data/user/0/com.example.dell.miss/files/a.png");
        if (null!=bitmap){
            imgTou.setImageBitmap(bitmap);
        }else {
            Util.getDBImage(PersonEditActivity.this,Constant.URL+"images/"+LoginUser.getHeader(),imgTou);
        }
        rlName = findViewById(R.id.rl_name);
        tvName=findViewById(R.id.tv_name);

        rlSex = findViewById(R.id.rl_sex);
        tvSex = findViewById(R.id.tv_sex);

        rlAddress = findViewById(R.id.rl_address);
        tvAddress = findViewById(R.id.tv_address);
    }

    public void initDate(){
        tvName.setText(LoginUser.getName());
        if (LoginUser.getSex()==0){
            tvSex.setText("女");
        }else {
            tvSex.setText("男");
        }
        tvAddress.setText(LoginUser.getArea());
    }

    public void saveBitmap(Bitmap bm){
        try {
            FileOutputStream fos=new FileOutputStream(getFilesDir()+"/a.png");
            bm.compress(Bitmap.CompressFormat.PNG,80,fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class MyListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.rl_head:
                    openDialog();
                    break;
                case R.id.rl_name:
                    Intent intent1 = new Intent(PersonEditActivity.this,MineNameActivity.class);
                    startActivity(intent1);
                    finish();
                    break;
                case R.id.rl_sex:
                    showChooseSexDialog();
                    break;
                case R.id.rl_address:
                    Intent intent3 = new Intent(PersonEditActivity.this,MineAddressActivity.class);
                    startActivity(intent3);
                    finish();
                    break;
            }
        }
    }

    //显示修改性别的对话框
    protected void showChooseSexDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog;
        builder.setTitle("修改性别");
        final String[] items = {"男","女"};
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                which0=which;
                Toast.makeText(PersonEditActivity.this,items[which],Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                Log.e("which值为",which+"");
                if (items[which0].equals("男")){
                    sex=1;
                }
                UpdateSexAsync updateSexAsync=new UpdateSexAsync();
                updateSexAsync.execute(Constant.URL+"user/sex",LoginUser.getId(),sex);

                tvSex.setText(items[which0]);
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
    }

    /**
     * 修改用户性别
     */
    public class UpdateSexAsync extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpURLConnection connection= Util.getURLConnection((String)objects[0]);
                OutputStream os=connection.getOutputStream();
                String message=objects[1]+","+objects[2];
                os.write(message.getBytes());

                InputStream is=connection.getInputStream();
                String content=Util.readInputStreamToString(is);
                Util.closeIO(is,os);
                return content;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Object o) {
            if (null==o){
                Toast.makeText(PersonEditActivity.this,"网络连接不可用，请稍后再试",Toast.LENGTH_SHORT).show();
            }else if("".equals(o.toString())){
                Toast.makeText(PersonEditActivity.this,"修改失败，请稍后再试",Toast.LENGTH_SHORT).show();
            }else {
                LoginUser.setSex(sex);
            }
        }
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
                    this.returnBp=bitmap;
                    saveBitmap(returnBp);
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
                this.returnBp=bitmap1;
                Log.e("returnBitmap的值",returnBitmap+"");
                /*//上传图片到服务器
                UpdateImageAsync updateImageAsync=new UpdateImageAsync();
                updateImageAsync.execute(Constant.URL+"user/header",LoginUser.getId(),LoginUser.getHeader(),bitmap1);*/
                saveBitmap(returnBp);
                imgTou.setImageBitmap(bitmap1);
                break;

            // 相机
            case CAMERA_REQUEST:
                this.returnUri=photoUri;
                try {
                    Bitmap bitmap3 = BitmapFactory.decodeStream(getContentResolver()
                            .openInputStream(photoUri));
                    this.returnBitmap=Bitmap2Bytes(bitmap3);
                    this.returnBp=bitmap3;
                    saveBitmap(returnBp);
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

    /**
     * 将图片传至服务器端
     */
    public class UpdateImageAsync extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                HttpURLConnection connection = Util.getURLConnection((String) objects[0]);
                OutputStream os = connection.getOutputStream();
                JSONObject object = new JSONObject();
                object.put("userId", objects[1]);
                object.put("image", Bitmap2Bytes((Bitmap) objects[2]));
                os.write(object.toString().getBytes());
                InputStream is = connection.getInputStream();
                String content = Util.readInputStreamToString(is);
                Util.closeIO(is, os);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
