package com.github.vuzoll.datasets.controller

import com.github.vuzoll.datasets.domain.Dataset
import com.github.vuzoll.datasets.repository.DatasetRepository
import com.github.vuzoll.datasets.service.DatasetExplorationService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletResponse

@RestController
@Slf4j
class DatasetExplorationController {

    @Autowired
    DatasetExplorationService datasetExplorationService

    @Autowired
    DatasetRepository datasetRepository

    @GetMapping(path = '/dataset/{id}/exploration')
    void findDatasetById(@PathVariable String id, HttpServletResponse response) {
        Dataset dataset = datasetRepository.findOne(id)
        if (dataset != null) {
            response.outputStream.withPrintWriter { writer ->
                writer.write(datasetExplorationService.exploration(dataset))
                writer.write('\n')
            }
        }
    }
}
