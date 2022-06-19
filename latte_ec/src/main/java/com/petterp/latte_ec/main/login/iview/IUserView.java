package com.petterp.latte_ec.main.login.iview;

import com.petterp.latte_core.mvp.view.IBaseView;

/**
 * 用户详细信息-view
 *
 * @ author Zhenyu
 * @date 2022-05-06
 */
public interface IUserView extends IBaseView {

    void updateName(String name);

    void updateSex(String sex);

}
