package com.mengxuegu.springboot.mapper;

import com.mengxuegu.springboot.entities.Provider;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProviderMapper {

    List<Provider> getAllProviders();

    List<Provider> getProviders(Provider provider);

    Provider getProviderById(Integer pid);

    int addProvider(Provider provider);

    int updateProvider(Provider provider);

    int deleteProvider(Integer pid);

}
