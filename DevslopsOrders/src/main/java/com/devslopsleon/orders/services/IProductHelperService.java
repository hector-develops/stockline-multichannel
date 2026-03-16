package com.devslopsleon.orders.services;

import com.devslopsleon.orders.core.dto.product.*;
import org.springframework.data.domain.*;

import java.util.List;

public interface IProductHelperService {

    BrandDTO addBrand(final BrandRequestDTO brandRequestDTO);
    Page<BrandDTO> listBrandsPage(String q, Pageable pageable);
    BrandDTO findBrandByCode(final String code);
    BrandDTO findBrandByIdAndCompanyId(final Long id);
    List<BrandDTO> brandList();
    List<BrandDTO> brandListActivated();

    ColorDTO addColor(final ColorDTO colorDTO);
    ColorDTO findColorByCode(final String code);
    ColorDTO findColorByIdAndCompany(final Long id);
    List<ColorDTO> colorsList();
    List<ColorDTO> colorsListActivated();
    Page<ColorDTO> listColorsPage(String q, Pageable pageable);

    UnitOfMeasureDTO addUnit(final UnitOfMeasureDTO unit);
    UnitOfMeasureDTO findUnitByCode(final String code);
    UnitOfMeasureDTO findUnitByIdAndCompany(final Long id);
    List<UnitOfMeasureDTO> units();
    Page<UnitOfMeasureDTO> listUnitsPage(String q, Pageable pageable);
}
