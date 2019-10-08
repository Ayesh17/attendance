package com.project.attendance.model;

import java.util.List;

public class TimeTableMappingDTO {
    private List<TimeTableMapping> timeTableMappings;

    public TimeTableMappingDTO() {
    }

    public TimeTableMappingDTO(List<TimeTableMapping> timeTableMappings) {
        this.timeTableMappings = timeTableMappings;
    }

    public void addTimeTableMapping(TimeTableMapping timeTableMapping){
        this.timeTableMappings.add(timeTableMapping);
    }

    public List<TimeTableMapping> getTimeTableMappings() {
        return timeTableMappings;
    }

    public void setTimeTableMappings(List<TimeTableMapping> timeTableMappings) {
        this.timeTableMappings = timeTableMappings;
    }
}
