package org.example.element;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ElementRepositoryJdbc {

	private static final String WHERE = " WHERE ";
	private static final String QUERY_CONCATENATION = " AND ";
	private static final String COMMON_SELECT_SQL_HEADER = """
			SELECT ID, NAME, TYPE
			FROM ELEMENTS
			""";

	private static final RowMapper<ElementData> ROW_MAPPER =
		(resultSet, row) -> ElementData.builder()
			.id(resultSet.getInt("ID"))
			.name(resultSet.getString("NAME"))
			.type(resultSet.getString("TYPE"))
			.build();

	private final JdbcTemplate jdbcTemplate;
	private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<ElementData> find() {

		return this.jdbcTemplate.query(COMMON_SELECT_SQL_HEADER, ROW_MAPPER);
	}

	public List<ElementData> findByParameters(final String name, final String type) {

		final Map<String, String> filterParameters = new HashMap<>();
		final String queryFilters = this.buildFindByParametersQueryFilters(name, type, filterParameters);
		final String query = this.buildFindByParametersQuery(queryFilters);

		return this.namedParameterJdbcTemplate.query(query, filterParameters, ROW_MAPPER);
	}

	public Optional<ElementData> findById(final int elementId) {

		final String query = """
			SELECT ID, NAME, TYPE
			FROM ELEMENTS
			WHERE ID = ?
			""";

		try {
			return Optional.ofNullable(this.jdbcTemplate.queryForObject(query, ROW_MAPPER, elementId));
		} catch (final EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	public void create(final ElementData elementData) {

		final String query = """
			INSERT INTO
			ELEMENTS
			(ID, NAME, TYPE)
			VALUES
			(?, ?, ?)
			""";

		this.jdbcTemplate.update(query, elementData.getId(), elementData.getName(), elementData.getType());
	}

	public void update(final ElementData elementData) {

		final String query = """
			UPDATE ELEMENTS
			SET
			NAME = ?
			TYPE = ?
			WHERE ID = ?
			""";

		this.jdbcTemplate.update(query, elementData.getName(), elementData.getType(), elementData.getId());
	}

	public void deleteAll() {

		final String query = """
			DELETE FROM
			ELEMENTS
			""";

		this.jdbcTemplate.update(query);
	}

	public void delete(final int elementId) {

		final String query = """
			DELETE FROM
			ELEMENTS
			WHERE ID = ?
			""";

		this.jdbcTemplate.update(query, elementId);
	}

	private String buildFindByParametersQueryFilters(final String name, final String type, final Map<String, String> filterParameters) {

		final List<String> queryFilters = new ArrayList<>();

		if (isNotBlank(name)) {
			queryFilters.add("NAME = :name");
			filterParameters.put("name", name);
		}

		if (isNotBlank(type)) {
			queryFilters.add("TYPE = :type");
			filterParameters.put("type", type);
		}

		return String.join(QUERY_CONCATENATION, queryFilters);
	}

	private String buildFindByParametersQuery(final String filters) {

		return isNotEmpty(filters)
			? COMMON_SELECT_SQL_HEADER + WHERE + filters
			: COMMON_SELECT_SQL_HEADER;
	}
}
