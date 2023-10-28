package app.wooportal.server.test.units.core.setup.entities.base;

import static com.querydsl.core.types.PathMetadataFactory.forVariable;

import javax.annotation.Generated;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.PathMetadata;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.ListPath;
import com.querydsl.core.types.dsl.PathInits;
import com.querydsl.core.types.dsl.StringPath;

import app.wooportal.server.test.units.core.setup.entities.child.QTestChildEntity;
import app.wooportal.server.test.units.core.setup.entities.child.TestChildEntity;


/**
 * QTestDataEntity is a Querydsl query type for TestDataEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTestEntity extends EntityPathBase<TestEntity> {

    private static final long serialVersionUID = -607055182L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QTestEntity testEntity = new QTestEntity("testEntity");

    public final app.wooportal.server.core.base.QBaseEntity _super = new app.wooportal.server.core.base.QBaseEntity(this);

    public final QTestChildEntity child;

    public final ListPath<TestChildEntity, QTestChildEntity> childs = this.<TestChildEntity, QTestChildEntity>createList("childs", TestChildEntity.class, QTestChildEntity.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.OffsetDateTime> created = _super.created;

    //inherited
    public final StringPath id = _super.id;

    //inherited
    public final DateTimePath<java.time.OffsetDateTime> modified = _super.modified;

    public final StringPath name = createString("name");

    public QTestEntity(String variable) {
        this(TestEntity.class, forVariable(variable), INITS);
    }

    public QTestEntity(Path<? extends TestEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QTestEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QTestEntity(PathMetadata metadata, PathInits inits) {
        this(TestEntity.class, metadata, inits);
    }

    public QTestEntity(Class<? extends TestEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.child = inits.isInitialized("child") ? new QTestChildEntity(forProperty("child")) : null;
    }

}

