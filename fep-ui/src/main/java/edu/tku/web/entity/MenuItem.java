package edu.tku.web.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MenuItem {
    private String funcId;
    private String funcName;
    private String funcUrl;
    private List<MenuItem> subMenu = new ArrayList<>();
}
