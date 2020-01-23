package com.example.novel.service;

import com.example.novel.po.Billboard;

import java.util.List;

public interface BillboardService {

    public List<Billboard> getAllBillboard();

    public Billboard BillboardAll(Long id);

}
