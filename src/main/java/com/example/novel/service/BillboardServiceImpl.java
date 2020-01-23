package com.example.novel.service;

import com.example.novel.dao.BillboardMapper;
import com.example.novel.po.Billboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillboardServiceImpl implements BillboardService {

    @Autowired
    BillboardMapper billboardMapper;

    @Override
    public List<Billboard> getAllBillboard() {
        return billboardMapper.getAllBillboard();
    }

    @Override
    public Billboard BillboardAll(Long id) {
        return billboardMapper.getBillboard(id);
    }
}
