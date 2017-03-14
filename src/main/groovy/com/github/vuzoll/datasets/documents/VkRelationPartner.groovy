package com.github.vuzoll.datasets.documents

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes = 'vkId')
class VkRelationPartner {

    Integer vkId
    String firstName
    String lastName
}
