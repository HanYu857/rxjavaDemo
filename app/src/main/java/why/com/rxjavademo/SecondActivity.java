package why.com.rxjavademo;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

public class SecondActivity extends BaseActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.img)
    ImageView img;


    @Override
    public int getContentViewResId() {
        return R.layout.activity_second;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        Glide.with(SecondActivity.this)
                .load("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png")
                .placeholder(R.mipmap.ic_launcher)
                .into(img);
    }


    @OnClick(R.id.btn1)
    public void asyncSubjectClick() {
        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        asyncSubject.onNext("Hello JiNan");
        asyncSubject.onNext("Hello China");
        asyncSubject.onNext("Hello World");
        asyncSubject.onCompleted();   //当asyncSubject不调用onCompleted时observer不接受任何的信息

        asyncSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Toast.makeText(SecondActivity.this, "结束", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(SecondActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(String s) {
                Toast.makeText(SecondActivity.this, "s = " + s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.btn2)
    public void behaviorSubjectClick() {
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create("-1");
        behaviorSubject.onNext("Hello World !");
        behaviorSubject.onNext("Hello World ! 2");
        behaviorSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Toast.makeText(SecondActivity.this, "结束", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(SecondActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(String s) {
                Toast.makeText(SecondActivity.this, "s = " + s, Toast.LENGTH_SHORT).show();
            }
        });
        behaviorSubject.onNext("Hello Android");
    }

    @OnClick(R.id.btn3)
    public void publishSubject() {
        PublishSubject<String> publishSubject = PublishSubject.create();
        publishSubject.onNext("Hello World");

        publishSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Toast.makeText(SecondActivity.this, "结束", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(SecondActivity.this, "失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNext(String s) {
                Toast.makeText(SecondActivity.this, "s = " + s, Toast.LENGTH_SHORT).show();
            }
        });

        publishSubject.onNext("Hello World 3");
    }

    @OnClick(R.id.btn4)
    public void operatorOption() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                //发送数据的地方
                subscriber.onNext("你好啊");
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())  //表示发送数据的地方在哪个线程
                .observeOn(AndroidSchedulers.mainThread())  //表示接受数据的地方发生在哪个线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(SecondActivity.this, "结束了", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        //用来更新Ui
                        textView.setText(s);
                    }
                });
    }

    @OnClick(R.id.btn5)
    public void mapOperator() {
//        Observable.just("123456789").map(new Func1<String, String>() {
//            @Override
//            public String call(String s) {
//                return s.substring(0, 4);
//            }
//        }).subscribe(new Action1<String>() {
//            @Override
//            public void call(String str) {
//                //获取到bitmap 并显示
//                Toast.makeText(SecondActivity.this, str, Toast.LENGTH_SHORT).show();
//            }
//        });
//        List<School> list = new ArrayList<>();
//        Observable.from(list).flatMap(new Func1<School, Observable<School.Student>>() {
//            @Override
//            public Observable<School.Student> call(School school) {
//                return Observable.from(school.getStudentList());
//            }
//        }).subscribe(new Action1<School.Student>() {
//            @Override
//            public void call(School.Student student) {
//
//            }
//        });

        Observable.just(1, 2, 3).buffer(3).subscribe(new Action1<List<Integer>>() {
            @Override
            public void call(List<Integer> integers) {
                Log.d("TAGSecondActivity", integers.toString());
            }
        });
    }

    @OnClick(R.id.btn6)
    public void jumpToBottomActivity() {
        startActivity(this, BottomActivity.class);
    }
}
