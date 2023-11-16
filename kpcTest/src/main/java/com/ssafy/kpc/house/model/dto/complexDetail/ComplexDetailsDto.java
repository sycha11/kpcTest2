package com.ssafy.kpc.house.model.dto.complexDetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ComplexDetailsDto {
    List<PhotoDto> photos;
    ComplexDetailDto complexDetailDto;
    List<ComplexPyeongDetailDto> complexPyeongDetailDtos;
}
