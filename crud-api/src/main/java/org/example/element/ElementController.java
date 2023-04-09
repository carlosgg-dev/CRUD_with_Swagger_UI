package org.example.element;

import static org.example.common.ApiConstants.DELETE_ALL_ELEMENTS_DESCRIPTION;
import static org.example.common.ApiConstants.DELETE_ALL_ELEMENTS_SUMMARY;
import static org.example.common.ApiConstants.DELETE_ELEMENT_DESCRIPTION;
import static org.example.common.ApiConstants.DELETE_ELEMENT_SUMMARY;
import static org.example.common.ApiConstants.ELEMENTS_BASE_MAPPING;
import static org.example.common.ApiConstants.ELEMENT_BASE_MAPPING;
import static org.example.common.ApiConstants.FILTERED_ELEMENTS_BASE_MAPPING;
import static org.example.common.ApiConstants.GET_ELEMENTS_SUMMARY;
import static org.example.common.ApiConstants.GET_ELEMENT_DESCRIPTION;
import static org.example.common.ApiConstants.POST_ELEMENT_DESCRIPTION;
import static org.example.common.ApiConstants.POST_ELEMENT_SUMMARY;
import static org.example.common.ApiConstants.PUT_ELEMENT_DESCRIPTION;
import static org.example.common.ApiConstants.PUT_ELEMENT_SUMMARY;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Elements maintenance CRUD")
@Validated
@RestController
@RequestMapping(ELEMENTS_BASE_MAPPING)
@RequiredArgsConstructor
public class ElementController {

	private final ElementService elementService;

	@GetMapping
	@Operation(summary = GET_ELEMENTS_SUMMARY, description = GET_ELEMENT_DESCRIPTION)
	public List<ElementApi> obtainAllElements() {

		return this.elementService.obtainAllElements();
	}

	@GetMapping(ELEMENT_BASE_MAPPING)
	@Operation(summary = GET_ELEMENTS_SUMMARY, description = GET_ELEMENT_DESCRIPTION)
	public Optional<ElementApi> obtainElement(@PathVariable final int elementId) {

		return this.elementService.obtainElementById(elementId);
	}

	@GetMapping(FILTERED_ELEMENTS_BASE_MAPPING)
	@Operation(summary = GET_ELEMENTS_SUMMARY, description = GET_ELEMENT_DESCRIPTION)
	public List<ElementApi> obtainElementsByParameters(@Parameter(description = "Element name", name = "name")
													   @RequestParam(value = "name", required = false) final String name,
													   @Parameter(description = "Element type", name = "type")
													   @RequestParam(value = "type", required = false) final String type) {

		return this.elementService.obtainElementsByParameters(name, type);
	}

	@PostMapping
	@Operation(summary = POST_ELEMENT_SUMMARY, description = POST_ELEMENT_DESCRIPTION)
	public ElementApi createElement(@RequestBody @Valid final ElementApi elementApi) {

		return this.elementService.createElement(elementApi);
	}

	@PutMapping
	@Operation(summary = PUT_ELEMENT_SUMMARY, description = PUT_ELEMENT_DESCRIPTION)
	public ElementApi updateElement(@RequestBody @Valid final ElementApi elementApi) {

		return this.elementService.updateElement(elementApi);
	}

	@DeleteMapping
	@Operation(summary = DELETE_ALL_ELEMENTS_SUMMARY, description = DELETE_ALL_ELEMENTS_DESCRIPTION)
	public void deleteAllElements() {

		this.elementService.deleteAllElements();
	}

	@DeleteMapping(ELEMENT_BASE_MAPPING)
	@Operation(summary = DELETE_ELEMENT_SUMMARY, description = DELETE_ELEMENT_DESCRIPTION)
	public void deleteElement(@PathVariable final int elementId) {

		this.elementService.deleteElement(elementId);
	}
}
