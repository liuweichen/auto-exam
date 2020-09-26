package com.autoexam.apiserver.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "answer")
public class Answer {
  @Id
  @GeneratedValue(generator = "answer_generator")
  @SequenceGenerator(
    name = "answer_generator",
    sequenceName = "answer_sequence",
    initialValue = 1000
  )
  @Column(name = "id", columnDefinition = "bigserial")
  private Long id;

  @NotBlank
  @Column(name = "content", columnDefinition = "text")
  private String content;

  @Column(name = "is_selected", columnDefinition = "boolean")
  private Boolean isSelected;

  @Column(name = "question_id", columnDefinition = "bigint not null references question(id)")
  private Long questionId;
}
