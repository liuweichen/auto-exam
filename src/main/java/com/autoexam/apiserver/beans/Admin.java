package com.autoexam.apiserver.beans;

import com.autoexam.apiserver.beans.base.AuditInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class Admin extends AuditInfo {
  @Id
  @GeneratedValue(generator = "admin_generator")
  @SequenceGenerator(
    name = "admin_generator",
    sequenceName = "admin_sequence",
    initialValue = 1000
  )
  @Column(name = "id", columnDefinition = "bigserial")
  private Long id;

  @NotBlank
  @Size(min = 3, max = 128, message = "name length should be between 3 and 128")
  @Column(name = "name", unique=true, columnDefinition = "text not null unique")
  private String name;

  @NotNull
  @Size(min = 3, max = 128, message = "password length should be between 3 and 128")
  @Column(name = "password", columnDefinition = "text not null")
  private String password;

  @Column(name = "description", columnDefinition = "text")
  private String description;

  @Column(name = "ak", columnDefinition = "text")
  private String ak;

  @Column(name = "sk", columnDefinition = "text")
  private String sk;

  @Column(name = "bucket", columnDefinition = "text")
  private String bucket;

  @Column(name = "host_name", columnDefinition = "text")
  private String hostName;
}
