package com.lxiaow.utils.excelUtil;

/**
 * @ClassName HotQuestion
 * @Description: TODO
 * @Author lxw
 * @Date 2020/1/10
 **/
public class HotQuestion {

    private String quest_province;

    private String quest_province_name;

    private String quest_title;

    private String quest_sort;

    private String quest_count;

    private String quest_interaction_date;

    private String quest_proportion;

    public String getQuest_province() {
        return quest_province;
    }

    public void setQuest_province(String quest_province) {
        this.quest_province = quest_province;
    }

    public String getQuest_province_name() {
        return quest_province_name;
    }

    public void setQuest_province_name(String quest_province_name) {
        this.quest_province_name = quest_province_name;
    }

    public String getQuest_title() {
        return quest_title;
    }

    public void setQuest_title(String quest_title) {
        this.quest_title = quest_title;
    }

    public String getQuest_sort() {
        return quest_sort;
    }

    public void setQuest_sort(String quest_sort) {
        this.quest_sort = quest_sort;
    }

    public String getQuest_count() {
        return quest_count;
    }

    public void setQuest_count(String quest_count) {
        this.quest_count = quest_count;
    }

    public String getQuest_interaction_date() {
        return quest_interaction_date;
    }

    public void setQuest_interaction_date(String quest_interaction_date) {
        this.quest_interaction_date = quest_interaction_date;
    }

    public String getQuest_proportion() {
        return quest_proportion;
    }

    public void setQuest_proportion(String quest_proportion) {
        this.quest_proportion = quest_proportion;
    }

    @Override
    public String toString() {
        return "HotQuestion{" +
                "quest_province='" + quest_province + '\'' +
                ", quest_province_name='" + quest_province_name + '\'' +
                ", quest_title='" + quest_title + '\'' +
                ", quest_sort='" + quest_sort + '\'' +
                ", quest_count='" + quest_count + '\'' +
                ", quest_interaction_date='" + quest_interaction_date + '\'' +
                ", quest_proportion='" + quest_proportion + '\'' +
                '}';
    }
}
