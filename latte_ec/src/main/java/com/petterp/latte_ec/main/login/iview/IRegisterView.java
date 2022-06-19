package com.petterp.latte_ec.main.login.iview;

import com.petterp.latte_core.mvp.view.IBaseView;

/**
 * @ author Zhenyu
 * @date 2022-04-31
 */
public interface IRegisterView extends IBaseView {

    /**
     * 设置倒计时
     * @param res
     */
    void setTvCode(String res);

    /**
     * 允许点击
     */
    void openClick();


    /**
     * 关闭点击
     */
    void closeClick();


    /**
     * 跳转设置具体用户信息
     */
    void onclickUserInfo();


    /**
     * 验证码错误
     */
    void codeError();
}
