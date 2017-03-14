package com.github.vuzoll.datasets.documents

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes = 'vkId')
class VkCity {

    Integer vkId
    String name
}
