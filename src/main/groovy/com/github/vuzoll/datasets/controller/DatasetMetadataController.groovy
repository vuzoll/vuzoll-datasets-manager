package com.github.vuzoll.datasets.controller

import com.github.vuzoll.datasets.domain.Dataset
import com.github.vuzoll.datasets.repository.DatasetRepository
import com.github.vuzoll.datasets.service.DatasetMetadataService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Slf4j
class DatasetMetadataController {

    @Autowired
    DatasetMetadataService datasetService

    @Autowired
    DatasetRepository datasetRepository

    @PostMapping(path = '/dataset')
    @ResponseBody Dataset registerNewDataset(Dataset dataset) {
        log.info "Received request to register new dataset: $dataset"

        datasetService.registerNewDataset dataset
    }

    @GetMapping(path = '/dataset')
    @ResponseBody Collection<Dataset> listAllDatasets() {
        datasetRepository.findAll()
    }

    @GetMapping(path = '/dataset/{id}')
    @ResponseBody Dataset findDatasetById(@PathVariable String id) {
        datasetRepository.findOne(id)
    }

    @GetMapping(path = '/dataset/search')
    @ResponseBody Dataset findDatasetByName(@RequestParam('name') String name) {
        datasetRepository.findOneByName(name)
    }
}
