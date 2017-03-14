package com.github.vuzoll.datasets.controller

import com.github.vuzoll.datasets.domain.Dataset
import com.github.vuzoll.datasets.repository.DatasetRepository
import com.github.vuzoll.datasets.service.DatasetDataService
import com.github.vuzoll.datasets.service.DatasetMetadataService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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

    @Autowired
    DatasetDataService datasetDataService

    @PostMapping(path = '/dataset')
    @ResponseBody Dataset registerNewDataset(@RequestBody Dataset dataset) {
        log.info "Received request to register new dataset: ${dataset}"

        datasetService.registerNewDataset dataset
    }

    @DeleteMapping(path = '/dataset/{id}')
    @ResponseBody Dataset deleteDatasetById(@PathVariable String id, @RequestParam('deleteData') Boolean deleteData) {
        log.info "Received request to delete existing dataset with id=${id}"

        if (deleteData) {
            log.info "Received request to delete data associated with dataset with id=${id}"
            Dataset dataset = datasetRepository.findOne(id)
            datasetDataService.deleteData dataset
        }

        datasetRepository.delete(id)
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
