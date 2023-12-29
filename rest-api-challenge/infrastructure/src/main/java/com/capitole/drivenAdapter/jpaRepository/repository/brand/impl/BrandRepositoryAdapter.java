package com.capitole.drivenAdapter.jpaRepository.repository.brand.impl;

import com.capitole.drivenAdapter.jpaRepository.entity.BrandEntity;
import com.capitole.drivenAdapter.jpaRepository.entity.ProductEntity;
import com.capitole.drivenAdapter.jpaRepository.mapper.BrandMapper;
import com.capitole.drivenAdapter.jpaRepository.repository.brand.BrandRepository;
import com.capitole.drivenPort.repository.BrandRepositoryPort;
import com.capitole.entity.brand.Brand;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BrandRepositoryAdapter implements BrandRepositoryPort {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public BrandRepositoryAdapter(BrandRepository brandRepository, BrandMapper brandMapper){
        this.brandRepository = brandRepository;
        this.brandMapper = brandMapper;
    }
    @Override
    @Transactional
    public Brand save(Brand brand) {
        BrandEntity brandEntity = brandMapper.toEntity(brand);
        brandRepository.save(brandEntity);
        return brandMapper.toDomainEntity(brandEntity);
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brandRepository.findById(id).map(brandMapper::toDomainEntity);
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll().stream().map(brandMapper::toDomainEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<Brand> update(Brand brand) {
        Optional<BrandEntity> optionalProduct = brandRepository.findById(brand.getId());
        if (optionalProduct.isPresent()) {
            BrandEntity current = optionalProduct.get();
            brandMapper.updateEntity(brand,current);
            BrandEntity updatedBrandEntity = brandRepository.save(current);
            return Optional.of(brandMapper.toDomainEntity(updatedBrandEntity));
        }else {
            return Optional.empty();
        }

    }

    @Override
    public boolean deleteById(Long id) {
        if (brandRepository.existsById(id)) {
            brandRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
