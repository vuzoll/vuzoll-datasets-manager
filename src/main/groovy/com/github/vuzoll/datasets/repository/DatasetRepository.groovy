package com.github.vuzoll.datasets.repository

import com.github.vuzoll.datasets.domain.Dataset
import org.springframework.data.repository.PagingAndSortingRepository

interface DatasetRepository extends PagingAndSortingRepository<Dataset, String> {

    Dataset findOneByName(String name)
}