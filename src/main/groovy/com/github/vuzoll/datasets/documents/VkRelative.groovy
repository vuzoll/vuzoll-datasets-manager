package com.github.vuzoll.datasets.documents

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes = 'vkId')
class VkRelative {

    Integer vkId
    String type
}
