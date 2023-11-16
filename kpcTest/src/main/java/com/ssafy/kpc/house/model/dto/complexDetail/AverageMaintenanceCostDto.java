package com.ssafy.kpc.house.model.dto.complexDetail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class AverageMaintenanceCostDto {
    String averageTotalPrice;
    String summerTotalPrice;
    String winterTotalPrice;
}
