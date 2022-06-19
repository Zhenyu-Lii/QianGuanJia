package com.petterp.latte_core.app;

/**
 * @ author Zhenyu on 2019/4/21
 * Summary:用户是否登录信息回调
 * email： 1023927274@qq.com
 */
public interface IUserCheker {
    void onSignIn();

    void onNotSoignIn();
}
