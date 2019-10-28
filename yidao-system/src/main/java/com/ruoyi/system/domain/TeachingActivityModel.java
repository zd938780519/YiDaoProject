package com.ruoyi.system.domain;

import java.util.List;
import java.util.Map;

public class TeachingActivityModel {
    private List<Map<String,String>> mainMaterials;
    private List<Map<String,String>> assistMaterials;
    private List<Map<String,String>> steps;

    public List<Map<String, String>> getMainMaterials() {
        return mainMaterials;
    }

    public void setMainMaterials(List<Map<String, String>> mainMaterials) {
        this.mainMaterials = mainMaterials;
    }

    public List<Map<String, String>> getAssistMaterials() {
        return assistMaterials;
    }

    public void setAssistMaterials(List<Map<String, String>> assistMaterials) {
        this.assistMaterials = assistMaterials;
    }

    public List<Map<String, String>> getSteps() {
        return steps;
    }

    public void setSteps(List<Map<String, String>> steps) {
        this.steps = steps;
    }
}
