package com.wang.neteasemusic.ui.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by HP on 2017/6/3.  解决Fragment预加载问题
 */

public abstract class LoadFragment extends Fragment {

    /**
     * 视图是否初始化
     **/
    private boolean isInitView = false;
    /**
     * 是否加载数据
     **/
    private boolean isLoadData = false;



    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(setContentView(), container, false);
        isInitView = true;
        isCanLoadData();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(!isLoadData){
            isCanLoadData();
        }

    }

    private void isCanLoadData() {

        if (!isInitView) {
            return;
        }
        if (getUserVisibleHint()) {
            isLoadData = true;
            lazyLoad();
        } else {
            if (isLoadData) {
                stopLoad();
            }
        }

    }

    /**
     * 停止加载
     */
    protected void stopLoad() {

    }

    /**
     * 进行懒加载
     */
    protected abstract void lazyLoad();

    /**
     * 初始化视图
     *
     * @return
     */
    protected abstract int setContentView();

    /**
     * 子类传递过来的View
     *
     * @return
     */
    protected View getContentView() {
        return view;
    }

    /**
     * 绑定视图Id
     *
     * @param id
     * @param <T>
     * @return
     */
    protected <T extends View> T findViewById(int id) {

        return (T) getContentView().findViewById(id);
    }

    /**
     * 视图销毁时，初始化为false
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInitView=false;
        isLoadData=false;
    }
}
