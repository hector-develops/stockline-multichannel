package com.devslopsleon.orders.controllers;


import com.devslopsleon.orders.core.dto.product.*;
import com.devslopsleon.orders.services.ICateogryService;
import com.devslopsleon.orders.services.IProductHelperService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

@RestController
@RequestMapping("/api/v1/product/util")
public class ProductHelperController {

    private final IProductHelperService productHelperService;
    private final ICateogryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryRequestDTO categoryRequestDTO
                                            ){

        CategoryDTO c = categoryService.createCategory(categoryRequestDTO);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<?> findCategoryById(@PathVariable Long id
                                              ){

        CategoryDTO c = categoryService.findCategoryById(id);

        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    // ✅ Listar paginado (ADMINISTRATOR, MANAGEMENT, AGENT)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @GetMapping("/category/list")
    public Page<CategoryDTO> listCategoryPage(
            @RequestParam(value = "q", required = false) String q,
            @PageableDefault(size = 20, sort = "modifiedTime", direction = Sort.Direction.DESC) Pageable pageable
    ){
        return categoryService.listCategoryPage(q, pageable);
    }

    @GetMapping("/category/{categoryCode}")
    public ResponseEntity<?> findCategoryByCode(@PathVariable String categoryCode
                                               ){
        return null;
    }

    @PostMapping("/category/upload")
    public ResponseEntity<String> uploadCSV(@RequestParam("file") MultipartFile file){
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))){
            // Mapea automáticamente el CSV a objetos Java
            CsvToBean<CategoryRequestDTO> csvToBean = new CsvToBeanBuilder<CategoryRequestDTO>(reader)
                    .withType(CategoryRequestDTO.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // Procesa línea por línea (ideal para bases de datos pesadas)
            for (CategoryRequestDTO dto : csvToBean) {
                // Aquí llamas a tu lógica de servicio y persistencia
                categoryService.createCategoryCSV(dto);
            }
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok("Procesado con éxito");
    }



    @PostMapping("/unit")
    public ResponseEntity<?> createUnit (@Valid @RequestBody UnitOfMeasureDTO dto) {

        UnitOfMeasureDTO unit = productHelperService.addUnit(dto);

        return new ResponseEntity<>(unit, HttpStatus.OK);
    }

    @GetMapping("/unit/{unitId}")
    public ResponseEntity<?> getUnitById(@PathVariable Long unitId){
        //List<UnitOfMeasureDTO> units = productHelperService.units(companyId);
        UnitOfMeasureDTO unit = productHelperService.findUnitByIdAndCompany(unitId);
        return new ResponseEntity<>(unit, HttpStatus.OK);
    }

    // ✅ Listar paginado (ADMINISTRATOR, MANAGEMENT, AGENT)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @GetMapping("/unit/list")
    public Page<UnitOfMeasureDTO> listUnitsPage(
            @RequestParam(value = "q", required = false) String q,
            @PageableDefault(size = 20, sort = "modifiedTime", direction = Sort.Direction.DESC) Pageable pageable
    ){
        return productHelperService.listUnitsPage(q, pageable);
    }

    @PostMapping("/brand")
    public ResponseEntity<?> createBrand (@Valid @RequestBody BrandRequestDTO dto) {
        BrandDTO brand = productHelperService.addBrand(dto);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    // ✅ Listar paginado (ADMINISTRATOR, MANAGEMENT, AGENT)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @GetMapping("/brand/list")
    public Page<BrandDTO> listBrandsPage(
            @RequestParam(value = "q", required = false) String q,
            @PageableDefault(size = 20, sort = "modifiedTime", direction = Sort.Direction.DESC) Pageable pageable
    ){
       return productHelperService.listBrandsPage(q, pageable);
    }

    @PostMapping("/color")
    public ResponseEntity<?> createColor (@Valid @RequestBody ColorDTO dto) {
        ColorDTO c = productHelperService.addColor(dto);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    // ✅ Listar paginado (ADMINISTRATOR, MANAGEMENT, AGENT)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','MANAGEMENT','AGENT', 'SUPERADMIN')")
    @GetMapping("/color/list")
    public Page<ColorDTO> listColorsPage(
            @RequestParam(value = "q", required = false) String q,
            @PageableDefault(size = 20, sort = "modifiedTime", direction = Sort.Direction.DESC) Pageable pageable
    ){
        return productHelperService.listColorsPage(q, pageable);
    }

    public ProductHelperController(IProductHelperService productHelperService, ICateogryService categoryService) {
        this.productHelperService = productHelperService;
        this.categoryService = categoryService;
    }
}
