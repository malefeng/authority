package com.innove.authority.dao;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ExampleMapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author dijiangtao
 * @Date 2019-12-25 14:10
 * @Description: 基础通用 Mapper
 */
public interface GenericMapper<T> extends BaseMapper<T>, MySqlMapper<T>, ExampleMapper<T> {
}
