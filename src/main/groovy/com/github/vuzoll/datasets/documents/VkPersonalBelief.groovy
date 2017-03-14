package com.github.vuzoll.datasets.documents

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class VkPersonalBelief {

    Integer politicalBelief
    Collection<String> languages
    String religionBelief
    String inspiredBy
    Integer importantInPeople
    Integer importantInLife
    Integer smokingAttitude
    Integer alcoholAttitude
}
