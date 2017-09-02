package com.example.mymodeldemos.utils;

import com.example.mymodeldemos.R;
import com.example.mymodeldemos.model.DataOne;
import com.example.mymodeldemos.model.MultipleDataOne;
import com.example.mymodeldemos.model.SectionDataOne;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 吴城林 on 2017/8/24.
 */

public class DataServer {

    private static List<DataOne> dataOnes = new ArrayList<>();

    private static List<SectionDataOne> sections = new ArrayList<>();

    private static List<MultipleDataOne> multiples = new ArrayList<>();

    public static List<DataOne> getDataList() {
        dataOnes.clear();
        DataOne dataOne;
        for (int i = 0; i < 20; i++) {
            dataOne = new DataOne(R.drawable.rella4, "图片主题", "海量图片赏析");
            dataOnes.add(dataOne);
        }
        return dataOnes;
    }

    public static List<SectionDataOne> getSectionDataList() {
        sections.clear();
        SectionDataOne sectionDataOne;
        for (int i = 0; i < 20; i++) {
            if (i % 5 == 0) {
                sectionDataOne = new SectionDataOne(true, "动漫专题");
            } else {
                DataOne dataOne = new DataOne(R.drawable.rella4, "图片主题", "海量图片赏析");
                sectionDataOne = new SectionDataOne(dataOne);
            }
            sections.add(sectionDataOne);
        }

        return sections;
    }

    public static List<MultipleDataOne> getMultipleDataList() {
        multiples.clear();
        MultipleDataOne dataOne;
        for (int i = 0; i < 3; i++) {
            dataOne = new MultipleDataOne("动漫专题");
            multiples.add(dataOne);
            for (int j = 0; j < 4; j++) {
                dataOne = new MultipleDataOne(R.drawable.rella4, "图片主题", "海量图片赏析");
                multiples.add(dataOne);
            }
            dataOne = new MultipleDataOne(R.drawable.rella4, "图片主题");
            multiples.add(dataOne);
        }
        return multiples;
    }
}
