package why.com.rxjavademo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewResId());
        Log.d("HanYu","wang");
        Log.d("HanYu","wang wang wang");
        mUnbinder = ButterKnife.bind(this);
        init(savedInstanceState);
    }

    public abstract int getContentViewResId();

    public abstract void init(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();

    }

    public void startActivity(Activity currentActivity, Class<?> targetActivity) {
        Intent intent = new Intent();
        intent.setClass(currentActivity, targetActivity);
        startActivity(intent);
    }

}
