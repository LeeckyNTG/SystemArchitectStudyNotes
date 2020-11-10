package com.clover.springcloud.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {

    /**
     * 扣减库存
     */
    void decrease(@Param("userId") Long userId, @Param("count") Integer count);

}
