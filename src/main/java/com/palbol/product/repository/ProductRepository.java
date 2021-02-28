package com.palbol.product.repository;

import com.palbol.product.domain.ListProductDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ListProductDomain, Long> {
    @Query(value = "select * from sp_getProductList(:search, :page_size, :page_offset, :sort_column, :sort_direction)", nativeQuery = true)
    List<ListProductDomain> findAllWithSearchAndPagination
            (@Param("search") String search,
             @Param("page_size") Integer pageSize,
             @Param("page_offset") Integer pageOffset,
             @Param("sort_column") String sortColumn,
             @Param("sort_direction") String sortDirection);

    @Query(value = "select * from sp_getProductById(:id_prod)", nativeQuery = true)
    ListProductDomain findByStoredProcedure
            (@Param("id_prod") Integer productId);

}
