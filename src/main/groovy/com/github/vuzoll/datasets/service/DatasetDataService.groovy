package com.github.vuzoll.datasets.service

import com.github.vuzoll.datasets.domain.Dataset
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

import static org.springframework.data.mongodb.core.query.Criteria.where
import static org.springframework.data.mongodb.core.query.Query.query

@Service
@Slf4j
class DatasetDataService {

    @Autowired
    MongoTemplate mongoTemplate

    void deleteData(Dataset dataset) {
        log.warn "Deleting data from dataset [$dataset.name]..."

        mongoTemplate.remove(query(where('datasetName').is(dataset.name)), dataset.parameters.documentName.toString())

        log.info "Succesfully deleted data from dataset [$dataset.name]..."
    }
}
