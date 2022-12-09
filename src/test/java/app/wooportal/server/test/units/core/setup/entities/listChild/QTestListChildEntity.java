package app.wooportal.server.test.units.core.setup.entities.listChild;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringPath;


/**
 * QTestDataChildEntity is a Querydsl query type for TestDataChildEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTestListChildEntity extends EntityPathBase<TestListChildEntity> {

    private static final long serialVersionUID = 438188720L;

    public static final QTestListChildEntity testListChildEntity = new QTestListChildEntity("testListChildEntity");

    public final app.wooportal.server.core.base.QBaseEntity _super = new app.wooportal.server.core.base.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.OffsetDateTime> created = _super.created;

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final DateTimePath<java.time.OffsetDateTime> modified = _super.modified;

    public final StringPath name = createString("name");

    public QTestListChildEntity(String variable) {
        super(TestListChildEntity.class, forVariable(variable));
    }

    public QTestListChildEntity(Path<? extends TestListChildEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTestListChildEntity(PathMetadata metadata) {
        super(TestListChildEntity.class, metadata);
    }

}

