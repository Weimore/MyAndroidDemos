package com.example.mymodeldemos.model;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by 吴城林 on 2017/8/24.
 */

public class SectionDataOne extends SectionEntity<DataOne> {


    public SectionDataOne(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SectionDataOne(DataOne dataOne) {
        super(dataOne);
    }
}
