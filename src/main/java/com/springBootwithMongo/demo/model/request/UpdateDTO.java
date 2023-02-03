package com.springBootwithMongo.demo.model.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDTO {

    @Positive
    @Min(100)
    @Max(10000)
    private Integer salary;

    @NotBlank(message = "enter a valid designation")
    private String designation;
}

