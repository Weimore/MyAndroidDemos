package com.example.mymodeldemos.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mymodeldemos.utils.LogUtils;

/**
 *对于懒加载，无论是FragmentPagerAdapter还是FragmentStatePagerAdapter，只要不启用懒加载，都会自动缓存周围的Fragment，即loadView（）也loadData（）
 * 如果开启懒加载，则只会缓存周围Fragment的View，只要当Fragment显示的时候，才会loadData（）
 *
 * 对于FragmentPagerAdapter，如果想要使用懒加载，则直接setLazyLoadEnable（）开启懒加载即可
 *
 * 对于FagmentStataPagerAdapter，如果不启用懒加载，则会缓存周围的Fragment的view和data，且在被销毁的Fragment重新被创建时会重新载入数据
 * 如果开启了懒加载，且你通过saveInstanceState（）方法确实存储了数据，则Fragment再次创建时会复用你存储的数据，不会去加载数据
 * 如果你并未存储数据，则Fragment创建后会重新去加载数据
 */
//默认不启用懒加载，可通过 setLazyLoadEnable（）开启懒加载
public abstract class BaseFragment extends Fragment {

    protected Context context;   //Fragment的宿主Activity

    private boolean isVisible;   //Fragment是否可见
    private boolean isViewCreated;   //Fragment是否已加载布局
    private boolean isDataInvalidated;   //Fragment是否已加载过数据

    private boolean isLazyLoadEnable = false;   //默认不启用懒加载

    public static final String ARGUMENT = "argument";


    //当不能自动调用setUserVisibleHint（）方法时，手动来改变visible
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    //是否启用懒加载
    public void setLazyLoadEnable() {
        isLazyLoadEnable = true;
    }

    //判断此时是否要加载数据
    private void checkIfLoadData(Bundle saveInstanceState) {
        if(isViewCreated && isVisible && !isDataInvalidated){   //如果Fragment可见，且加载了布局，且还未加载数据，则可以加载数据
            setUpData(saveInstanceState);
            isDataInvalidated = true;    //数据已加载
        }
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        checkIfLoadData(null);
        LogUtils.d("setUserVisibleHint..........................." + isVisibleToUser + "......" + getClass().getSimpleName() + "...." + getArguments());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getContext();
        LogUtils.d("onCreated..........................." + getClass().getSimpleName() + "...." + getArguments());
    }

    //初始化Fragment的布局，加载布局和findViewById的操作通常在此函数内完成，但是不建议执行耗时的操作，
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LogUtils.d("onCreateView..........................." + getClass().getSimpleName() + "...." + getArguments());
        return setUpContentView(inflater, container, savedInstanceState);
    }


    //布局创建完成后立刻调用，可以获得onCreateView返回的布局view，子类可以在该方法中findViewById，保证布局已被加载完成
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;        //基本布局已加载完成
        setUpView(view, savedInstanceState);
        LogUtils.d("onViewCreated..........................." + getClass().getSimpleName() + "...." + getArguments());
    }


    //与Fragment绑定的Activity的onCreate方法已经执行完成并返回，在该方法内可以进行与Activity交互的UI操作
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LogUtils.d("onActivityCreated..........................." + getClass().getSimpleName() + "...." + getArguments());
        if (!isLazyLoadEnable) {
            setUpData(savedInstanceState);
        } else {   //如果使用了懒加载
            if (savedInstanceState != null) {               //如果你有想要恢复的数据，可重写onSaveInstanceState（）方法将数据存入Bundle中
                onRestoreInstanceState(savedInstanceState);  // 子类通过重些onRestoreInstanceState(）方法，在该方法中获得之前存储的数据
            }
            if (isDataInvalidated) {          //如果数据已被加载过，直接setData
                setUpData(savedInstanceState);
            } else {                        //如果数据未被加载过，判断当前是否需要加载
                checkIfLoadData(savedInstanceState);
            }
        }

    }


    //子类实现该方法，加载布局
    protected abstract View setUpContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    //子类可在该方法中findViewById
    protected void setUpView(View view, Bundle saveInstanceState) {
    }

    //子类可在该方法中加载数据
    protected void setUpData(Bundle saveInstanceState) {
    }


    //当fragment被重新创建后，且Fragment上的view中的onRestoreInstanceState（）调用之后，即view保存的数据都被恢复之后被调用
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        LogUtils.d("onViewStateRestored..........................." + getClass().getSimpleName() + "...." + getArguments());
    }

    //执行该方法时，Fragment由不可见变为可见状态
    @Override
    public void onStart() {
        super.onStart();
        LogUtils.d("onStart..........................." + getClass().getSimpleName() + "...." + getArguments());
    }


    //执行该方法时，Fragment处于活动状态，用户可与之交互。
    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d("onResume..........................." + getClass().getSimpleName() + "...." + getArguments());
    }

    //执行该方法时，Fragment处于暂停状态，但依然可见，用户不能与之交互
    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d("onPause..........................." + getClass().getSimpleName() + "...." + getArguments());
    }

    //保存当前Fragment的状态。该方法会在onPause()方法执行后和onDestrory（）方法执行前被调用，它会自动保存Fragment的状态
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.d("onSaveInstanceState..........................." + getClass().getSimpleName() + "...." + getArguments());
    }

    //自己创建一个方法，用于恢复数据，它在onActivityCreated（）中被调用
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        isDataInvalidated = true;
        LogUtils.d("onRestoreInstanceState..........................." + getClass().getSimpleName() + "...." + getArguments());
    }

//    //可以通过setRetainInstance(boolean)来保存自定义的对象数据,
//    // 调用了setRetainInstance(true)后 Fragment 会只在 onDestroyView()和onCreateView()中执行，使得成员变量数值被保留 ?are you sure?
//    @Override
//    public void setRetainInstance(boolean retain) {
//        super.setRetainInstance(retain);
//    }

    //执行该方法时，Fragment完全不可见
    @Override
    public void onStop() {
        super.onStop();
        LogUtils.d("onStop..........................." + getClass().getSimpleName() + "...." + getArguments());
    }

    //执行该方法时，Fragment的视图被销毁，但Fragment未被销毁
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isViewCreated = false;
        LogUtils.d("onDestroyView..........................." + getClass().getSimpleName() + "...." + getArguments());
    }

    //销毁Fragment 。通常按Back键退出或者Fragment被回收时调用此方法
    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.d("onDestroy..........................." + getClass().getSimpleName() + "...." + getArguments());
    }

    //解除与Activity的绑定。在onDestroy方法之后调用
    @Override
    public void onDetach() {
        super.onDetach();
        context = null;
        LogUtils.d("onDetach..........................." + getClass().getSimpleName() + "...." + getArguments());
    }

}
