package com.github.jensgerdes.cteissue;

import com.blazebit.persistence.CriteriaBuilder;
import com.blazebit.persistence.CriteriaBuilderFactory;
import com.blazebit.persistence.PagedList;
import com.blazebit.persistence.criteria.BlazeCriteria;
import com.blazebit.persistence.criteria.BlazeCriteriaBuilder;
import com.blazebit.persistence.criteria.BlazeCriteriaQuery;
import com.github.jensgerdes.cteissue.cte.ArticleCTE;
import com.github.jensgerdes.cteissue.cte.StoryCTE;
import com.github.jensgerdes.cteissue.cte.UnionCTE;
import com.github.jensgerdes.cteissue.entity.DbArticle;
import com.github.jensgerdes.cteissue.entity.DbStory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;

@Component
@RequiredArgsConstructor
public class DatabaseRepository {
    private final CriteriaBuilderFactory factory;
    private final EntityManager em;

    @Transactional(readOnly = true)
    public PagedList<Tuple> performRequest(boolean inlineCTE) {
        BlazeCriteriaBuilder cb = BlazeCriteria.get(factory);

        BlazeCriteriaQuery<Tuple> storyQuery = cb.createQuery(Tuple.class);
        storyQuery.from(DbStory.class);
        //storyQuery.where(storyPredicate);

        BlazeCriteriaQuery<Tuple> articleQuery = cb.createQuery(Tuple.class);
        articleQuery.from(DbArticle.class);
        //articleQuery.where(articlePredicate);

        CriteriaBuilder<Tuple> storyBuilder = storyQuery.createCriteriaBuilder(em);
        CriteriaBuilder<Tuple> articleBuilder = articleQuery.createCriteriaBuilder(em);

        return factory.create(em, Tuple.class)
                .with(StoryCTE.class, storyBuilder, inlineCTE)
                .bind("id").select("id")
                .bind("statusChangedAt").select("statusChangedAt")
                .end()
                .with(ArticleCTE.class, articleBuilder, inlineCTE)
                .bind("id").select("id")
                .bind("statusChangedAt").select("statusChangedAt")
                .end()
                .with(UnionCTE.class, inlineCTE)
                .from(StoryCTE.class, "s")
                .bind("id").select("s.id")
                .bind("statusChangedAt").select("s.statusChangedAt")
                .bind("type").select("'story'")
                .unionAll()
                .from(ArticleCTE.class, "a")
                .bind("id").select("a.id")
                .bind("statusChangedAt").select("a.statusChangedAt")
                .bind("type").select("'article'")
                .endSet()
                .end()
                .from(UnionCTE.class)
                .orderByDesc("statusChangedAt")
                .orderByAsc("id")
                .page(0, 100)
                .getResultList();
    }
}
