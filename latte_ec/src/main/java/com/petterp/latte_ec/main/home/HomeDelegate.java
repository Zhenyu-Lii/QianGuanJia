package com.petterp.latte_ec.main.home;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.petterp.latte_core.mvp.factory.CreatePresenter;
import com.petterp.latte_core.mvp.base.BaseFragment;
import com.petterp.latte_core.util.storage.LatterPreference;
import com.petterp.latte_core.util.time.TimeUtils;
import com.petterp.latte_ec.R;
import com.petterp.latte_ec.R2;
import com.petterp.latte_ec.main.home.draw.DrawAdapter;
import com.petterp.latte_ec.main.home.draw.DrawFields;
import com.petterp.latte_ec.main.home.draw.DrawItemClickListener;
import com.petterp.latte_ec.main.login.UserDelegate;
import com.petterp.latte_ui.recyclear.MultipleFidls;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 首页delegate
 *
 * @author luoluo
 */
@GlideModule
@CreatePresenter(HomePresenter.class)
public class HomeDelegate extends BaseFragment<HomePresenter> implements IHomeView, IHomeDrListener, IHomeRvListener {


    @BindView(R2.id.rv_index_list)
    RecyclerView recyclerView = null;
    @BindView(R2.id.tv_index_tob_surplus)
    AppCompatTextView tvSurplus = null;
    @BindView(R2.id.tv_index_tob_consume)
    AppCompatTextView tvConsume = null;
    @BindView(R2.id.tv_index_tob_income)
    AppCompatTextView tvIncome = null;
    @BindView(R2.id.cord_layout)
    CoordinatorLayout right = null;
    @BindView(R2.id.index_bar)
    Toolbar toolbar = null;


    //控制层
    private HomePresenter mPresenter;
    private HomeAdapter homeAdapter;

    @Override
    public Object setLayout() {
        return R.layout.delegate_home;
    }


    @SuppressLint("CheckResult")
    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        getActivity().getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //建立连接
        mPresenter = getPresenter();
    }


    @Override
    public void setTitleinfo(HashMap<IHomeRvFields, String> map) {
        tvConsume.setText(map.get(IHomeRvFields.CONSUME));
        CharSequence temp="- "+tvConsume.getText().subSequence(3,tvConsume.getText().length());
        tvConsume.setText(temp);
        tvIncome.setText(map.get(IHomeRvFields.INCOME));
        temp="+ "+tvIncome.getText().subSequence(3,tvIncome.getText().length());
        tvIncome.setText(temp);
        tvSurplus.setText(map.get(IHomeRvFields.SUR_PLUS));
    }



    @Override
    public void showRv(List<MultipleItemEntity> list) {
        homeAdapter = new HomeAdapter(list);
        View view = View.inflate(getContext(), R.layout.arrow_rv_foot_view, null);
        //添加尾部
        homeAdapter.addFooterView(view);
        recyclerView.setAdapter(homeAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //设置Rv分割线
        RecyclerViewDivider.with(Objects.requireNonNull(getContext()))
                .color(Color.parseColor("#F0F1F2"))
                .size(2)
                .build().addTo(recyclerView);
        //Rv滑动监听
        recyclerView.addOnScrollListener(new HomeRvoScrollListener(this));
        //Rv点击事件
        recyclerView.addOnItemTouchListener(new HomeItemClickListener(getChildFragmentManager(), mPresenter));
    }

    @OnClick(R2.id.my)
    void toMy(){
        fragmentStart(R.id.action_homeDelegate_to_userDelegate);
    }

    @OnClick(R2.id.imageView3)
    void toAdd(){
        fragmentStart(R.id.action_homeDelegate_to_addDelegate);
    }

    @Override
    public void updateRv() {
        homeAdapter.notifyDataSetChanged();
    }



    @Override
    public void updateItem() {
        homeAdapter.notifyDataSetChanged();
    }



    @Override
    public View setToolbar() {
        return toolbar;
    }

    @Override
    public void setHomeOffset(int r, int b) {

    }


}

