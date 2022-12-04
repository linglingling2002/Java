package com.jubilantz.services;

import com.jubilantz.entity.EasClass;
import com.jubilantz.utils.PageUtil;

import java.util.List;

/**
 * 班级
 */
public interface EasClassService {
    List<EasClass> getAll();

    int getCount();

    List<EasClass> getList(EasClass easClass, PageUtil pageUtil);

    List<EasClass> findClassName(String classes);

    void addClass(EasClass easClass);

    void batchDeleteClass(Integer[] ids);

    EasClass getClassView(Integer id);

    void updateClass(EasClass easClass);
}
