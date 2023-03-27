package com.silence.product.infrastructure.repository;

import com.silence.product.domain.entity.Product;
import com.silence.product.domain.entity.ProductAttribute;
import com.silence.product.domain.repository.ProductRepository;
import com.silence.product.infrastructure.dao.ProductMapper;
import com.silence.product.infrastructure.repository.DO.ProductAttributeDO;
import com.silence.product.infrastructure.repository.DO.ProductDO;
import com.silence.product.infrastructure.repository.converter.ProductConverter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * CategoryRepositoryImpl
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/8
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Resource
    ProductMapper productMapper;

    @Override
    public List<Product> find() {
        List<ProductDO> productDOS = productMapper.selectProductList();
        return ProductConverter.INSTANCE.productDOSToProducts(productDOS);
    }

    @Override
    @Transactional
    public List<Product> find(String categoryId) {
        List<ProductDO> productDOS = productMapper.selectProductListByCategoryId(categoryId);
        return ProductConverter.INSTANCE.productDOSToProducts(productDOS);
    }

    @Override
    public int save(Product product) {
        ProductDO productDO = ProductConverter.INSTANCE.productToProductDo(product);
        int i = productMapper.insertProduct(productDO);
        return i;
    }

    @Override
    public int saveAttribute(ProductAttribute productAttribute) {
       ProductAttributeDO attributeDO = ProductConverter.INSTANCE.attributeToAttributeDo(productAttribute);
        return productMapper.insertAttribute(attributeDO);
    }
}
