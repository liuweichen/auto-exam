package com.autoexam.apiserver.controller;

import com.autoexam.apiserver.beans.Course;
import com.autoexam.apiserver.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CourseController {
  @Autowired
  private CourseService service;

  @PostMapping("/teachers/{teacher_id}/courses")
  public void createCourse(
    @PathVariable("teacher_id") Long teacherId,
    @Valid @RequestBody Course course) {
    course.setTeacherId(teacherId);
    service.save(course);
  }

  @GetMapping("/teachers/{teacher_id}/courses")
  public List<Course> getCourses(@PathVariable("teacher_id") Long teacherId) {
    return service.getAll(teacherId);
  }

  @DeleteMapping("/teachers/{teacher_id}/courses/{course_id}")
  public void deleteCourse(
    @PathVariable("teacher_id") Long teacherId,
    @PathVariable("course_id") Long courseId) {
    service.deleteById(courseId);
  }
}
