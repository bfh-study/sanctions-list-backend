package com.github.bfh.study.slb.rest;

import com.github.bfh.study.slb.SearchPersonResponse;
import com.github.bfh.study.slb.domain.entities.SanctionEntity;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 * Api endpoint.
 *
 * @author Dario Carosella
 */
@Path("allowed")
public class SearchPersonService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchPersonService.class);

    private static final String MAX_SCORE_PROPERTY_NAME = "sanction-list.max-allowed-score";

    @Inject
    private FullTextEntityManager fullTextEntityManager;

    /**
     * Decide if a person is allowed or not.
     *
     * @param firstName first name
     * @param lastName last name
     * @return response with max score and decision
     */
    @GET
    @Path("/{firstName}/{lastName}")
    public Response isPersonAllowed(
            @PathParam("firstName") String firstName,
            @PathParam("lastName") String lastName) {

        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
            .buildQueryBuilder().forEntity(SanctionEntity.class).get();

        org.apache.lucene.search.Query luceneQuery = qb
            .keyword()
            .onFields(
                "entityInfoList.firstName",
                "entityInfoList.lastName",
                "entityInfoList.wholeName"
            )
            .matching(firstName + " " + lastName)
            .createQuery();

        FullTextQuery fullTextQuery =
            fullTextEntityManager.createFullTextQuery(luceneQuery, SanctionEntity.class);
        fullTextQuery.setProjection(FullTextQuery.SCORE);

        List scores = fullTextQuery.getResultList();
        List<Float> convertedList = new ArrayList<>(scores.size());
        scores.forEach(o -> {
            Object[] objectArray = (Object[]) o;
            convertedList.add((Float) objectArray[0]);
        });

        Optional<Float> maxScore = convertedList.stream()
            .sorted(Comparator.comparing(Float::floatValue, Comparator.reverseOrder()))
            .findFirst();

        SearchPersonResponse responseEntity;
        if (maxScore.isPresent()) {
            Float maxAllowedScore = null;
            try {
                maxAllowedScore = Float.valueOf(System.getProperty(MAX_SCORE_PROPERTY_NAME));
            } catch (NumberFormatException e) {
                LOGGER.warn("can not convert property '" + MAX_SCORE_PROPERTY_NAME + "' to float");
                maxAllowedScore = 0.8F;
            }
            if (maxScore.get() >= maxAllowedScore) {
                responseEntity = new SearchPersonResponse(maxScore.get(), false);
            } else {
                responseEntity = new SearchPersonResponse(maxScore.get(), true);
            }
        } else {
            responseEntity = new SearchPersonResponse(0F, true);
        }

        return Response.ok()
            .header(HttpHeaders.CONTENT_TYPE, "application/json")
            .header("Access-Control-Allow-Origin", "*")
            .header("Access-Control-Allow-Methods", "GET")
            .entity(responseEntity)
            .build();
    }
}
