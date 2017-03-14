package com.github.vuzoll.datasets.documents

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class VkCareerRecord {

    Integer groupId
    String company
    Integer countryId
    Integer cityId
    Integer from
    Integer until
    String position
}
