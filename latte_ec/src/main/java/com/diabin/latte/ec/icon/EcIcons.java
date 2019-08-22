package com.diabin.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * Create by 心跳 on 2019/8/16 7:44
 * Blog : https://mp.csdn.net/
 * escription :
 */
public enum EcIcons implements Icon {
    icon_scan('\ue606'),
    icon_ali_pay('\ue606');


    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return  name().replace('_', '-');
    }

    @Override
    public char character() {
        return character;
    }}
