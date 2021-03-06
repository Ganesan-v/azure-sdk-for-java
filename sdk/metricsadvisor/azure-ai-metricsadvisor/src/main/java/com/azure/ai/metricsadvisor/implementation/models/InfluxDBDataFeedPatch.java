// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.metricsadvisor.implementation.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/** The InfluxDBDataFeedPatch model. */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "dataSourceType")
@JsonTypeName("InfluxDB")
@Fluent
public final class InfluxDBDataFeedPatch extends DataFeedDetailPatch {
    /*
     * The dataSourceParameter property.
     */
    @JsonProperty(value = "dataSourceParameter")
    private InfluxDBParameter dataSourceParameter;

    /**
     * Get the dataSourceParameter property: The dataSourceParameter property.
     *
     * @return the dataSourceParameter value.
     */
    public InfluxDBParameter getDataSourceParameter() {
        return this.dataSourceParameter;
    }

    /**
     * Set the dataSourceParameter property: The dataSourceParameter property.
     *
     * @param dataSourceParameter the dataSourceParameter value to set.
     * @return the InfluxDBDataFeedPatch object itself.
     */
    public InfluxDBDataFeedPatch setDataSourceParameter(InfluxDBParameter dataSourceParameter) {
        this.dataSourceParameter = dataSourceParameter;
        return this;
    }
}
