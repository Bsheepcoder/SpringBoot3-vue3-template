package com.example.mapper;

import com.example.dto.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogisticsMapper {
    @Insert("insert into load_goods (ship_companies, ship_names, work_begin_time, work_end_time, begin_time, end_time, work_port, tidang_number, container_number, container_size, begin_address, end_address) values (#{ship_companies}, #{ship_names}, #{work_begin_time}, #{work_end_time}, #{begin_time}, #{end_time}, #{work_port}, #{tidang_number}, #{container_number}, #{container_size}, #{begin_address}, #{end_address})")
    int insertLoadGoods(LoadGoodsDto loadGoodsDtoDto);

    @Insert("insert into unload_goods (ship_companies, ship_names, work_begin_time, work_end_time, begin_time, end_time, work_port, tidang_number, container_number, container_size, begin_address, end_address) values (#{ship_companies}, #{ship_names}, #{work_begin_time}, #{work_end_time}, #{begin_time}, #{end_time}, #{work_port}, #{tidang_number}, #{container_number}, #{container_size}, #{begin_address}, #{end_address})")
    int insertUnloadGoods(UnloadsGoodsDto loadGoodsDtoDto);

    @Insert("insert into logistics_information (tidang_number, owner_name, owner_id, goods_companies, container_number, goods_name, goods_weight) values (#{tidang_number}, #{owner_name}, #{owner_id}, #{goods_companies}, #{container_number}, #{goods_name}, #{goods_weight})")
    int insertLogisticsInformation(LogisticsInformationDto logisticsInformationDto);

    @Insert("insert into container_dynamics (storage_port, container_number, container_size, tidang_number, storage_address, operatation, operatation_time) values (#{storage_port}, #{container_number}, #{container_size}, #{tidang_number}, #{storage_address}, #{operatation}, #{operatation_time})")
    int insertContainerDynamics(ContainerDynamicsDto containerDynamicsDto);

    @Insert("insert into customer_information (owner_name, owner_id, phone_number, owner_address) values (#{owner_name}, #{owner_id}, #{phone_number}, #{owner_address})")
    int insertCustomerInformation(CustomerInformationDto customerInformationDto);

    @Insert("insert into logistics_companies (goods_companies, contacter_id, contacter, contacter_phone_number, contacter_address) values (#{goods_companies}, #{contacter_id}, #{contacter}, #{contacter_phone_number}, #{contacter_address})")
    int insertLogisticsCompanies(LogisticsCompaniesDto logisticsCompaniesDto);
}
