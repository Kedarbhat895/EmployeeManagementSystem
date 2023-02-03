package com.springBootwithMongo.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseForAggregate {
    String _id;
    Integer totalSalary;

}
