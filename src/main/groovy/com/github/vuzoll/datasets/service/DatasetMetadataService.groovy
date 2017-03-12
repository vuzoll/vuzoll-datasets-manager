package com.github.vuzoll.datasets.service

import com.github.vuzoll.datasets.domain.Dataset
import com.github.vuzoll.datasets.repository.DatasetRepository
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Slf4j
class DatasetMetadataService {

    @Autowired
    DatasetRepository datasetRepository

    Dataset registerNewDataset(Dataset dataset) {
        if (datasetRepository.findOneByName(dataset.name)) {
            log.error "Can't create dataset. Datase with name=$dataset.name already exists"
            throw new IllegalArgumentException("Can't create dataset. Datase with name=$dataset.name already exists")
        }

        datasetRepository.save dataset
    }
}
