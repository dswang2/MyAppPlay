package com.dbstar.myappplay.common.rx;

import com.dbstar.myappplay.bean.BaseBean;
import com.dbstar.myappplay.common.exception.ApiException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by dswang on 2017/9/6.
 */

public class RxHttpReponseCompat {
    public static <T> Observable.Transformer<BaseBean<T>, T> compatResult() {
        return new Observable.Transformer<BaseBean<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseBean<T>> baseBeanObservable) {
                return baseBeanObservable.flatMap(new Func1<BaseBean<T>, Observable<T>>() {
                      @Override
                      public Observable<T> call(final BaseBean<T> tBaseBean) {
                          //处理tBaseBean ，如果上游数据正常，创建Observable并返回，否则抛出异常
                          if(tBaseBean.success()){
                              return Observable.create(new Observable.OnSubscribe<T>() {
                                  @Override
                                  public void call(Subscriber<? super T> subscriber) {
                                      try {
                                          subscriber.onNext(tBaseBean.getData());
                                          subscriber.onCompleted();
                                      }catch (Exception e){
                                          subscriber.onError(e);
                                      }
                                  }
                              });
                          }else {
                              return Observable.error(new ApiException(tBaseBean.getStatus(),tBaseBean.getMessage()));
                          }
                      }
                  }

                ).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}