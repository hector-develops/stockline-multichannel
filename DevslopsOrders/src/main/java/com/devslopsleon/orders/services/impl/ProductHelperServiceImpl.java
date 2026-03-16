package com.devslopsleon.orders.services.impl;


import com.devslopsleon.orders.core.dto.product.*;
import com.devslopsleon.orders.core.models.company.Company;
import com.devslopsleon.orders.core.models.product.BrandProduct;
import com.devslopsleon.orders.core.models.product.ColorProduct;
import com.devslopsleon.orders.core.models.product.UnitOfMeasure;
import com.devslopsleon.orders.core.repository.BrandRepository;
import com.devslopsleon.orders.core.repository.ColorRepository;
import com.devslopsleon.orders.core.repository.CompanyRepository;
import com.devslopsleon.orders.core.repository.UnitOfMeasureRepository;
import com.devslopsleon.orders.dexeptions.CompanyNotFoundException;
import com.devslopsleon.orders.mappers.product.BrandMapper;
import com.devslopsleon.orders.mappers.product.ColorMapper;
import com.devslopsleon.orders.mappers.product.UnitMapper;
import com.devslopsleon.orders.services.IProductHelperService;
import com.devslopsleon.orders.services.auth.TenantContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductHelperServiceImpl implements IProductHelperService {

    private final BrandRepository brandRepository;
    private final ColorRepository colorRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitMapper unitMapper;
    private final BrandMapper brandMapper;
    private final ColorMapper colorMapper;


    @Override
    public BrandDTO addBrand(BrandRequestDTO brandRequestDTO) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        BrandProduct b = brandMapper.toEntityFromRequest(brandRequestDTO);
        b.setCompanyId(companyPk);
        BrandProduct brandSaved = brandRepository.save(b);
        return brandMapper.toBrandDTO(brandSaved);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BrandDTO> listBrandsPage(String q, Pageable pageable) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        Page<BrandProduct> page;
        if (q != null && !q.trim().isEmpty()){
            page = brandRepository.findBrandListByCompanyPage(companyPk, pageable);
        }else {
            page = brandRepository.findBrandListByCompanyPage(companyPk, pageable);
        }
        return page.map(brandMapper::toBrandDTO);
    }

    @Override
    public BrandDTO findBrandByCode(final String code) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        Optional<BrandProduct> brand = brandRepository.findBrandByCodeAndCompany(code, companyPk);
        if(brand.isEmpty()){
            throw new RuntimeException("Brand not found");
        }
        return brandMapper.toBrandDTO(brand.get());
    }

    @Override
    public BrandDTO findBrandByIdAndCompanyId(Long id) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        Optional<BrandProduct> brand = brandRepository.findBrandByIdAndCompany(id, companyPk);
        if(brand.isEmpty()){
            throw new RuntimeException("Brand not found");
        }
        return brandMapper.toBrandDTO(brand.get());
    }

    @Override
    public List<BrandDTO> brandList() {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        List<BrandProduct> brands = brandRepository.findBrandListByCompany(companyPk);
        return brandMapper.toListDTOs(brands);
    }

    @Override
    public List<BrandDTO> brandListActivated() {
        return List.of();
    }

    @Override
    public ColorDTO addColor(ColorDTO colorDTO) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        ColorProduct cp = colorMapper.toEntityFromDTO(colorDTO);
        cp.setCompanyId(companyPk);
        ColorProduct colorSaved = colorRepository.save(cp);
        return colorMapper.toColorDTO(colorSaved);
    }

    @Override
    public ColorDTO findColorByCode(final String code) {
        return null;
    }

    @Override
    public ColorDTO findColorByIdAndCompany(Long id) {
        return null;
    }

    @Override
    public List<ColorDTO> colorsList() {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        List<ColorProduct> colors = colorRepository.findColorsByCompany(companyPk);

        return colorMapper.toListDTO(colors);
    }

    @Override
    public List<ColorDTO> colorsListActivated() {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        List<ColorProduct> colors = colorRepository.findColorsByCompany(companyPk);
        return colorMapper.toListDTO(colors);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ColorDTO> listColorsPage(String q, Pageable pageable) {
        return null;
    }

    @Override
    public UnitOfMeasureDTO addUnit(final UnitOfMeasureDTO unit) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        UnitOfMeasure unitModel = unitMapper.toEntity(unit);
        unitModel.setCompanyId(companyPk);
        UnitOfMeasure unitSaved = unitOfMeasureRepository.save(unitModel);
        return unitMapper.toUnitDTO(unitSaved);
    }

    @Override
    public UnitOfMeasureDTO findUnitByIdAndCompany(Long id) {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        Optional<UnitOfMeasure> unit = unitOfMeasureRepository.findUnitByIdAndCompany(id, companyPk);
        if(unit.isEmpty()){
            throw new RuntimeException("Unit not found");
        }
        return unitMapper.toUnitDTO(unit.get());
    }

    @Override
    public List<UnitOfMeasureDTO> units() {
        Long companyPk = TenantContext.getCompanyPk();
        if (companyPk == null) {
            throw new RuntimeException("Tenant not resolved"); // o AccessDenied
        }
        List<UnitOfMeasure> units = unitOfMeasureRepository.findUnitsByCompany(companyPk);
        return unitMapper.units(units);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UnitOfMeasureDTO> listUnitsPage(String q, Pageable pageable) {
        return null;
    }

    @Override
    public UnitOfMeasureDTO findUnitByCode(final String code) {
        return null;
    }


    public ProductHelperServiceImpl(BrandRepository brandRepository, ColorRepository colorRepository, UnitOfMeasureRepository unitOfMeasureRepository, UnitMapper unitMapper, BrandMapper brandMapper, ColorMapper colorMapper) {
        this.brandRepository = brandRepository;
        this.colorRepository = colorRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitMapper = unitMapper;
        this.brandMapper = brandMapper;
        this.colorMapper = colorMapper;
    }
}
