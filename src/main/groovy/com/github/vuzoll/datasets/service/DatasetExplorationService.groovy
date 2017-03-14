package com.github.vuzoll.datasets.service

import com.github.vuzoll.datasets.domain.Dataset
import groovy.json.JsonOutput
import groovy.util.logging.Slf4j
import org.apache.commons.lang3.RandomUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Service

import static org.springframework.data.mongodb.core.query.Criteria.where
import static org.springframework.data.mongodb.core.query.Query.query

@Service
@Slf4j
class DatasetExplorationService {

    @Autowired
    MongoTemplate mongoTemplate

    String exploration(Dataset dataset) {
        if (dataset.type == 'mongo-document') {
            mongoDocumentExploration(dataset)
        } else {
            genericExploration(dataset)
        }
    }

    String mongoDocumentExploration(Dataset dataset) {
        int padSize = ([ "id", "name", "type", "documents count", "random document" ] + dataset.parameters.keySet())*.length().max() + 2

        String exploration

        exploration =  "Id: ".padRight(padSize) + dataset.id + '\n'
        exploration += "Name: ".padRight(padSize) + dataset.name + '\n'
        exploration += "Type: ".padRight(padSize) + dataset.type + '\n'

        dataset.parameters.each { String name, String value ->
            exploration += "${name}: ".padRight(padSize) + value + '\n'
        }

        int documentsCount = mongoTemplate.count(query(where('datasetName').is(dataset.name)), dataset.parameters.documentName.toString())
        exploration += "Documents count: ".padRight(padSize) + documentsCount + '\n'

        int randomIndex = RandomUtils.nextInt(0, documentsCount)
        String randomDocument = mongoTemplate
                .stream(query(where('datasetName').is(dataset.name)), Object, dataset.parameters.documentName.toString())
                .drop(randomIndex)
                .take(1)
                .collect({ JsonOutput.toJson(it) })
                .collect(JsonOutput.&prettyPrint)

        exploration += "Random document:\n" + randomDocument + '\n'

        return exploration
    }

    String genericExploration(Dataset dataset) {
        int padSize = ([ "id", "name", "type" ] + dataset.parameters.keySet())*.length().max() + 2

        String exploration

        exploration =  "Id: ".padRight(padSize) + dataset.id + '\n'
        exploration += "Name: ".padRight(padSize) + dataset.name + '\n'
        exploration += "Type: ".padRight(padSize) + dataset.type + '\n'

        dataset.parameters.each { String name, String value ->
            exploration += "${name}: ".padRight(padSize) + value + '\n'
        }

        return exploration
    }
}
