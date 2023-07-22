package com.application.doctor.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpOutput {
   private boolean signUpStatus = true;
   private String massage=null;

}
