package com.project.attendance.repository;

public interface TimeTableMappingProjection {
    Long id();
   String day();
   String start();
  String end();
    String subject_code();
    Long code();
}
