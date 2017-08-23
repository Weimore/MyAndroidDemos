package com.example.mymodeldemos.widget.rowViewWidgets;

import java.util.List;

/**
 * Created by 吴城林 on 2017/7/11.
 */

public class GroupDescriptor {

    List<RowDescriptor> rowDescriptorList;
    String title;

    public GroupDescriptor(List<RowDescriptor> rowDescriptorList) {
        this(rowDescriptorList,null);
    }

    public GroupDescriptor(List<RowDescriptor> rowDescriptorList, String title) {
        this.rowDescriptorList = rowDescriptorList;
        this.title = title;
    }

}
