/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.cognitiveservices.language.luis.authoring.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An object containing the model feature information either the model name or
 * feature name.
 */
public class JsonModelFeatureInformation {
    /**
     * The name of the model used.
     */
    @JsonProperty(value = "modelName")
    private String modelName;

    /**
     * The name of the feature used.
     */
    @JsonProperty(value = "featureName")
    private String featureName;

    /**
     * Get the modelName value.
     *
     * @return the modelName value
     */
    public String modelName() {
        return this.modelName;
    }

    /**
     * Set the modelName value.
     *
     * @param modelName the modelName value to set
     * @return the JsonModelFeatureInformation object itself.
     */
    public JsonModelFeatureInformation withModelName(String modelName) {
        this.modelName = modelName;
        return this;
    }

    /**
     * Get the featureName value.
     *
     * @return the featureName value
     */
    public String featureName() {
        return this.featureName;
    }

    /**
     * Set the featureName value.
     *
     * @param featureName the featureName value to set
     * @return the JsonModelFeatureInformation object itself.
     */
    public JsonModelFeatureInformation withFeatureName(String featureName) {
        this.featureName = featureName;
        return this;
    }

}
