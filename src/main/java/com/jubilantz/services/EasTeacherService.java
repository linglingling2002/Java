package com.jubilantz.services;

import com.jubilantz.entity.EasTeacher;

import java.util.List;

/**
 * 教师
 */
public interface EasTeacherService {

    List<EasTeacher> findTeacherList(EasTeacher easTeacher) throws Exception;

    List<EasTeacher> findListByUsername(String username);

    EasTeacher getTeacherByUsername(String username);

    void updateTeacher(EasTeacher easTeacher);

    List<EasTeacher> getAll();

    EasTeacher findTeacherByUsername(String username);

    int getTotal();

    void addUsername(String username);

    void deleteTeacher(String username);
}
