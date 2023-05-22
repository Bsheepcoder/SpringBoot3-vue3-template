package com.example.service;

import com.example.dto.*;
import com.example.entity.UnloadsGoods;
import com.example.mapper.LogisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogisticService {
    @Autowired
    LogisticsMapper logisticsMapper;

    @Transactional(rollbackFor = Exception.class)
    public int insertLoadGoods(LoadGoodsDto loadGoodsDto){
        logisticsMapper.insertLoadGoods(loadGoodsDto);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertUnloadGoods(UnloadsGoodsDto unloadsGoodsDto){
        logisticsMapper.insertUnloadGoods(unloadsGoodsDto);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertLogisticsInformation(LogisticsInformationDto logisticsInformationDto){
        logisticsMapper.insertLogisticsInformation(logisticsInformationDto);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertContainerDynamics(ContainerDynamicsDto containerDynamicsDto){
        logisticsMapper.insertContainerDynamics(containerDynamicsDto);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertCustomerInformation(CustomerInformationDto customerInformationDto){
        logisticsMapper.insertCustomerInformation(customerInformationDto);
        return 1;
    }

    @Transactional(rollbackFor = Exception.class)
    public int insertLogisticsCompanies(LogisticsCompaniesDto logisticsCompaniesDto){
        logisticsMapper.insertLogisticsCompanies(logisticsCompaniesDto);
        return 1;
    }
}
