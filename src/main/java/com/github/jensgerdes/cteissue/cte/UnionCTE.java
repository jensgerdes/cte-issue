package com.github.jensgerdes.cteissue.cte;

import com.blazebit.persistence.CTE;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@CTE
@Entity
@Getter
@Setter
public class UnionCTE {
    @Id
    private Long id;
    private OffsetDateTime statusChangedAt;
    private String type;
}
