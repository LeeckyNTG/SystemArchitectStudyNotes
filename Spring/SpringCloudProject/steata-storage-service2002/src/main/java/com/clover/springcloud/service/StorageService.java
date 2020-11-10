package com.clover.springcloud.service;

public interface StorageService {
    void decrease(Long productId, Integer count);
}
