package com.example.bankapplication.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class BaseDto implements Serializable {
    @JsonIgnore
    private Long id;
    protected String uuid;
    protected Date createdDate;
    protected String name;

}
