package com.palbol.product.repository;

import com.palbol.product.domain.ProductDomain;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<ProductDomain, Long> {
    @Query(value = "select * from sp_getProductList(:page_size, :page_offset, :sort_column, :sort_direction)", nativeQuery = true)
    List<ProductDomain> findAllBetweenStoredProcedure
            (@Param("page_size") Integer pageSize,
             @Param("page_offset") Integer pageOffset,
             @Param("sort_column") String sortColumn,
             @Param("sort_direction") String sortDirection);

}
