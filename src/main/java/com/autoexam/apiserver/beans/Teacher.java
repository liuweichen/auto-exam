package com.autoexam.apiserver.beans;

import com.autoexam.apiserver.annotation.file.PostNotNull;
import com.autoexam.apiserver.beans.base.AuditInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(
  value = {"password"},
  allowSetters = true
)
@Table(name = "teacher")
public class Teacher extends AuditInfo {
  @Id
  @GeneratedValue(generator = "teacher_generator")
  @SequenceGenerator(
    name = "teacher_generator",
    sequenceName = "teacher_sequence",
    initialValue = 1000
  )
  @Column(name = "id", columnDefinition = "bigserial")
  private Long id;

  @NotBlank(message = "name can not be null")
  @Size(min = 3, max = 128, message = "name length should be between 3 and 128")
  @Column(name = "name", columnDefinition = "text not null unique")
  private String name;

  @PostNotNull(message = "password can not be null")
  @Size(min = 3, max = 128, message = "password length should be between 3 and 128")
  @Column(name = "password", columnDefinition = "text not null")
  private String password;

  @Column(name = "description", columnDefinition = "text")
  private String description;

  @Column(name = "admin_id", columnDefinition = "bigint not null references admin(id)")
  private Long adminId;

  @Column(name = "ak", columnDefinition = "text")
  private String ak;

  @Column(name = "sk", columnDefinition = "text")
  private String sk;

  @Column(name = "bucket", columnDefinition = "text")
  private String bucket;
}
