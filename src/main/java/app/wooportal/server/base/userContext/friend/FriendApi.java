package app.wooportal.server.base.userContext.friend;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import app.wooportal.server.core.base.CrudApi;
import app.wooportal.server.core.base.dto.listing.FilterSortPaginate;
import app.wooportal.server.core.base.dto.listing.PageableList;
import app.wooportal.server.core.security.permissions.AdminPermission;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@GraphQLApi
@Component
public class FriendApi extends CrudApi<FriendEntity, FriendService> {

	public FriendApi(FriendService userService) {
		super(userService);
	}

	@Override
	@GraphQLQuery(name = "getFriends")
	public PageableList<FriendEntity> readAll(@GraphQLArgument(name = CrudApi.params) FilterSortPaginate params) {
		return super.readAll(params);
	}

	@Override
	@GraphQLQuery(name = "getFriend")
	public Optional<FriendEntity> readOne(@GraphQLArgument(name = CrudApi.entity) FriendEntity entity) {
		return super.readOne(entity);
	}

	@Override
	@GraphQLMutation(name = "saveFriends")
	public List<FriendEntity> saveAll(@GraphQLArgument(name = CrudApi.entities) List<FriendEntity> entities) {
		return super.saveAll(entities);
	}

	@Override
	@GraphQLMutation(name = "saveFriend")
	public FriendEntity saveOne(@GraphQLArgument(name = CrudApi.entity) FriendEntity entity) {
		return super.saveOne(entity);
	}

	@Override
	@GraphQLMutation(name = "deleteFriends")
	public Boolean deleteAll(@GraphQLArgument(name = CrudApi.ids) List<String> ids) {
		return super.deleteAll(ids);
	}

	@Override
	@GraphQLMutation(name = "deleteFriend")
	public Boolean deleteOne(@GraphQLArgument(name = CrudApi.id) String id) {
		return super.deleteOne(id);
	}
}
