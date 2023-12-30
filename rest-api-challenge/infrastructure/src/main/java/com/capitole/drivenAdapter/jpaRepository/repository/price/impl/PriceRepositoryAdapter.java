package com.capitole.drivenAdapter.jpaRepository.repository.price.impl;

import com.capitole.drivenAdapter.jpaRepository.entity.BrandEntity;
import com.capitole.drivenAdapter.jpaRepository.entity.PriceEntity;
import com.capitole.drivenAdapter.jpaRepository.entity.ProductEntity;
import com.capitole.drivenAdapter.jpaRepository.mapper.PriceMapper;
import com.capitole.drivenAdapter.jpaRepository.repository.brand.BrandRepository;
import com.capitole.drivenAdapter.jpaRepository.repository.price.PriceRepository;
import com.capitole.drivenAdapter.jpaRepository.repository.product.ProductRepository;
import com.capitole.drivenPort.repository.PriceRepositoryPort;
import com.capitole.entity.price.Price;
import com.capitole.exception.EntityNotFoundException;
import com.capitole.exception.NullPointerException;
import com.capitole.exception.constant.ApiConstant;
import com.capitole.exception.constant.BrandConstant;
import com.capitole.exception.constant.ProductConstant;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PriceRepositoryAdapter implements PriceRepositoryPort {

    private final PriceRepository priceRepository;
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;
    private final PriceMapper priceMapper;



    public PriceRepositoryAdapter(PriceRepository priceRepository, PriceMapper priceMapper, BrandRepository brandRepository, ProductRepository productRepository){
        this.priceRepository = priceRepository;
        this.priceMapper = priceMapper;
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
    }
    @Override
    @Transactional
    public Price save(Price price) {
        PriceEntity priceEntity = priceMapper.toPriceEntity(price);
        //validate if exist product and brand in order to set on new price
        Pair<BrandEntity, ProductEntity> entities = validateBrandAndProductExistence(price.getBrandId(), price.getProductId());
        priceEntity.setBrand(entities.getFirst());
        priceEntity.setProduct(entities.getSecond());
        priceRepository.save(priceEntity);
        return priceMapper.toPriceDomainEntity(priceEntity);
    }

    @Override
    public Optional<Price> findById(Long id) {
        return priceRepository.findById(id).map(priceMapper::toPriceDomainEntity);
    }

    @Override
    public List<Price> findAll() {
        return priceRepository.findAll().stream().map(priceMapper::toPriceDomainEntity).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<Price> update(Price price) {
        Optional<PriceEntity> optionalPrice = priceRepository.findById(price.getId());
        if (optionalPrice.isPresent()) {
               if(price.getBrandId() != null){
                   Optional<BrandEntity> optionalBrand = brandRepository.findById(price.getBrandId());
                   optionalBrand.ifPresent(brandEntity -> optionalPrice.get().setBrand(brandEntity));
               }
               if(price.getProductId() != null) {
                   Optional<ProductEntity> optionalProduct = productRepository.findById(price.getProductId());
                   optionalProduct.ifPresent(productEntity -> optionalPrice.get().setProduct(productEntity));
               }
            PriceEntity current = optionalPrice.get();
            priceMapper.updateEntity(price,current);
            PriceEntity updatedPriceEntity = priceRepository.save(current);
            return Optional.of(priceMapper.toPriceDomainEntity(updatedPriceEntity));
        }else {
           return Optional.empty();
        }

    }

    @Override
    public boolean deleteById(Long id) {
        if (priceRepository.existsById(id)) {
            priceRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Price> getQueryPrice(Long brandId, Long productId, LocalDateTime applicationDate) {
       return priceRepository.findAllByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId(applicationDate,applicationDate,brandId,productId).stream().map(priceMapper::toPriceDomainEntity).collect(Collectors.toList());
    }

    private Pair<BrandEntity, ProductEntity> validateBrandAndProductExistence(Long brandId, Long productId) {
        if (brandId == null || productId == null) {
            throw new NullPointerException(ApiConstant.ID_CAN_NOT_BE_NULL_MESSAGE_ERROR);
        }
        Optional<BrandEntity> brandEntity = brandRepository.findById(brandId);
        if (brandEntity.isEmpty()) {
            throw  new EntityNotFoundException(
                    String.format(BrandConstant.BRAND_NOT_FOUND_MESSAGE_ERROR, brandId)
            );
        }

        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        if (productEntity.isEmpty()) {
            throw  new EntityNotFoundException(
                    String.format(ProductConstant.PRODUCT_NOT_FOUND_MESSAGE_ERROR,productId)
            );
        }

        return Pair.of(brandEntity.get(), productEntity.get());
    }


}
