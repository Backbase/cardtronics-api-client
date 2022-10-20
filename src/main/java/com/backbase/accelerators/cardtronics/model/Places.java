
package com.backbase.accelerators.cardtronics.model;

import java.util.HashMap;
import java.util.Map;

@lombok.Data
public class Places {

    private Data data;
    private String message;
    private Integer statusCode;
    private Map<String, Object> additionalProperties = new HashMap<>();

}
