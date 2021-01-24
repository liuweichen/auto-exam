package com.autoexam.apiserver.dao;

import com.autoexam.apiserver.beans.Course;
import com.autoexam.apiserver.model.response.Overview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseDao extends JpaRepository<Course, Long> {
  @Query("select c from Course c where (c.teacherId = :teacherId or :teacherId is null) and (c.id = :id or :id is null)")
  List<Course> getAllByTeacherId(@Param("teacherId") Long teacherId, @Param("id") Long id);

  @Modifying
  @Transactional(rollbackFor = Exception.class)
  @Query("delete from Course c where c.id = :id and c.teacherId = :teacherId")
  void deleteByIdAndTeacherId(@Param("id") Long id, @Param("teacherId") Long teacherId);

  @Query("select t from Course t where t.teacherId = :teacherId and t.id = :id")
  Optional<Course> getByTeacherIdAndCourseId(@Param("teacherId") Long teacherId, @Param("id") Long id);

  @Query("select ch.id from Course co join Chapter ch on co.id = ch.courseId where co.teacherId = :teacherId and ch.id = :id")
  Optional<Long> getByTeacherIdAndChapterId(@Param("teacherId") Long teacherId, @Param("id") Long id);

  @Query("select ex.id from Course co join Exam ex on co.id = ex.courseId where co.teacherId = :teacherId and ex.id = :id")
  Optional<Long> getByTeacherIdAndExamId(@Param("teacherId") Long teacherId, @Param("id") Long id);

  @Query("select qu.id from Course co join Chapter ch on co.id = ch.courseId join Question qu on ch.id = qu.chapterId where co.teacherId = :teacherId and qu.id = :id")
  Optional<Long> getByTeacherIdAndQuestionId(@Param("teacherId") Long teacherId, @Param("id") Long id);

  @Query("select count(*) from Course co join Chapter ch on co.id = ch.courseId join Question qu on ch.id = qu.chapterId where co.teacherId = :teacherId and qu.id in :idList")
  Long getByTeacherIdAndQuestionIdList(@Param("teacherId") Long teacherId, @Param("idList") List<Long> idList);

  @Query("select new com.autoexam.apiserver.model.response.Overview(" +
    "count(distinct te.id), count(distinct co.id), count(distinct ch.id), count(distinct qu.id), count(distinct ex.id)) " +
    "from Teacher te left join Course co on te.id = co.teacherId left join Chapter ch on co.id = ch.courseId left join Question qu on ch.id = qu.chapterId left join Exam ex on co.id = ex.courseId " +
    "where :teacherId is null or te.id = :teacherId")
  Overview getOverview(@Param("teacherId") Long teacherId);
}
