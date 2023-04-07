package org.example.element;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElementService {

	private final ElementRepository elementRepository;

	public List<ElementApi> obtainAllElements() {

		return elementRepository.findAll().stream()
				.map(this::convertToElementApi)
				.toList();
	}

	public Optional<ElementApi> obtainElementById(final int id) {

		return elementRepository.findById(id)
				.map(this::convertToElementApi);
	}

	public ElementApi createElement(final ElementApi elementApi) {

		final ElementData elementData = this.convertToElementData(elementApi);

		this.elementRepository.save(elementData);

		return this.convertToElementApi(elementData);
	}

	public Optional<ElementApi> updateElement(final ElementApi elementApi) {

		final ElementData elementData = this.convertToElementData(elementApi);

		if (this.obtainElementById(elementData.id).isPresent()) {

			return Optional.of(this.createElement(elementApi));
		}

		return Optional.empty();
	}

	public void deleteAllElements() {

		this.elementRepository.deleteAll();
	}

	public void deleteElement(final int id) {

		this.elementRepository.deleteById(id);
	}

	private ElementData convertToElementData(final ElementApi elementApi) {

		return new ElementData(elementApi.getId(), elementApi.getName());
	}

	private ElementApi convertToElementApi(final ElementData elementData) {

		return ElementApi.builder()
				.id(elementData.id)
				.name(elementData.name)
				.build();
	}
}
