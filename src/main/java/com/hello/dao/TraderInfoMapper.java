package com.hello.dao;

import com.hello.model.TraderInfo;
import com.hello.model.TraderInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TraderInfoMapper {
    long countByExample(TraderInfoExample example);

    int deleteByExample(TraderInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TraderInfo record);

    int insertSelective(TraderInfo record);

    List<TraderInfo> selectByExample(TraderInfoExample example);

    TraderInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TraderInfo record, @Param("example") TraderInfoExample example);

    int updateByExample(@Param("record") TraderInfo record, @Param("example") TraderInfoExample example);

    int updateByPrimaryKeySelective(TraderInfo record);

    int updateByPrimaryKey(TraderInfo record);
}