package com.github.vuzoll.datasets.documents

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class VkMilitaryRecord {

    Integer vkId
    String unit
    Integer countryId
    Integer from
    Integer until
}
