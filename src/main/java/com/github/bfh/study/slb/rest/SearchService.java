package com.github.bfh.study.slb.rest;

import com.github.bfh.study.slb.domain.entities.SanctionEntity;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;

@Path("search")
public class SearchService {

    @Inject
    private FullTextEntityManager fullTextEntityManager;

    @GET
    public Response search(@QueryParam("query") String queryString) {
        QueryBuilder qb = fullTextEntityManager.getSearchFactory()
            .buildQueryBuilder().forEntity(SanctionEntity.class).get();

        org.apache.lucene.search.Query luceneQuery = qb
            .keyword()
            .onFields(SanctionEntity.getSearchFields())
            .matching(queryString)
            .createQuery();

        FullTextQuery fullTextQuery =
            fullTextEntityManager.createFullTextQuery(luceneQuery, SanctionEntity.class);
        List list = fullTextQuery.getResultList();

        return Response
                .status(Status.OK)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .entity(list)
                .build();
    }
}
