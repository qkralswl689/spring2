package com.javateam.demoJPA.service;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.javateam.demoJPA.domain.DemoVO;

@Transactional(readOnly = true)
public interface PagingJpaService extends PagingAndSortingRepository<DemoVO, Integer> {
 
    Iterable<DemoVO> findAll(Sort sort);
   
    Page<DemoVO> findAll(Pageable pageable);
   
    DemoVO findById(BigDecimal id);
   
} 