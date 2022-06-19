package com.petterp.latte_ec.main.add;

/**
 * @ author Zhenyu on 2022/4/27
 * Summary:
 * 邮箱： 1023927274@qq.com
 */
public class AddRvDataMessage {
    private AddItemFileds mode;
    private String kind;
    private String kindNew;
    private String category;

    public AddRvDataMessage(AddItemFileds mode, String kind, String kindNew, String category) {
        this.mode = mode;
        this.kind = kind;
        this.category = category;
        this.kindNew = kindNew;
    }

    public AddRvDataMessage(AddItemFileds mode, String category) {
        this.mode = mode;
        this.category = category;
    }

    public AddRvDataMessage(AddItemFileds mode, String kind, String category) {
        this.mode = mode;
        this.kind = kind;
        this.kindNew = kind;
        this.category = category;
    }

    public AddItemFileds getMode() {
        return mode;
    }

    public String getKind() {
        return kind;
    }

    public String getKindNew() {
        return kindNew;
    }

    public String getCategory() {
        return category;
    }

}
