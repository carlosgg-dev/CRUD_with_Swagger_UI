package org.example.element;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElementService {

	private final ElementRepositoryJdbc elementRepositoryJdbc;

	public List<ElementApi> obtainAllElements() {

		return elementRepositoryJdbc.find().stream()
				.map(this::convertToElementApi)
				.toList();
	}

	public Optional<ElementApi> obtainElementById(final int id) {

		return elementRepositoryJdbc.findById(id)
				.map(this::convertToElementApi);
	}

	public List<ElementApi> obtainElementsByParameters(final String name, final String type) {

		return elementRepositoryJdbc.findByParameters(name, type).stream()
			.map(this::convertToElementApi)
			.toList();
	}

	public ElementApi createElement(final ElementApi elementApi) {

		if (this.obtainElementById(elementApi.getId()).isPresent()) {
			throw new IllegalArgumentException("The element already exist");
		}

		final ElementData elementData = this.convertToElementData(elementApi);
		this.elementRepositoryJdbc.create(elementData);

		return this.convertToElementApi(elementData);
	}

	public ElementApi updateElement(final ElementApi elementApi) {

		checkIfElementExist(elementApi.getId());

		final ElementData elementData = this.convertToElementData(elementApi);
		this.elementRepositoryJdbc.update(elementData);

		return this.convertToElementApi(elementData);
	}

	public void deleteAllElements() {

		this.elementRepositoryJdbc.deleteAll();
	}

	public void deleteElement(final int elementId) {

		checkIfElementExist(elementId);

		this.elementRepositoryJdbc.delete(elementId);
	}

	private void checkIfElementExist(final int elementId) {

		if (this.obtainElementById(elementId).isEmpty()) {
			throw new NoSuchElementException("The element not exist");
		}
	}

	private ElementData convertToElementData(final ElementApi elementApi) {

		return ElementData.builder()
			.id(elementApi.getId())
			.name(elementApi.getName())
			.type(elementApi.getType())
			.build();
	}

	private ElementApi convertToElementApi(final ElementData elementData) {

		return ElementApi.builder()
			.id(elementData.getId())
			.name(elementData.getName())
			.type(elementData.getType())
			.build();
	}
}
