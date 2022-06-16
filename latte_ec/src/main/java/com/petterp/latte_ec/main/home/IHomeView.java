package com.petterp.latte_ec.main.home;

import com.petterp.latte_core.mvp.view.IBaseView;
import com.petterp.latte_ui.recyclear.MultipleItemEntity;

import java.util.HashMap;
import java.util.List;

/**
 * 首页-view层
 * @author by luoluo
 */
public interface IHomeView extends IBaseView {


    /**
     * 设置内部内容
     */
    void setTitleinfo(HashMap<IHomeRvFields,String> map);


    /**
     * 显示首页rv
     */
    void showRv(List<MultipleItemEntity> list);

    /**
     * 更新Rv内容
     */
    void updateRv();


    /**
     * 更新update或者delegate分类后的数据
     */
    void updateItem();
}
