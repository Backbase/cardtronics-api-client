
package com.backbase.accelerators.cardtronics.model;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Data
public class Services {

    private List<String> service = null;
    private Map<String, Object> additionalProperties = new HashMap<>();
}
