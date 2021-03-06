package com.github.vuzoll.datasets.domain

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.data.annotation.Id

@EqualsAndHashCode(includes = 'name')
@ToString(includeNames = true)
class Dataset {

    @Id
    String id

    String name

    String type

    Map parameters
}