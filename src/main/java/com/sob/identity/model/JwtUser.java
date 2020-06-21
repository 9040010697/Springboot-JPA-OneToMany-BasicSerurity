package com.sob.identity.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtUser {
  private String id;
  private String subject;
  private String role;

}
