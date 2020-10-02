package com.autoexam.apiserver.controller;

import com.autoexam.apiserver.beans.Chapter;
import com.autoexam.apiserver.controller.base.ExceptionHandlerController;
import com.autoexam.apiserver.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ChapterController extends ExceptionHandlerController {
  @Autowired
  private ChapterService service;

  @PostMapping("/teachers/{teacher_id}/courses/{course_id}/chapters")
  public void createChapter(
    @PathVariable("teacher_id") Long teacherId,
    @PathVariable("course_id") Long courseId,
    @Valid @RequestBody Chapter chapter) {
    chapter.setCourseId(courseId);
    service.save(chapter);
  }

  @PutMapping("/teachers/{teacher_id}/courses/{course_id}/chapters/{chapter_id}")
  public void updateChapter(
    @PathVariable("teacher_id") Long teacherId,
    @PathVariable("course_id") Long courseId,
    @PathVariable("chapter_id") Long chapterId,
    @Valid @RequestBody Chapter course) {
    course.setCourseId(courseId);
    course.setId(chapterId);
    service.update(course);
  }

  @GetMapping("/teachers/{teacher_id}/courses/{course_id}/chapters")
  public List<Chapter> getChapters(
    @PathVariable("teacher_id") Long teacherId,
    @PathVariable("course_id") Long courseId) {
    return service.getAll(courseId);
  }

  @DeleteMapping("/teachers/{teacher_id}/courses/{course_id}/chapters/{chapter_id}")
  public void deleteChapter(
    @PathVariable("teacher_id") Long teacherId,
    @PathVariable("course_id") Long courseId,
    @PathVariable("chapter_id") Long chapterId) {
    service.deleteById(chapterId);
  }
}
