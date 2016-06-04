package com.sample.RxJava;

import com.library.Activity.Fragment.BaseBackStackFragment;
import com.sample.MainActivity;
import com.sample.R;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Notification;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.schedulers.TimeInterval;


/**
 * Created by xiaoye on 2016/4/24.
 */
public class RxJavaTestFragment extends BaseBackStackFragment<MainActivity> implements IRxJavaTest {
    @Override
    protected int getContentView() {
        return R.layout.rxjava_test_layout;
    }

    @Override
    protected void init() {
        super.init();

        ButterKnife.bind(this, getView());
    }

    @OnClick(R.id.rxjava_create)
    @Override
    public void create() {
        final String[] names = new String[]{"小人", "大SB", "那个谁", "叶恒信", "叶亨心", "叶亨鑫"};
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0, size = names.length; size > i; i++) {
                    subscriber.onNext(names[i]);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("打印完成   onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    @OnClick(R.id.rxjava_filter)
    @Override
    public void filter() {
        //过滤条件
        final String[] filterParams = new String[]{"小人", "大SB"};
        Observable.<String>just("小人", "大SB", "那个谁", "叶恒信", "叶亨心", "叶亨鑫").filter(new Func1<String, Boolean>() {
            /** 返回true 表示这个值会被保留 */
            @Override
            public Boolean call(String s) {
                return !(filterParams[0].equals(s) || filterParams[1].equals(s));
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("打印完成   onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }


    @OnClick(R.id.rxjava_flatMap)
    @Override
    public void flatMap() {
        final String[] names = new String[]{"小人", "大SB", "那个谁", "叶恒信", "叶亨心", "叶亨鑫"};
        Observable.from(names).flatMap(new Func1<String, Observable<String>>() {
            @Override
            public Observable<String> call(String s) {
                return Observable.just("我叫  " + s);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                System.out.println("打印完成   onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        });
    }

    @OnClick(R.id.rxjava_map)
    @Override
    public void map() {
        final String[] ints = new String[]{"1993", "4", "25", "叶恒信", "叶亨心", "叶亨鑫"};
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                System.out.println(Thread.currentThread().getName() + "  create");
                for (int i = 0, size = ints.length; size > i; i++) {
                    subscriber.onNext(ints[i]);
                }
                subscriber.onCompleted();
            }
        }).filter(new Func1<String, Boolean>() {
            /** 返回true 表示这个值会被保留 */
            @Override
            public Boolean call(String s) {
                int value = 0;
                try {
                    value = Integer.valueOf(s);
                } catch (NumberFormatException e) {
                    System.out.println(s + "  不能装换为数字");
                }
                System.out.println(Thread.currentThread().getName() + "  filter");
                return value != 0;
            }
        }).map(new Func1<String, Integer>() {
            //在这里可以做类型转换
            @Override
            public Integer call(String s) {
                System.out.println(Thread.currentThread().getName() + "  map");
                return Integer.valueOf(s);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("打印完成   onCompleted");
                System.out.println(Thread.currentThread().getName() + "  subscribe");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer s) {
                System.out.println(s + " 我可是数字哦");
            }
        });
    }

    /**
     * 目前不明白什么意思
     * 有点像代理的意思
     */
    @OnClick(R.id.rxjava_lift)
    @Override
    public void lift() {
        final String[] numbers = new String[]{"1993", "4", "25", "错误"};
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                for (int i = 0, size = numbers.length; size > i; i++) {
                    subscriber.onNext(numbers[i]);
                }
                subscriber.onCompleted();
            }
        }).lift(new Observable.Operator<Integer, String>() {
            @Override
            public Subscriber<? super String> call(final Subscriber<? super Integer> subscriber) {
                return new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("lift onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                        System.out.println("lift  发生错误了");
                    }

                    @Override
                    public void onNext(String s) {
                        subscriber.onNext(Integer.valueOf(s));
                    }
                };
            }
        }).subscribe(new Subscriber<Integer>() {
            /** 这个不会执行 执行lift里面的onCompleted方法 */
            @Override
            public void onCompleted() {
                System.out.println("Subscriber onCompleted");
            }

            /** 这个不会执行 onError */
            @Override
            public void onError(Throwable e) {
                System.out.println("Subscriber  发生错误了");
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println(integer + 1 + " 我变大了");
            }
        });
    }

    /**
     * 只会对当前任务延迟
     */
    @OnClick(R.id.rxjava_delay)
    @Override
    public void delay() {

        printTime();
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {

                for (int i = 0; 3 > i; i++) {
                    subscriber.onNext(i); //这个将会在同一时间执行
                }
            }
        }).delay(2000, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer aLong) {
                printTime();
                System.out.print(aLong);
            }
        });

    }

    @OnClick(R.id.rxjava_do)
    @Override
    public void Do() {
        Observable.just(1, 2, 3)
                .doOnEach(new Action1<Notification<? super Integer>>() {
                    @Override
                    public void call(Notification<? super Integer> notification) {
                        log("doOnEach send " + notification.getValue() + " type:" + notification.getKind());
                    }
                })
                .doOnNext(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        log("doOnNext send " + integer);
                    }
                })
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        log("on subscribe");
                    }
                })
                .doOnUnsubscribe(new Action0() {
                    @Override
                    public void call() {
                        log("on unsubscribe\n");
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        log("onCompleted");
                    }
                }).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer i) {
                log("do:" + i);
            }
        });
    }

    @OnClick(R.id.rxjava_timeout)
    @Override
    public void timeout() {

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i <= 4; i++) {
                    try {
                        Thread.sleep(i * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).timeout(200, TimeUnit.MILLISECONDS, Observable.just(5, 6)/** 这个参数如果没有的话 会执行subscriber onError方法 如果有则不会onError 会打印5,6 */).subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {
                log("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                log(e.toString());
            }

            @Override
            public void onNext(Integer integer) {
                log(integer + "");
            }
        });

    }

    @OnClick(R.id.rxjava_timeInterval)
    @Override
    public void timeInterval() {
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i <= 3; i++) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread()).timeInterval().subscribe(new Action1<TimeInterval<Integer>>() {
            @Override
            public void call(TimeInterval<Integer> i) {
                log("timeInterval:" + i.getValue() + "-" + i.getIntervalInMilliseconds());
            }
        });
    }

    @OnClick(R.id.rxjava_skip)
    @Override
    public void skip() {
        Observable.just(1, 2, 3, 4, 5, 6).skip(3).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                log(integer + "");
            }
        });
    }

    @OnClick(R.id.rxjava_take)
    @Override
    public void take() {
        Observable.just(1, 2, 3, 4, 5, 6).take(3).subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                log(integer + "");
            }
        });
    }

    void printTime() {
        System.out.println("当前时间  " + System  .currentTimeMillis());
    }

    void log(String str) {
        System.out.println(str);
    }
}
