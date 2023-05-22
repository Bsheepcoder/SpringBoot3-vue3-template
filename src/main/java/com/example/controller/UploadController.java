package com.example.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.poi.excel.ExcelReader;
import com.example.dto.*;
import com.example.entity.*;
import com.example.exception.BusinessException;
import com.example.mapper.LogisticsMapper;
import com.example.service.LogisticService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
@RequestMapping("/api")
public class UploadController {
    @Autowired
    private LogisticService logisticService;

    @PostMapping("/upload")
    @ApiOperation(tags = "IgnoreProductId 页面操作", value = "ProductId数据文件上传")
    public String uploadProductId(MultipartFile file) throws Exception {

        if (ObjectUtil.isNull(file)) {
            throw new BusinessException("请检查Excel是否有数据");
        }

        // 文件名的获取 并判断是否是excel
        String originalFilename = file.getOriginalFilename();
        if (StringUtils.isBlank(originalFilename)) {
            throw new BusinessException("上传的文件名为空");
        }
        String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        if (!"xls".equals(fileSuffix) && !"xlsx".equals(fileSuffix)) {
            throw new BusinessException("文件格式不正确,请检查后重新上传!");
        }

//        // 设置表头别名
//        Map<String, String> headerAlis = new HashMap<>(80);
//        String[] LoadGoodsColumns = {"船公司#ship_companies", "船名称#ship_names", "作业开始时间#work_begin_time", "作业结束时间#work_end_time", "始发时间#begin_time", "到达时间#end_time", "作业港口#work_port", "提单号#tidang_number", "集装箱箱号#container_number", "箱尺寸（TEU）#container_size", "启运地#begin_address", "目的地#end_address"};
//        for (String LoadGoodsExcelExportColumn : LoadGoodsColumns) {
//            String[] split = LoadGoodsExcelExportColumn.split("#");
//            headerAlis.put(split[0], split[1]);
//        }
//
//        // 将excel中的数据读取成list
//        ExcelReader reader = new ExcelReader(file.getInputStream(), 0);
//        reader.setHeaderAlias(headerAlis);
//        List<LoadGoodsDto> loadGoodsDtos = reader.readAll(LoadGoodsDto.class);
//
//
//        List<LoadGoods> loadGoods = BeanUtil.copyToList(loadGoodsDtos, LoadGoods.class);
//
//        for (int i = 0; i < loadGoods.size(); i++) {
//            logisticService.insertLoadGoods(loadGoodsDtos.get(i));
//            System.out.println(loadGoods);
//        }

//        // 设置表头别名
//        Map<String, String> headerAlis = new HashMap<>(80);
//        String[] UnloadGoodsColumns = {"船公司#ship_companies", "船名称#ship_names", "作业开始时间#work_begin_time", "作业结束时间#work_end_time", "始发时间#begin_time", "到达时间#end_time", "作业港口#work_port", "提单号#tidang_number", "集装箱箱号#container_number", "箱尺寸（TEU）#container_size", "启运地#begin_address", "目的地#end_address"};
//        for (String UnloadGoodsExcelExportColumn : UnloadGoodsColumns) {
//            String[] split = UnloadGoodsExcelExportColumn.split("#");
//            headerAlis.put(split[0], split[1]);
//        }
//
//        // 将excel中的数据读取成list
//        ExcelReader reader = new ExcelReader(file.getInputStream(), 0);
//        reader.setHeaderAlias(headerAlis);
//        List<UnloadsGoodsDto> unloadsGoodsDtos = reader.readAll(UnloadsGoodsDto.class);
//
//
//        List<UnloadsGoods> unloadsGoods = BeanUtil.copyToList(unloadsGoodsDtos, UnloadsGoods.class);
//
//        for (int i = 0; i < unloadsGoods.size(); i++) {
//            logisticService.insertUnloadGoods(unloadsGoodsDtos.get(i));
//            System.out.println(unloadsGoods);
//        }

//        // 设置表头别名
//        Map<String, String> headerAlis = new HashMap<>(80);
//        String[] LogisticsInformationColumns = {"提单号#tidang_number", "货主名称#owner_name", "货主代码#owner_id", "物流公司#goods_companie", "集装箱箱号#container_number", "货物名称#goods_name", "货重（吨）#goods_weight"};
//        for (String LogisticsInformationExcelExportColumn : LogisticsInformationColumns) {
//            String[] split = LogisticsInformationExcelExportColumn.split("#");
//            headerAlis.put(split[0], split[1]);
//        }
//
//        // 将excel中的数据读取成list
//        ExcelReader reader = new ExcelReader(file.getInputStream(), 0);
//        reader.setHeaderAlias(headerAlis);
//        List<LogisticsInformationDto> logisticsInformationDtos = reader.readAll(LogisticsInformationDto.class);
//
//
//        List<LogisticsInformation> logisticsInformations = BeanUtil.copyToList(logisticsInformationDtos, LogisticsInformation.class);
//
//        for (int i = 0; i < logisticsInformations.size(); i++) {
//            logisticService.insertLogisticsInformation(logisticsInformationDtos.get(i));
//            System.out.println(logisticsInformations);
//        }

        // 设置表头别名
        Map<String, String> headerAlis = new HashMap<>(80);
        String[] ContainerDynamicsColumns = {"堆存港口#storage_port", "集装箱箱号#container_number", "箱尺寸（TEU）#container_size", "提单号#tidang_number", "堆场位置#storage_address", "操作#operatation", "操作日期#operatation_time"};
        for (String ContainerDynamicsExcelExportColumn : ContainerDynamicsColumns) {
            String[] split = ContainerDynamicsExcelExportColumn.split("#");
            headerAlis.put(split[0], split[1]);
        }

        // 将excel中的数据读取成list
        ExcelReader reader = new ExcelReader(file.getInputStream(), 0);
        reader.setHeaderAlias(headerAlis);
        List<ContainerDynamicsDto> containerDynamicsDtos = reader.readAll(ContainerDynamicsDto.class);


        List<ContainerDynamics> containerDynamics = BeanUtil.copyToList(containerDynamicsDtos, ContainerDynamics.class);

        for (int i = 0; i < containerDynamics.size(); i++) {
            logisticService.insertContainerDynamics(containerDynamicsDtos.get(i));
            System.out.println(containerDynamics);
        }

//        // 设置表头别名
//        Map<String, String> headerAlis = new HashMap<>(80);
//        String[] CustomerInformationColumns = {"客户名称#owner_name", "客户编号#owner_id", "手机号#phone_number", "省市区#owner_address"};
//        for (String CustomerInformationExcelExportColumn : CustomerInformationColumns) {
//            String[] split = CustomerInformationExcelExportColumn.split("#");
//            headerAlis.put(split[0], split[1]);
//        }
//
//        // 将excel中的数据读取成list
//        ExcelReader reader = new ExcelReader(file.getInputStream(), 0);
//        reader.setHeaderAlias(headerAlis);
//        List<CustomerInformationDto> customerInformationDtos = reader.readAll(CustomerInformationDto.class);
//
//
//        List<CustomerInformation> customerInformations = BeanUtil.copyToList(customerInformationDtos, CustomerInformation.class);
//
//        for (int i = 0; i < customerInformations.size(); i++) {
//            logisticService.insertCustomerInformation(customerInformationDtos.get(i));
//            System.out.println(customerInformations);
//        }

        // 设置表头别名
//        Map<String, String> headerAlis = new HashMap<>(80);
//        String[] LogisticsCompaniesColumns = {"公司名称#goods_companies", "客户编号#contacter_id", "联系人#contacter", "电话#contacter_phone_number", "省市区#contacter_address"};
//        for (String LogisticsCompaniesExcelExportColumn : LogisticsCompaniesColumns) {
//            String[] split = LogisticsCompaniesExcelExportColumn.split("#");
//            headerAlis.put(split[0], split[1]);
//        }
//
//        // 将excel中的数据读取成list
//        ExcelReader reader = new ExcelReader(file.getInputStream(), 0);
//        reader.setHeaderAlias(headerAlis);
//        List<LogisticsCompaniesDto> logisticsCompaniesDtos = reader.readAll(LogisticsCompaniesDto.class);
//
//
//        List<LogisticsCompanies> logisticsCompanies = BeanUtil.copyToList(logisticsCompaniesDtos, LogisticsCompanies.class);
//
//        for (int i = 0; i < logisticsCompanies.size(); i++) {
//            logisticService.insertLogisticsCompanies(logisticsCompaniesDtos.get(i));
//            System.out.println(logisticsCompanies);
//        }

        return "OK";
    }
}
